package com.yangyang.seckill.service;

import com.yangyang.seckill.vo.GoodsVo;

import java.util.List;

public interface GoodsService {

	/**
	 * 获取商品列表
	 * @return
	 */
	 List<GoodsVo> listGoodsVo();

	/**
	 * 根据id获取商品详情
	 * @param goodsId
	 * @return
	 */
	 GoodsVo getGoodsVoByGoodsId(long goodsId);

	 void reduceStock(GoodsVo goods);
	
}
