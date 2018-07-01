package com.yangyang.seckill.service.Impl;

import com.yangyang.seckill.dao.OrderDao;
import com.yangyang.seckill.daomain.OrderInfo;
import com.yangyang.seckill.daomain.SeckillOrder;
import com.yangyang.seckill.daomain.User;
import com.yangyang.seckill.service.OrderService;
import com.yangyang.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderDao.getSeckillOrderByUserIdGoodsId(userId,goodsId);
    }

    @Override
    @Transactional
    public OrderInfo createOrder(User user, GoodsVo goodsVo) {

        //1.生产订单信息
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderDao.insert(orderInfo);

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setUserId(user.getId());
        orderDao.insertSeckillOrder(seckillOrder);
        return orderInfo;
    }
}
