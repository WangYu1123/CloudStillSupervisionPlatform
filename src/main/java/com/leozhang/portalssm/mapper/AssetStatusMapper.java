package com.leozhang.portalssm.mapper;

import com.leozhang.portalssm.entity.AssetStatus;
import com.leozhang.portalssm.entity.AssetStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetStatusMapper {
    long countByExample(AssetStatusExample example);

    int deleteByExample(AssetStatusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssetStatus record);

    int insertSelective(AssetStatus record);

    List<AssetStatus> selectByExample(AssetStatusExample example);

    AssetStatus selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssetStatus record, @Param("example") AssetStatusExample example);

    int updateByExample(@Param("record") AssetStatus record, @Param("example") AssetStatusExample example);

    int updateByPrimaryKeySelective(AssetStatus record);

    int updateByPrimaryKey(AssetStatus record);
}