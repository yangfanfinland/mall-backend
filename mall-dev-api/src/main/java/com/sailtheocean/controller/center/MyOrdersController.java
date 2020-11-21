package com.sailtheocean.controller.center;

import com.sailtheocean.controller.BaseController;
import com.sailtheocean.pojo.vo.OrderStatusCountsVO;
import com.sailtheocean.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "User center my order", tags = {"API interface for user center my order"})
@RestController
@RequestMapping("myorders")
public class MyOrdersController extends BaseController {

    @ApiOperation(value = "Get order status counts summarize", notes = "Get order status counts summarize", httpMethod = "POST")
    @PostMapping("/statusCounts")
    public JSONResult statusCounts(
            @ApiParam(name = "userId", value = "User id", required = true)
            @RequestParam String userId) {

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg(null);
        }

        OrderStatusCountsVO result = myOrdersService.getOrderStatusCounts(userId);

        return JSONResult.ok(result);
    }

    @ApiOperation(value = "Query order list", notes = "Query order list", httpMethod = "POST")
    @PostMapping("/query")
    public JSONResult query(
            @ApiParam(name = "userId", value = "User id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderStatus", value = "Order status", required = false)
            @RequestParam Integer orderStatus,
            @ApiParam(name = "page", value = "Page", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "Page size", required = false)
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg(null);
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = myOrdersService.queryMyOrders(userId,
                orderStatus,
                page,
                pageSize);

        return JSONResult.ok(grid);
    }


    // 商家发货没有后端，所以这个接口仅仅只是用于模拟
    @ApiOperation(value="商家发货", notes="商家发货", httpMethod = "GET")
    @GetMapping("/deliver")
    public JSONResult deliver(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) throws Exception {

        if (StringUtils.isBlank(orderId)) {
            return JSONResult.errorMsg("订单ID不能为空");
        }
        myOrdersService.updateDeliverOrderStatus(orderId);
        return JSONResult.ok();
    }


    @ApiOperation(value="User receive product", notes="User receive product", httpMethod = "POST")
    @PostMapping("/confirmReceive")
    public JSONResult confirmReceive(
            @ApiParam(name = "orderId", value = "Order id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "User id", required = true)
            @RequestParam String userId) throws Exception {

        JSONResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        boolean res = myOrdersService.updateReceiveOrderStatus(orderId);
        if (!res) {
            return JSONResult.errorMsg("Order confirm receive failed");
        }

        return JSONResult.ok();
    }

    @ApiOperation(value="User delete order", notes="User delete order", httpMethod = "POST")
    @PostMapping("/delete")
    public JSONResult delete(
            @ApiParam(name = "orderId", value = "Order id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "User id", required = true)
            @RequestParam String userId) throws Exception {

        JSONResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        boolean res = myOrdersService.deleteOrder(userId, orderId);
        if (!res) {
            return JSONResult.errorMsg("Delete order failed");
        }

        return JSONResult.ok();
    }

    @ApiOperation(value = "Track order", notes = "Track order", httpMethod = "POST")
    @PostMapping("/trend")
    public JSONResult trend(
            @ApiParam(name = "userId", value = "User id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "page", value = "Page", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "Page size", required = false)
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg(null);
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = myOrdersService.getOrdersTrend(userId,
                page,
                pageSize);

        return JSONResult.ok(grid);
    }

}
