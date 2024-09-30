package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.AssetType;
import com.leozhang.portalssm.entity.Sex;
import com.leozhang.portalssm.service.AssetTypeService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/assets")
public class assetTypeController {

    @Autowired
    private AssetTypeService assetTypeService;

    @RequiresPermissions("permission:query")
    @RequestMapping("/list")
    public String assetTypeList(){
        //type/sex/list表示当前函数最后返回webapp->WEB-INF->views->type->sex->list.jsp⻚⾯
        return "type/assetType/list";
    }

    /**
     * 添加跳转
     * @return
     */
    @RequestMapping("/add/page")
    public String addPage(){
        return "type/assetType/add";
    }

    /**
     * 添加
     * @param assetType
     * @return
     */
    @RequestMapping("/add")
    public String addPage(AssetType assetType){
        assetTypeService.insterAssetType(assetType);
        return "redirect:/assets/list";
    }

    /**
     * 修改
     */
    @RequestMapping("/edit/page")
    public String editPage(Long id, Model model){
        AssetType assetType = assetTypeService.selectAssetTypeById(id);
        //这⾥的key命名为formData是为了写别的模块可以快速的改
        model.addAttribute("formData",assetType);
        return "type/assetType/edit";
    }

    /**
     * 修改提交实现
     * @param assetType
     * @return
     */
    @RequestMapping("/edit")
    public String assetTypeEdit(AssetType assetType){
        assetTypeService.updateAssetType(assetType);
        return "redirect:/assets/list";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String assetTypeDelete(Long id){
        assetTypeService.deleteAssetTypeById(id);
        return "redirect:/assets/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result assetTypeListPage(
            //pno为⻚号
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            //psize为每⻚查询多少条
            @RequestParam(value = "psize",defaultValue = "10")int psize,
            //sexName代表模糊查询的条件，根据性别名称查询
            @RequestParam(value = "assetType",defaultValue = "")String assetType,
            //sortField代表排序依赖的字段名
            @RequestParam(value = "sortField",defaultValue = "")String sortField,
            //sortType代表排序按照顺序还是倒序
            @RequestParam(value = "sortType",defaultValue = "")String sortType
    ) {
        return assetTypeService.getListForPage(pno, psize, assetType, sortField, sortType);
    }


}
