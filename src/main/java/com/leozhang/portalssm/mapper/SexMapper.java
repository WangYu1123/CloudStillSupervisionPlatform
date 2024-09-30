package com.leozhang.portalssm.mapper;

import com.leozhang.portalssm.entity.Sex;
import com.leozhang.portalssm.entity.SexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SexMapper {
    long countByExample(SexExample example);

    int deleteByExample(SexExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Sex record);

    int insertSelective(Sex record);

    List<Sex> selectByExample(SexExample example);

    Sex selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sex record, @Param("example") SexExample example);

    int updateByExample(@Param("record") Sex record, @Param("example") SexExample example);

    int updateByPrimaryKeySelective(Sex record);

    int updateByPrimaryKey(Sex record);
}