package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.Sex;
import com.leozhang.portalssm.service.SexService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/sex")
public class SexController {

    @Autowired
    private SexService sexService;
    //代表当访问/sex/list的时候执⾏该函数
    @RequiresPermissions("permission:query")
    @RequestMapping("/list")
    public String sexList(){
        //type/sex/list表示当前函数最后返回webapp->WEB-INF->views->type->sex->list.jsp⻚⾯
        return "type/sex/list";
    }

    /**
     * 添加跳转
     * @return
     */
    @RequestMapping("/add/page")
    public String addPage(){
        return "type/sex/add";
    }

    /**
     * 添加
     * @param sex
     */
    @RequestMapping("/add")
    public String addSex(Sex sex){
        sexService.insertSex(sex);
        return "redirect:/sex/list";
    }

    /**
     * 修改
     */
    @RequestMapping("/edit/page")
    public String editPage(Long id, Model model){
        Sex sex = sexService.selectSexById(id);
        //这⾥的key命名为formData是为了写别的模块可以快速的改
        model.addAttribute("formData",sex);
        return "type/sex/edit";
    }

    /**
     * 修改提交实现
     * @param sex
     * @return
     */
    @RequestMapping("/edit")
    public String sexEdit(Sex sex){
        sexService.updateSex(sex);
        return "redirect:/sex/list";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String sexDelete(Long id){
        sexService.deleteSexById(id);
        return "redirect:/sex/list";
    }

    /**
     * 分页查询
     * @param pno
     * @param psize
     * @param sexName
     * @param sortField
     * @param sortType
     * @return
     */
    @RequestMapping("/list/page")
    //代表返回的数据是json格式不使⽤⻚⾯
    @ResponseBody
    //Result对象是针对table封装好的返回对象
    public Result sexListPage(
            //pno为⻚号
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            //psize为每⻚查询多少条
            @RequestParam(value = "psize",defaultValue = "10")int psize,
            //sexName代表模糊查询的条件，根据性别名称查询
            @RequestParam(value = "sexName",defaultValue = "")String sexName,
            //sortField代表排序依赖的字段名
            @RequestParam(value = "sortField",defaultValue = "")String sortField,
            //sortType代表排序按照顺序还是倒序
            @RequestParam(value = "sortType",defaultValue = "")String sortType
    ) {
        return sexService.getListForPage(pno, psize, sexName, sortField, sortType);
    }

}
