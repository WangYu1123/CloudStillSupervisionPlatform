package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.service.AssetStatusService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/assets-status")
public class AssetStatusController {

    @Autowired
    private AssetStatusService assetStatusService;

    @RequiresPermissions("permission:query")
    @RequestMapping("/list")
    public String assetTypeList(){
        //type/sex/list表示当前函数最后返回webapp->WEB-INF->views->type->sex->list.jsp⻚⾯
        return "type/assetStatus/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result assetTypeListPage(
            //pno为⻚号
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            //psize为每⻚查询多少条
            @RequestParam(value = "psize",defaultValue = "10")int psize,
            //sexName代表模糊查询的条件，根据性别名称查询
            @RequestParam(value = "assetStatus",defaultValue = "")String assetStatus,
            //sortField代表排序依赖的字段名
            @RequestParam(value = "sortField",defaultValue = "")String sortField,
            //sortType代表排序按照顺序还是倒序
            @RequestParam(value = "sortType",defaultValue = "")String sortType
    ) {
        return assetStatusService.getListForPage(pno, psize, assetStatus, sortField, sortType);
    }

}
