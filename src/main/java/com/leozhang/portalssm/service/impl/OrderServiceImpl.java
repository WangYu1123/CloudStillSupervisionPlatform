package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Order;
import com.leozhang.portalssm.entity.OrderExample;
import com.leozhang.portalssm.entity.User;
import com.leozhang.portalssm.mapper.OrderMapper;
import com.leozhang.portalssm.service.OrderService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Result selectListForPage(int pno, int psize, Long orderStatusId, Long publicUserId, Long targetUserId, String sortField, String sortType) {
        Page<Order> p = PageHelper.startPage(pno, psize);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if(orderStatusId !=null){
            criteria.andOrderStatusIdEqualTo(orderStatusId);
        }
        if(publicUserId != null){
            criteria.andPublicUserIdEqualTo(publicUserId);
        }
        if(targetUserId != null ){
            criteria.andTargetUserIdEqualTo(targetUserId);
        }
        if(sortField.trim().length()>0){
            orderExample.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<Order> list = orderMapper.selectAllByExample(orderExample);
        return Result.end(200,list,"查询成功",p.getTotal());
    }

    @Override
    public void insertOrder(Order order, HttpSession session) {
        order.setOrderStatusId(Long.valueOf(1));
        User user = (User) session.getAttribute("userInfo");
        order.setPublicUserId(user.getId());
        order.setInsertTime(new Date());
        orderMapper.insert(order);
    }

    @Override
    public Order selectOrderById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateById(Order order) {
        order.setHandleTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
