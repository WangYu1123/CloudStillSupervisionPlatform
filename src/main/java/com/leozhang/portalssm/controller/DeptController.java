package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.AssetType;
import com.leozhang.portalssm.entity.Dept;
import com.leozhang.portalssm.service.DeptService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {
    private List<String> iconList = new ArrayList<String>(Arrays.asList(
            "layui-icon-heart-fill",
            "layui-icon-heart",
            "layui-icon-light",
            "layui-icon-time",
            "layui-icon-bluetooth",
            "layui-icon-at",
            "layui-icon-mute",
            "layui-icon-mike",
            "layui-icon-key",
            "layui-icon-gift",
            "layui-icon-email",
            "layui-icon-rss",
            "layui-icon-wifi",
            "layui-icon-logout",
            "layui-icon-android",
            "layui-icon-ios",
            "layui-icon-windows",
            "layui-icon-transfer",
            "layui-icon-service",
            "layui-icon-subtraction",
            "layui-icon-addition",
            "layui-icon-slider",
            "layui-icon-print",
            "layui-icon-export",
            "layui-icon-cols",
            "layui-icon-screen-restore",
            "layui-icon-screen-full",
            "layui-icon-rate-half",
            "layui-icon-rate",
            "layui-icon-rate-solid",
            "layui-icon-cellphone",
            "layui-icon-vercode",
            "layui-icon-login-wechat",
            "layui-icon-login-qq",
            "layui-icon-login-weibo",
            "layui-icon-password",
            "layui-icon-username",
            "layui-icon-refresh-3",
            "layui-icon-auz",
            "layui-icon-spread-left",
            "layui-icon-shrink-right",
            "layui-icon-snowflake",
            "layui-icon-tips",
            "layui-icon-note",
            "layui-icon-home",
            "layui-icon-senior",
            "layui-icon-refresh",
            "layui-icon-refresh-1",
            "layui-icon-flag",
            "layui-icon-theme",
            "layui-icon-notice",
            "layui-icon-website",
            "layui-icon-console",
            "layui-icon-face-surprised",
            "layui-icon-set",
            "layui-icon-template-1",
            "layui-icon-app",
            "layui-icon-template",
            "layui-icon-praise",
            "layui-icon-tread",
            "layui-icon-male",
            "layui-icon-female",
            "layui-icon-camera",
            "layui-icon-camera-fill",
            "layui-icon-more",
            "layui-icon-more-vertical",
            "layui-icon-rmb",
            "layui-icon-dollar",
            "layui-icon-diamond",
            "layui-icon-fire",
            "layui-icon-return",
            "layui-icon-location",
            "layui-icon-read",
            "layui-icon-survey",
            "layui-icon-face-smile",
            "layui-icon-face-cry",
            "layui-icon-cart-simple",
            "layui-icon-cart",
            "layui-icon-next",
            "layui-icon-prev",
            "layui-icon-upload-drag",
            "layui-icon-upload",
            "layui-icon-download-circle",
            "layui-icon-component",
            "layui-icon-file-b",
            "layui-icon-user",
            "layui-icon-find-fill",
            "layui-icon-loading",
            "layui-icon-loading-1",
            "layui-icon-add-1",
            "layui-icon-play",
            "layui-icon-pause",
            "layui-icon-headset",
            "layui-icon-video",
            "layui-icon-voice",
            "layui-icon-speaker",
            "layui-icon-fonts-del",
            "layui-icon-fonts-code",
            "layui-icon-fonts-html",
            "layui-icon-fonts-strong",
            "layui-icon-unlink",
            "layui-icon-picture",
            "layui-icon-link",
            "layui-icon-face-smile-b",
            "layui-icon-align-left",
            "layui-icon-align-right",
            "layui-icon-align-center",
            "layui-icon-fonts-u",
            "layui-icon-fonts-i",
            "layui-icon-tabs",
            "layui-icon-radio",
            "layui-icon-circle",
            "layui-icon-edit",
            "layui-icon-share",
            "layui-icon-delete",
            "layui-icon-form",
            "layui-icon-cellphone-fine",
            "layui-icon-dialogue",
            "layui-icon-fonts-clear",
            "layui-icon-layer",
            "layui-icon-date",
            "layui-icon-water",
            "layui-icon-code-circle",
            "layui-icon-carousel",
            "layui-icon-prev-circle",
            "layui-icon-layouts",
            "layui-icon-util",
            "layui-icon-templeate-1",
            "layui-icon-upload-circle",
            "layui-icon-tree",
            "layui-icon-table",
            "layui-icon-chart",
            "layui-icon-chart-screen",
            "layui-icon-engine",
            "layui-icon-triangle-d",
            "layui-icon-triangle-r",
            "layui-icon-file",
            "layui-icon-set-sm",
            "layui-icon-reduce-circle",
            "layui-icon-add-circle",
            "layui-icon-404",
            "layui-icon-about",
            "layui-icon-up",
            "layui-icon-down",
            "layui-icon-left",
            "layui-icon-right",
            "layui-icon-circle-dot",
             "layui-icon-search",
             "layui-icon-set-fill",
             "layui-icon-group",
             "layui-icon-friends",
             "layui-icon-reply-fill",
             "layui-icon-menu-fill",
             "layui-icon-log",
             "layui-icon-picture-fine",
             "layui-icon-face-smile-fine",
             "layui-icon-list",
             "layui-icon-release",
             "layui-icon-ok",
             "layui-icon-help",
             "layui-icon-chat",
             "layui-icon-top",
             "layui-icon-star",
             "layui-icon-star-fill",
             "layui-icon-close-fill",
             "layui-icon-close",
             "layui-icon-ok-circle",
             "layui-icon-add-circle-fine"
             ));

    @Autowired
    private DeptService deptService;
    @RequiresPermissions(value = {"permission:query"})
    @RequestMapping("/list")
    public String deptList(Long pid, Model model){
        model.addAttribute("pid",pid);
        return "staff/dept/list";
    }

    /**
     * 修改页面跳转
     * @param id
     * @param pid
     * @param model
     * @return
     */
    @RequestMapping("/edit/page")
    public String editPage(Long id,Long pid,Model model){
        //查询原始数据并且存储到formData中
        Dept dept = deptService.selectDeptById(id);
        model.addAttribute("formData",dept);
        model.addAttribute("pid",pid);
        model.addAttribute("iconList",iconList);
        return "staff/dept/edit";
    }

    /**
     * 修改保存
     */
    @RequestMapping("/edit")
    public String edit(Dept dept,Long pid){
        deptService.updateDeptById(dept);
        return "redirect:/dept/list?pid="+pid;
    }

    /**
     * 新增跳转
     * */
    @RequestMapping("/add/page")
    public String addPage(Long pid,Model model){
        model.addAttribute("pid",pid);
        model.addAttribute("iconList",iconList);
        return "staff/dept/add";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(Long id,Long pid){
        deptService.deleteDeptById(id);
        return "redirect:/dept/list?pid="+pid;
    }


    /**
     * 新增
     * */
    @RequestMapping("/add")
    public String add(Dept dept){
        deptService.insertDept(dept);
        //这⾥在重定向的时候⼀定要携带pid保证返回的查询⻚⾯还是当前层级的列表展示⻚
        return "redirect:/dept/list?pid="+dept.getPid();
    }


    /**
     * 获取当前id下的部门信息
     */
    @RequestMapping("/list/pid")
    @ResponseBody
    public Result getDeptListByPid(Long pid){
        return deptService.getDeptListByPid(pid);
    }



    @RequestMapping("/list/page")
    @ResponseBody
    public Result deptListPage(
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            @RequestParam(value = "psize",defaultValue = "10")int psize,
            @RequestParam(value = "pid",defaultValue = "-1")Long pid,
            @RequestParam(value = "name",defaultValue = "")String name,
            @RequestParam(value = "sortField",defaultValue = "")String sortField,
            @RequestParam(value = "sortType",defaultValue = "")String sortType
    ){
        return deptService.getListForPage(pno,psize,pid,name,sortField,sortType);
    }
}
