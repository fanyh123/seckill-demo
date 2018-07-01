package com.yangyang.seckill.service;

import com.yangyang.seckill.daomain.OrderInfo;
import com.yangyang.seckill.daomain.SeckillOrder;
import com.yangyang.seckill.daomain.User;
import com.yangyang.seckill.vo.GoodsVo;

import java.util.List;

public interface OrderService {

	/**
	 * 根据用户id和商品id获取秒杀订单详情
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	SeckillOrder getSeckillOrderByUserIdGoodsId(long userId,long goodsId);

	/**
	 * 生产订单信息和秒杀订单信息
	 * @param user
	 * @param goodsVo
	 * @return
	 */
	OrderInfo createOrder(User user, GoodsVo goodsVo);
}
