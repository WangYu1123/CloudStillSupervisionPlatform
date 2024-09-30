package com.leozhang.portalssm.service.impl;

import com.leozhang.portalssm.entity.RoomArea;
import com.leozhang.portalssm.mapper.RoomAreaMapper;
import com.leozhang.portalssm.service.RoomAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAreaServiceImpl implements RoomAreaService {

    @Autowired
    private RoomAreaMapper roomAreaMapper;

    @Override
    public List<RoomArea> selectListAll() {
        return roomAreaMapper.selectByExample(null);
    }
}
