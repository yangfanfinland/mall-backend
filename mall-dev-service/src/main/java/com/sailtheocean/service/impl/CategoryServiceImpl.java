package com.sailtheocean.service.impl;

import com.sailtheocean.mapper.CategoryMapper;
import com.sailtheocean.mapper.CategoryMapperCustom;
import com.sailtheocean.pojo.Category;
import com.sailtheocean.pojo.vo.CategoryVO;
import com.sailtheocean.pojo.vo.NewItemsVO;
import com.sailtheocean.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);

        List<Category> result = categoryMapper.selectByExample(example);

        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {
        Map<String, Object> map = new HashMap<>();
        map.put("rootCatId", rootCatId);
        return categoryMapperCustom.getSixNewItemsLazy(map);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> getParentCatList(Integer catId) {
        List<Category> parentCatList = new ArrayList<>();
        Category cat = categoryMapper.selectByPrimaryKey(catId);
        parentCatList.add(cat);

        while (cat.getFatherId() != 0) {
            cat = categoryMapper.selectByPrimaryKey(cat.getFatherId());
            parentCatList.add(cat);
        }
        return parentCatList;
    }
}
