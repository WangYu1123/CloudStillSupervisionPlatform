package com.leozhang.portalssm.controller;


import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.entity.EquipmentStatus;
import com.leozhang.portalssm.service.EquipmentBrandService;
import com.leozhang.portalssm.service.EquipmentService;
import com.leozhang.portalssm.service.EquipmentStatusService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiresPermissions(value = {"permission:query"})
@RequestMapping("/equipment")
@Controller
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentBrandService equipmentBrandService;

    //在controller中增加注⼊
    @Autowired
    private EquipmentStatusService equipmentStatusService;

    /**
     * 查询
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"permission:query"})
    @RequestMapping("/list")
    public String list(Model model){
        List<EquipmentBrand> equipmentBrandList = equipmentBrandService.selectListAll();
        model.addAttribute("equipmentBrandList",equipmentBrandList);
        List<EquipmentStatus> equipmentStatusList =
                equipmentStatusService.selectListAll();
        model.addAttribute("equipmentStatusList",equipmentStatusList);
        return "room/equipment/list";
    }

    /**
     * 分页
     */
    @RequestMapping("/list/page")
    @ResponseBody
    public Result listPage(
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            @RequestParam(value = "psize",defaultValue = "10")int psize,
            @RequestParam(value = "name",defaultValue = "")String name,
            @RequestParam(value = "brandId",required = false)Long brandId,
            @RequestParam(value = "statusId",required = false)Long statusId,
            @RequestParam(value = "sortField",defaultValue = "")String sortField,
            @RequestParam(value = "sortType",defaultValue = "")String sortType,
            @RequestParam(value = "roomId",required=false)Long roomId
    ){
        return
                equipmentService.selectListForPage(pno,psize,name,brandId,statusId,sortField,sortType,roomId);
    }



    /**
     * 添加跳转
     */
    @RequiresPermissions(value = {"permission:insert"})
    @RequestMapping("/add/page")
    public String addPage(Model model){
        List<EquipmentBrand> equipmentBrandList = equipmentBrandService.selectListAll();
        model.addAttribute("equipmentBrandList",equipmentBrandList);
        return "room/equipment/add";
    }

    @RequestMapping("/set/status")
    public String setStatus(Long id,Model model){
        Equipment equipment = equipmentService.selectById(id);
        List<EquipmentStatus> equipmentStatusList =
                equipmentStatusService.selectListAll();
        model.addAttribute("formData",equipment);
        model.addAttribute("equipmentStatusList",equipmentStatusList);
        return "room/equipment/set-status";
    }

    /**
     * 设备数据的新增
     * @param equipment
     * @return
     */
    @RequiresPermissions(value = {"permission:insert"})
    @RequestMapping("/add")
    public String add(Equipment equipment){
        equipmentService.insert(equipment);
        return "redirect:/equipment/list";
    }

}
