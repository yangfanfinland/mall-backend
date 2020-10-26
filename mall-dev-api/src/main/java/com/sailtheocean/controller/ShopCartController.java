package com.sailtheocean.controller;

import com.sailtheocean.pojo.bo.ShopcartBO;
import com.sailtheocean.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "Shopping cart", tags = {"API interface for shopping cart"})
@RequestMapping("shopcart")
@RestController
public class ShopCartController {

    @ApiOperation(value = "Add product to shopping cart", notes = "Add product to shopping cart", httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(@RequestParam String userId,
                          @RequestBody ShopcartBO shopcartBO,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("");
        }

        System.out.println(shopcartBO);

        // TODO 前段用户在登陆的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存

        return JSONResult.ok();
    }

    @ApiOperation(value = "Delete product in shopping cart", notes = "Delete product in shopping cart", httpMethod = "POST")
    @PostMapping("/del")
    public JSONResult del(@RequestParam String userId,
                          @RequestParam String itemSpecId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return JSONResult.errorMsg("Parameters cannot be empty");
        }

        // TODO 用户在页面删除购物车中的商品数据，如果此时用户已经登陆，则需要同步删除后端购物车中的商品

        return JSONResult.ok();
    }
}
