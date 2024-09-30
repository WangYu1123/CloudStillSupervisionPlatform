package com.leozhang.portalssm.service.impl;

import com.leozhang.portalssm.entity.EquipmentStatus;
import com.leozhang.portalssm.mapper.EquipmentStatusMapper;
import com.leozhang.portalssm.service.EquipmentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentStatusServiceImpl implements EquipmentStatusService {
    @Autowired
    private EquipmentStatusMapper equipmentStatusMapper;

    @Override
    public List<EquipmentStatus> selectListAll() {
       return equipmentStatusMapper.selectByExample(null);
    }
}
