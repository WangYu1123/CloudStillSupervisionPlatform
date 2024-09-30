package com.leozhang.portalssm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leozhang.portalssm.entity.Dept;
import com.leozhang.portalssm.entity.Menu;
import com.leozhang.portalssm.entity.User;
import com.leozhang.portalssm.entity.UserExample;
import com.leozhang.portalssm.mapper.DeptMapper;
import com.leozhang.portalssm.mapper.MenuMapper;
import com.leozhang.portalssm.mapper.UserMapper;
import com.leozhang.portalssm.service.UserService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public String getUserList() {
        UserExample userEx = new UserExample();
        UserExample.Criteria criteria = userEx.createCriteria();
        criteria.andUsernameLike("admin");
        List<User> list = userMapper.selectByExample(userEx);
        return JSONObject.toJSONString(list);
    }

    @Override
    public String getMenuListForPage(int pno, int psize) {
        Page<Menu> p = PageHelper.startPage(pno, psize);
        List<Menu> list = menuMapper.selectByExample(null);
        PageInfo<Menu> page = new PageInfo<Menu>(list);
        JSONObject j = new JSONObject();
        System.out.println(page.getList());
        System.out.println(page.getPages());
        j.put("list",page.getList());
        j.put("page",page);
        return j.toJSONString();
    }

    @Override
    public List<Menu> testList(int pno, int psize, Model model) {
        Page<Menu> p = PageHelper.startPage(pno, psize);
        List<Menu> list = menuMapper.selectByExample(null);
        Page<Menu> page = (Page)list;
        System.out.println(p.getTotal());
        System.out.println(p.getResult());
        System.out.println(p.getPages());
        System.out.println(page.getResult());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.getResult().get(0));
        model.addAttribute("list",list);
        model.addAttribute("page",page);
        model.addAttribute("pageSize",page.getPageSize());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("total",page.getTotal());
        return page;
    }

    @Override
    public User findByUsername(String userName) {

        UserExample ex = new UserExample();
        UserExample.Criteria cri = ex.createCriteria();
        cri.andUsernameEqualTo(userName);
        List<User> list = userMapper.selectByExample(ex);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public User login(String username, String password, HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        User u = null;
        try {
            currentUser.login(token);

            u = (User)currentUser.getPrincipals().getPrimaryPrincipal();
            request.getSession().setAttribute("userInfo",u);

        }catch (UnknownAccountException e) {
            System.out.println("账号不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码不正确");
        } catch (AuthenticationException e) {
            System.out.println("用户验证失败");
        }

        return u;
    }

    @Override
    public Result getListForPage(int pno, int psize, String username, String sortField, String sortType) {
        Page<User> p = PageHelper.startPage(pno, psize);
        UserExample ue = new UserExample();
        UserExample.Criteria userCriteria = ue.createCriteria();
        if(username.trim().length()>0){
            userCriteria.andUsernameLike("%"+username+"%");
        }
        if(sortField.trim().length()>0){
            ue.setOrderByClause(ChangeChar.camelToUnderline(sortField,2) +" " +sortType);

        }

        userMapper.selectAllByExampleForPage(ue);



        return Result.end(200,p.getResult(),"查询成功",p.getTotal());
    }

    @Override
    public void insertUser(User user) {
        user.setInsertTime(new Date());
        user.setFreeze(0);
        String oldPassword = user.getPassword();
        SimpleHash newPassword = new SimpleHash("MD5", oldPassword, "abc", 2);
        user.setPassword(newPassword.toString());
        userMapper.insert(user);
    }


    @Override
    public void editUser(User user) {
        String oldPassword = user.getPassword();
        SimpleHash newPassword = new SimpleHash("MD5", oldPassword, "abc", 2);
        user.setPassword(newPassword.toString());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String changePassword(HttpSession session, User user, Model model) {
        String password = user.getPassword();
        String password1 = user.getPassword1();
        String password2 = user.getPassword2();
        if(password == null || password.trim().length()==0){
            model.addAttribute("error",true);
            model.addAttribute("msg","原密码不可以为空");
            return "system/password/password";
        }
        UserExample ue = new UserExample();
        UserExample.Criteria criteria = ue.createCriteria();
        SimpleHash passwordDecoded = new SimpleHash("MD5", password, "abc", 2);
        criteria.andUsernameEqualTo(user.getUsername())
                .andPasswordEqualTo(passwordDecoded.toString());
        List<User> list = userMapper.selectByExample(ue);
        model.addAttribute("formData",user);
        if(list.size() == 0){
            model.addAttribute("error",true);
            model.addAttribute("msg","原密码不正确");
            return "system/password/password";
        }
        if(password.equals(password1)){
            model.addAttribute("error",true);
            model.addAttribute("msg","原密码和新密码相同");
            return "system/password/password";
        }
        if(!password1.equals(password2)){
            model.addAttribute("error",true);
            model.addAttribute("msg","两次输入的密码不一致");
            return "system/password/password";
        }
        SimpleHash password1Decoded = new SimpleHash("MD5", password1, "abc", 2);
        user.setPassword(password1Decoded.toString());
        userMapper.updateByPrimaryKeySelective(user);
//        session.invalidate();
        model.addAttribute("complete",true);
        return "system/password/password";
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);

    }

    @Override
    public void bindDept(Long id, Long deptId) {
        User u = new User();
        u.setId(id);
        u.setDeptId(deptId);
        userMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public String getCheckedDeptList(User user) {
        //获取所有部⻔数据列表
        List<Dept> deptList = deptMapper.selectByExample(null);
        //初始化保存分组的map对象，这⾥使⽤LinkedHashMap保证key的设置是有序的
        Map<String,List<Dept>> map = new LinkedHashMap<String,List<Dept>>();
        //调⽤递归整理函数，整理之后map中就会⽣成对应的key，每个key代表⼀组select对象，顺序是和⽹⻚相反的
        getSelectById(user.getDeptId(),map,deptList);
        //获取entry对象
        Set<Map.Entry<String, List<Dept>>> set = map.entrySet();
        //初始化返回数据的字符串对象
        String str = "";
        //循环当前的map对象，将内部的分组转换成select套option的格式
        for (Map.Entry<String, List<Dept>> entry:set) {
            //从每个map的key中分离出select 的id
            String eleId = entry.getKey().split("@")[0];
            //从每个map的key中分离出当前组已经选中的数据的id
            String selectedId = entry.getKey().split("@")[1];
            //整理select标签头部
            String select = "<select id=\""+eleId+"\" class=\"layui-select\" lay-filter=\"dept-base\">" + "<option value=\"\">请选择</option>";
            //获取option中需要拼接的内容
            List<Dept> listDept = entry.getValue();
            //遍历option对象
            for (Dept dept : listDept) {
                //将部⻔信息整理成option字符串
                String option = "<option value=\""+dept.getId()+"\" data-isLeaf=\""+dept.getIsLeaf()+"\" "
                        //判断如果当前选中的id和列表中的部⻔id相同就让他状态变成默认选中
                        +(Long.valueOf(selectedId)==dept.getId() ?"selected":"")
                        +">"
                        + dept.getName() +
                        "</option>";
                //拼接到select字符串中
                select+=option;
            }
            //select标签的收尾
            select += "</select>";
            //使⽤str对象将当前组合倒序拼接，这样才能按照从头到尾在⻚⾯中正常展示
            str =select+str;
        }
        // 返回拼接好的select字符串
        return str;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectByExample(null);
    }


    public void getSelectById(Long deptId, Map<String,List<Dept>> map, List<Dept> list){
        //根据当前的部⻔id获取部⻔对象
        List<Dept> list1 = list.stream().filter(dept -> {
            if (dept.getId() == deptId) {
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.<Dept>toList());
        //如果返回的list有⻓度再进⾏下⼀步
        if(list1.size()>0){
            //获取当前部⻔对象
            Dept dept = list1.get(0);
            //根据当前部⻔的pid筛选他的同组数据l
            List<Dept> l = list.stream().filter(d -> {
                return d.getPid() == dept.getPid();
            }).collect(Collectors.toList());
            //如果部⻔的⽗id不是-1代表还没有追溯到头，进⾏递归和数据记录
            if(dept.getPid()!= -1){
                //使⽤dept-base部⻔⽗id@当前部⻔id作为key，存储当前同组数据
                map.put("dept-base"+dept.getPid()+"@"+dept.getId(),l);
                //组装完数据之后递归调⽤本函数
                getSelectById(dept.getPid(),map,list);
            }else{
                //当⽗id为-1的时候直接使⽤dept-base@部⻔id保存同组数据，代表递归结束
                map.put("dept-base@"+dept.getId(),l);
            }
        }
    }

}
