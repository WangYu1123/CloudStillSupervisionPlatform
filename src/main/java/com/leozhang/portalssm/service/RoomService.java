package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Room;
import com.leozhang.portalssm.util.Result;

public interface RoomService {
    Result getListForPage(int pno, int psize, String name, Long areaId, String phone, String sortField, String sortType);


    Room selectRoomById(Long id);

    void updateRoom(Room room);

    void deleteEquipment(Long roomId, Long id);
}
