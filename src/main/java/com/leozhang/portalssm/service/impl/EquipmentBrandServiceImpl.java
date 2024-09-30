package com.leozhang.portalssm.service.impl;

import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.mapper.EquipmentBrandMapper;
import com.leozhang.portalssm.service.EquipmentBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentBrandServiceImpl implements EquipmentBrandService {

    @Autowired
    private EquipmentBrandMapper equipmentBrandMapper;

    @Override
    public List<EquipmentBrand> selectListAll() {
        return equipmentBrandMapper.selectByExample(null);
    }
}
