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
    public List<Category> queryAllRootLevelCat();

    /**
     * Query subCategories base on first-level category id
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * Query the latest 6 pieces of data under first-level categories in Main page
     * @param rootCatId
     * @return
     */
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
