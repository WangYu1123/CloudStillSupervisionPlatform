package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.util.Result;

import java.util.List;

public interface EquipmentService {
    Result selectListForPage(int pno, int psize, String name, Long brandId, Long statusId, String sortField, String sortType,Long roomId);

    void insert(Equipment equipment);


    Equipment selectById(Long id);


    List<Equipment> selectAll();

    void update(Equipment equipment);

    List<Equipment> selectAllUsed();

}
