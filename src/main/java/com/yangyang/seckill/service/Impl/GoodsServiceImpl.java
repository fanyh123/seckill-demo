package com.yangyang.seckill.service.Impl;

import com.yangyang.seckill.dao.GoodsDao;
import com.yangyang.seckill.daomain.SeckillGoods;
import com.yangyang.seckill.service.GoodsService;
import com.yangyang.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	GoodsDao goodsDao;

	@Override
	public List<GoodsVo> listGoodsVo(){
		return goodsDao.listGoodsVo();
	}

	@Override
	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

	@Override
	public void reduceStock(GoodsVo goods) {
		SeckillGoods g = new SeckillGoods();
		g.setGoodsId(goods.getId());
		goodsDao.reduceStock(g);
	}
	
	
	
}
