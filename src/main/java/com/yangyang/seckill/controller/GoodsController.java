package com.yangyang.seckill.controller;

import com.yangyang.seckill.daomain.User;
import com.yangyang.seckill.service.GoodsService;
import com.yangyang.seckill.service.Impl.GoodsServiceImpl;
import com.yangyang.seckill.service.Impl.UserServiceImpl;
import com.yangyang.seckill.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsServiceImpl goodsService;

    @RequestMapping("/toList")
    public String toList(Model model,User user) {
        model.addAttribute("user",user);
        // 获取商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsList);
        return "goodsList";
    }

    /**
     * 跳转到商品详情页面
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model,User user,
    @PathVariable("goodsId")long goodsId) {

        // 获取商品列表
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods",goods);
        model.addAttribute("user",user);
        // 取秒杀商品开始时间和结束时间
        long startTime = goods.getStartDate().getTime();

        long endTime = goods.getEndDate().getTime();

        long nowTime = System.currentTimeMillis();

        // 秒杀状态
        int seckillStatus = 0;
        // 秒杀倒计时
        int remainSeconds = 0;
        // 秒杀还没开始，倒计时
        if(nowTime < startTime){
            seckillStatus=0;
            remainSeconds = (int)(startTime - nowTime)/1000;
            // 秒杀已经结束
        }else if(nowTime > endTime){
            remainSeconds = -1;
            seckillStatus=2;
            // 秒杀正在进行中
        }else {
            seckillStatus=1;
            remainSeconds=0;
        }
        model.addAttribute("seckillStatus",seckillStatus);
        model.addAttribute("remainSeconds",remainSeconds);
        return "goodsDetail";
    }


}
