package com.sailtheocean.mapper;

import com.sailtheocean.my.mapper.MyMapper;
import com.sailtheocean.pojo.ItemsComments;
import com.sailtheocean.pojo.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {

    void saveComments(Map<String, Object> map);

    List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}