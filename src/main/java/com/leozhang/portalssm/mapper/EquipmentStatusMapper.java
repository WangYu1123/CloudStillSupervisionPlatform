package com.leozhang.portalssm.mapper;

import com.leozhang.portalssm.entity.EquipmentStatus;
import com.leozhang.portalssm.entity.EquipmentStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentStatusMapper {
    long countByExample(EquipmentStatusExample example);

    int deleteByExample(EquipmentStatusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentStatus record);

    int insertSelective(EquipmentStatus record);

    List<EquipmentStatus> selectByExample(EquipmentStatusExample example);

    EquipmentStatus selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentStatus record, @Param("example") EquipmentStatusExample example);

    int updateByExample(@Param("record") EquipmentStatus record, @Param("example") EquipmentStatusExample example);

    int updateByPrimaryKeySelective(EquipmentStatus record);

    int updateByPrimaryKey(EquipmentStatus record);
}