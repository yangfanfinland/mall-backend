package com.sailtheocean.mapper;

import com.sailtheocean.pojo.vo.CategoryVO;
import com.sailtheocean.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {
    List<CategoryVO> getSubCatList(Integer rootCatId);
    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap")Map<String, Object> map);
}