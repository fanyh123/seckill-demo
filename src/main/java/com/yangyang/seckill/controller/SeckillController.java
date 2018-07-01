package com.yangyang.seckill.controller;

import com.yangyang.seckill.daomain.OrderInfo;
import com.yangyang.seckill.daomain.SeckillOrder;
import com.yangyang.seckill.daomain.User;
import com.yangyang.seckill.result.CodeMsg;
import com.yangyang.seckill.service.Impl.GoodsServiceImpl;
import com.yangyang.seckill.service.Impl.OrderServiceImpl;
import com.yangyang.seckill.service.Impl.SeckillServiceImpl;
import com.yangyang.seckill.service.SeckillService;
import com.yangyang.seckill.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private static Logger logger = LoggerFactory.getLogger(SeckillController.class);

    @Autowired
    private GoodsServiceImpl goodsService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private SeckillServiceImpl seckillService;
    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, User user,
                            @RequestParam("goodsId")long goodsId) {
        if(user == null){
            return "login";
        }
        // 判断库存
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goodsVo.getStockCount();
        if(stock <= 0){
            model.addAttribute("errmsg", CodeMsg.SECKILL_OVER.getMsg());
            return  "seckillFail";
        }
        // 判断是否已经秒杀过
        SeckillOrder seckillOrder = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if(seckillOrder != null){
            model.addAttribute("errmsg", CodeMsg.REPEATE_SECKILL.getMsg());
            return "seckillFail";
        }
        // 1.减库存2.下订单3.写入秒杀订单(放在一个事务中处理)
        OrderInfo orderInfo = seckillService.doSeckill(user,goodsVo);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goodsVo);
        return "orderDetail";
    }
}
