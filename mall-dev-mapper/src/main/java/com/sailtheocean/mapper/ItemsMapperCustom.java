package com.sailtheocean.mapper;

import com.sailtheocean.pojo.vo.ItemCommentVO;
import com.sailtheocean.pojo.vo.SearchItemsVO;
import com.sailtheocean.pojo.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);

    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);

    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);

    List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);

    int decreaseItemSpecStock(@Param("specId") String specId, @Param("pendingCounts") int pendingCounts);
}