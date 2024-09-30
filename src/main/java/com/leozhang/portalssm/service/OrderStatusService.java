package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatus> selectListAll();
}
