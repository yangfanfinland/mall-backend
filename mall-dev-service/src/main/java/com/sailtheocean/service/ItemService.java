package com.sailtheocean.service;

import com.sailtheocean.pojo.Items;
import com.sailtheocean.pojo.ItemsImg;
import com.sailtheocean.pojo.ItemsParam;
import com.sailtheocean.pojo.ItemsSpec;
import com.sailtheocean.pojo.vo.CommentLevelCountsVO;
import com.sailtheocean.pojo.vo.ShopcartVO;
import com.sailtheocean.utils.PagedGridResult;

import java.util.List;

public interface ItemService {
    /**
     * Query product details by id
     * @param itemId
     * @return
     */
    Items queryItemById(String itemId);

    /**
     * Query product image list by id
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * Query product specification by id
     * @param itemId
     * @return
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * Query product parameters by id
     * @param itemId
     * @return
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * Query product comments by id
     * @param itemId
     * @return
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * Query product comments by id and comment level
     * @param itemId
     * @param level
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * Query product list by keywords
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * Query product list by category Id
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * Query products in shopping cart by specification (Used to refresh products in shopping cart)
     * @param specIds
     * @return
     */
    List<ShopcartVO> queryItemsBySpecIds(String specIds);

    /**
     * 根据商品规格id获取规格对象的具体信息
     * @param specId
     * @return
     */
    ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据商品id获得商品图片主图url
     * @param itemId
     * @return
     */
    String queryItemMainImgById(String itemId);

    /**
     * 减少库存
     * @param specId
     * @param buyCounts
     */
    void decreaseItemSpecStock(String specId, int buyCounts);
}
