package com.sailtheocean.controller;

import com.sailtheocean.pojo.*;
import com.sailtheocean.pojo.vo.CommentLevelCountsVO;
import com.sailtheocean.pojo.vo.ItemInfoVO;
import com.sailtheocean.pojo.vo.ShopcartVO;
import com.sailtheocean.service.ItemService;
import com.sailtheocean.utils.JSONResult;
import com.sailtheocean.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Product details", tags = {"API interface for product details"})
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "Get product details", notes = "Get product details", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JSONResult info(
            @ApiParam(name = "itemId", value = "Product id", required = true)
            @PathVariable String itemId) {

        if (StringUtils.isBlank(itemId)) {
            return JSONResult.errorMsg(null);
        }

        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(itemsImgList);
        itemInfoVO.setItemSpecList(itemsSpecList);
        itemInfoVO.setItemParams(itemsParam);

        return JSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "Get product comment level", notes = "Get product comment level", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public JSONResult commentLevel(
            @ApiParam(name = "itemId", value = "Product id", required = true)
            @RequestParam String itemId) {

        if (StringUtils.isBlank(itemId)) {
            return JSONResult.errorMsg(null);
        }

        CommentLevelCountsVO countsVO = itemService.queryCommentCounts(itemId);
        return JSONResult.ok(countsVO);
    }

    @ApiOperation(value = "Get product comment", notes = "Get product comment", httpMethod = "GET")
    @GetMapping("/comments")
    public JSONResult comments(
            @ApiParam(name = "itemId", value = "Product id", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "Comment level", required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "Page", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "Page size", required = false)
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(itemId)) {
            return JSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.queryPagedComments(itemId, level, page, pageSize);
        return JSONResult.ok(grid);
    }

    @ApiOperation(value = "Get product list", notes = "Get product list", httpMethod = "GET")
    @GetMapping("/search")
    public JSONResult search(
            @ApiParam(name = "keywords", value = "Keywords", required = true)
            @RequestParam String keywords,
            @ApiParam(name = "sort", value = "Sort", required = false)
            @RequestParam(value = "sort", required = false) String sort,
            @ApiParam(name = "page", value = "Page", required = false)
            @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(name = "pageSize", value = "Page size", required = false)
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        if (StringUtils.isBlank(keywords)) {
            return JSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult grid = itemService.searchItems(keywords, sort, page, pageSize);
        return JSONResult.ok(grid);
    }

    @ApiOperation(value = "Get product list by category id", notes = "Get product list by category id", httpMethod = "GET")
    @GetMapping("/catItems")
    public JSONResult catItems(
            @ApiParam(name = "catId", value = "The third category Id", required = true)
            @RequestParam Integer catId,
            @ApiParam(name = "sort", value = "Sort", required = false)
            @RequestParam(value = "sort", required = false) String sort,
            @ApiParam(name = "page", value = "Page", required = false)
            @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(name = "pageSize", value = "Page size", required = false)
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        if (catId == null) {
            return JSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult grid = itemService.searchItems(catId, sort, page, pageSize);
        return JSONResult.ok(grid);
    }

    // 用于用户长时间未登录网站，刷新购物车中的数据（主要是商品价格），类似于 京东 淘宝
    @ApiOperation(value = "Get product list by specification ids", notes = "Get product list by specification ids", httpMethod = "GET")
    @GetMapping("/refresh")
    public JSONResult refresh(
            @ApiParam(name = "itemSpecIds", value = "Specification ids string split with comma", required = true, example = "1001,1003,1005")
            @RequestParam String itemSpecIds) {
        if (StringUtils.isBlank(itemSpecIds)) {
            return JSONResult.ok();
        }

        List<ShopcartVO> list = itemService.queryItemsBySpecIds(itemSpecIds);
        return JSONResult.ok(list);
    }
}
