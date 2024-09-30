package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.RoomWarn;
import com.leozhang.portalssm.util.Result;

public interface RoomWarnService {
    Result selectListForPage(int pno, int psize, String sortField, String sortType);


    RoomWarn getRoomWarnByRoomId(Long roomId);


    void update(RoomWarn roomWarn);


}
