package com.yangyang.seckill.service.Impl;

import com.yangyang.seckill.daomain.OrderInfo;
import com.yangyang.seckill.daomain.User;
import com.yangyang.seckill.service.SeckillService;
import com.yangyang.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    OrderServiceImpl orderService;
    @Override
    @Transactional
    public OrderInfo doSeckill(User user, GoodsVo goodsVo) {
        // 1.减库存
        goodsService.reduceStock(goodsVo);
        // 2.生产订单信息和秒杀订单信息
        OrderInfo orderInfo = orderService.createOrder(user,goodsVo);
        return orderInfo;
    }
}
