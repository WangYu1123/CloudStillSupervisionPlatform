
package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.entity.Room;
import com.leozhang.portalssm.entity.RoomExample;
import com.leozhang.portalssm.mapper.EquipmentMapper;
import com.leozhang.portalssm.mapper.RoomMapper;
import com.leozhang.portalssm.service.RoomService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {


    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Result getListForPage(int pno, int psize, String name, Long areaId, String phone, String sortField, String sortType) {
        Page<Room> p = PageHelper.startPage(pno, psize);
        RoomExample roomExample = new RoomExample();
        RoomExample.Criteria criteria = roomExample.createCriteria();
        if(name.trim().length()>0){
            criteria.andNameLike("%"+name+"%");
        }
        if(areaId!=null&&areaId.toString().length()>0){
            criteria.andAreaIdEqualTo(areaId);
        }
        if(phone.trim().length()>0){
            criteria.andPhoneLike("%"+phone+"%");
        }
        if(sortField.trim().length()>0){
            roomExample.setOrderByClause(sortField+ " " + sortType);
        }
        List<Room> list = roomMapper.selectAllByExample(roomExample);
        System.out.println(list);
        return Result.end(200,list,"查询成功",p.getTotal());
    }

    @Override
    public Room selectRoomById(Long id) {
        return roomMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateRoom(Room room) {
        roomMapper.updateByPrimaryKeySelective(room);
    }

    @Override
    public void deleteEquipment(Long roomId, Long id) {
        Equipment equipment = equipmentMapper.selectByPrimaryKey(id);
        equipment.setRoomId(null);
        equipmentMapper.updateByPrimaryKey(equipment);
    }
}
