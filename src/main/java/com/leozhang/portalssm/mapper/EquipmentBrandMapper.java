package com.leozhang.portalssm.mapper;

import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.entity.EquipmentBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentBrandMapper {
    long countByExample(EquipmentBrandExample example);

    int deleteByExample(EquipmentBrandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentBrand record);

    int insertSelective(EquipmentBrand record);

    List<EquipmentBrand> selectByExample(EquipmentBrandExample example);

    EquipmentBrand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentBrand record, @Param("example") EquipmentBrandExample example);

    int updateByExample(@Param("record") EquipmentBrand record, @Param("example") EquipmentBrandExample example);

    int updateByPrimaryKeySelective(EquipmentBrand record);

    int updateByPrimaryKey(EquipmentBrand record);
}