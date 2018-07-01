package com.yangyang.seckill.service;

import com.yangyang.seckill.daomain.OrderInfo;
import com.yangyang.seckill.daomain.User;
import com.yangyang.seckill.vo.GoodsVo;

public interface SeckillService {


	OrderInfo doSeckill(User user, GoodsVo goodsVo);
	
}
