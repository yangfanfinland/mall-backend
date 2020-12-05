package com.sailtheocean.service;

import com.sailtheocean.pojo.Category;
import com.sailtheocean.pojo.vo.CategoryVO;
import com.sailtheocean.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {
    /**
     * Query all first-level categories
     * @return
     */
    List<Category> queryAllRootLevelCat();

    /**
     * Query subCategories base on first-level category id
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * Query the latest 6 pieces of data under first-level categories in Main page
     * @param rootCatId
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);

    List<Category> getParentCatList(Integer catId);
}
