package com.leozhang.portalssm.mapper;

import com.leozhang.portalssm.entity.RoomArea;
import com.leozhang.portalssm.entity.RoomAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomAreaMapper {
    long countByExample(RoomAreaExample example);

    int deleteByExample(RoomAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomArea record);

    int insertSelective(RoomArea record);

    List<RoomArea> selectByExample(RoomAreaExample example);

    RoomArea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomArea record, @Param("example") RoomAreaExample example);

    int updateByExample(@Param("record") RoomArea record, @Param("example") RoomAreaExample example);

    int updateByPrimaryKeySelective(RoomArea record);

    int updateByPrimaryKey(RoomArea record);
}