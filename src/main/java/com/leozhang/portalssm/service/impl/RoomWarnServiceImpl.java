package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.RoomWarn;
import com.leozhang.portalssm.entity.RoomWarnExample;
import com.leozhang.portalssm.mapper.RoomWarnMapper;
import com.leozhang.portalssm.service.RoomWarnService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomWarnServiceImpl implements RoomWarnService {

    @Autowired
    private RoomWarnMapper roomWarnMapper;

    @Override
    public Result selectListForPage(int pno, int psize, String sortField, String sortType) {
        Page<RoomWarn> p = PageHelper.startPage(pno, psize);
        RoomWarnExample roomWarnExample = new RoomWarnExample();
        if(sortField.trim().length()>0){
            roomWarnExample.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<RoomWarn> list = roomWarnMapper.selectRoomWarnListByExample(roomWarnExample);
        return Result.end(200,list,"查询成功",p.getTotal());
    }

    @Override
    public RoomWarn getRoomWarnByRoomId(Long roomId) {
        //根据roomId在数据表中检索是否存在该机房的配置，如果没有就创建一条记录
        RoomWarnExample roomWarnExample = new RoomWarnExample();
        RoomWarnExample.Criteria criteria = roomWarnExample.createCriteria();
        criteria.andRoomIdEqualTo(roomId);
        List<RoomWarn> list = roomWarnMapper.selectByExample(roomWarnExample);
        RoomWarn roomWarn = null;
        if(list.size()>0){
            roomWarn = list.get(0);
        }else{
            roomWarn = new RoomWarn();
            roomWarn.setRoomId(roomId);
            roomWarnMapper.insert(roomWarn);
            RoomWarnExample roomWarnExample1 = new RoomWarnExample();
            RoomWarnExample.Criteria criteria1 = roomWarnExample.createCriteria();
            criteria.andRoomIdEqualTo(roomId);
            List<RoomWarn> list1 = roomWarnMapper.selectByExample(roomWarnExample);
            roomWarn = list1.get(0);
        }
        return roomWarn;
    }

    @Override
    public void update(RoomWarn roomWarn) {
        roomWarnMapper.updateByPrimaryKeySelective(roomWarn);
    }
}
