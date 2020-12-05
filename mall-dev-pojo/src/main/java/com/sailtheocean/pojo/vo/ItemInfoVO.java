package com.sailtheocean.pojo.vo;

import com.sailtheocean.pojo.*;

import java.util.List;

/**
 * Product details VO
 */
public class ItemInfoVO {
    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public List<ItemsImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemsImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public List<ItemsSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemsSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }

    public ItemsParam getItemParams() {
        return itemParams;
    }

    public void setItemParams(ItemsParam itemParams) {
        this.itemParams = itemParams;
    }

    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;

    public List<Category> getParentCatList() {
        return parentCatList;
    }

    public void setParentCatList(List<Category> parentCatList) {
        this.parentCatList = parentCatList;
    }

    private ItemsParam itemParams;
    private List<Category> parentCatList;
}
