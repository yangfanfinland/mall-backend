package com.sailtheocean.controller.center;

import com.sailtheocean.controller.BaseController;
import com.sailtheocean.enums.YesOrNo;
import com.sailtheocean.pojo.OrderItems;
import com.sailtheocean.pojo.Orders;
import com.sailtheocean.pojo.bo.center.OrderItemsCommentBO;
import com.sailtheocean.service.center.MyCommentsService;
import com.sailtheocean.utils.JSONResult;
import com.sailtheocean.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "User center user comment", tags = {"API interface for user center user comment"})
@RestController
@RequestMapping("mycomments")
public class MyCommentsController extends BaseController {

    @Autowired
    private MyCommentsService myCommentsService;

    @ApiOperation(value = "Query order list", notes = "Query order list", httpMethod = "POST")
    @PostMapping("/pending")
    public JSONResult pending(
            @ApiParam(name = "userId", value = "User id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId", value = "Order id", required = true)
            @RequestParam String orderId) {

        // 判断用户和订单是否关联
        JSONResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }
        // 判断该笔订单是否已经评价过，评价过了就不再继续
        Orders myOrder = (Orders)checkResult.getData();
        if (myOrder.getIsComment() == YesOrNo.YES.type) {
            return JSONResult.errorMsg("This order has been commented");
        }

        List<OrderItems> list = myCommentsService.queryPendingComment(orderId);

        return JSONResult.ok(list);
    }


    @ApiOperation(value = "Save comment list", notes = "Save comment list", httpMethod = "POST")
    @PostMapping("/saveList")
    public JSONResult saveList(
            @ApiParam(name = "userId", value = "User id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId", value = "Order id", required = true)
            @RequestParam String orderId,
            @RequestBody List<OrderItemsCommentBO> commentList) {

        System.out.println(commentList);

        // 判断用户和订单是否关联
        JSONResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }
        // 判断评论内容list不能为空
        if (commentList == null || commentList.isEmpty() || commentList.size() == 0) {
            return JSONResult.errorMsg("Comment content cannot be empty!");
        }

        myCommentsService.saveComments(orderId, userId, commentList);
        return JSONResult.ok();
    }

    @ApiOperation(value = "Query my comment", notes = "Query my comment", httpMethod = "POST")
    @PostMapping("/query")
    public JSONResult query(
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

        PagedGridResult grid = myCommentsService.queryMyComments(userId,
                page,
                pageSize);

        return JSONResult.ok(grid);
    }

}
