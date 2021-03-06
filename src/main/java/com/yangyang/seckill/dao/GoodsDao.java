package com.yangyang.seckill.dao;

import java.util.List;

import com.yangyang.seckill.daomain.SeckillGoods;
import com.yangyang.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface GoodsDao {
	
	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.seckill_price from seckill_goods mg left join goods g on mg.goods_id = g.id")
	 List<GoodsVo> listGoodsVo();

	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.seckill_price from seckill_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
	 GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

	@Update("update seckill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
	 int reduceStock(SeckillGoods g);
	
}
