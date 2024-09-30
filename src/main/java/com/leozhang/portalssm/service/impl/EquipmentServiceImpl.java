package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.entity.EquipmentExample;
import com.leozhang.portalssm.mapper.EquipmentMapper;
import com.leozhang.portalssm.service.EquipmentService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Result selectListForPage(int pno, int psize, String name, Long brandId, Long
            statusId, String sortField, String sortType, Long roomId) {
        Page<Equipment> p = PageHelper.startPage(pno, psize);
        EquipmentExample equipmentExample = new EquipmentExample();
        EquipmentExample.Criteria criteria = equipmentExample.createCriteria();
        //追加机房id的条件
        if(roomId!=null){
            criteria.andRoomIdEqualTo(roomId);
        }
        if(name.trim().length()>0){
            criteria.andNameLike("%"+name+"%");
        }
        if(brandId!=null){
            criteria.andBrandIdEqualTo(brandId);
        }
        if(statusId!=null){
            criteria.andStatusIdEqualTo(statusId);
        }
        if(sortField.trim().length()>0){

            equipmentExample.setOrderByClause(ChangeChar.camelToUnderline(sortField,2) +" "+sortType);
        }
        List<Equipment> list = equipmentMapper.selectAllByExample(equipmentExample);
        return Result.end(200,list,"查询成功",p.getTotal());
    }

    @Override
    public void insert(Equipment equipment) {
        equipment.setInsertTime(new Date());
        equipmentMapper.insert(equipment);
    }

    @Override
    public Equipment selectById(Long id) {
        return equipmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Equipment> selectAll() {
        //这里需要搜索没有被绑定机房的设备
        EquipmentExample equipmentExample = new EquipmentExample();
        EquipmentExample.Criteria criteria = equipmentExample.createCriteria();
        criteria.andRoomIdIsNull();
        return equipmentMapper.selectByExample(equipmentExample);
    }

    @Override
    public void update(Equipment equipment) {
        EquipmentExample equipmentExample = new EquipmentExample();
        EquipmentExample.Criteria criteria = equipmentExample.createCriteria();
        criteria.andIdEqualTo(equipment.getId());
        equipmentMapper.updateByExampleSelective(equipment,equipmentExample);
    }

    @Override
    public List<Equipment> selectAllUsed() {
        //这⾥需要查询已经被机房添加的设备
        EquipmentExample equipmentExample = new EquipmentExample();
        EquipmentExample.Criteria criteria = equipmentExample.createCriteria();
        criteria.andRoomIdIsNotNull();
        return equipmentMapper.selectByExample(equipmentExample);
    }
}
