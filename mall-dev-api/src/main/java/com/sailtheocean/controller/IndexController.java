package com.sailtheocean.controller;

import com.sailtheocean.enums.YesOrNo;
import com.sailtheocean.pojo.Carousel;
import com.sailtheocean.pojo.Category;
import com.sailtheocean.pojo.vo.CategoryVO;
import com.sailtheocean.pojo.vo.NewItemsVO;
import com.sailtheocean.service.CarouselService;
import com.sailtheocean.service.CategoryService;
import com.sailtheocean.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Main page", tags = {"API interface for main page"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Get main page carousel list", notes = "Get main page carousel list", httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult carousel() {
         List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
         return JSONResult.ok(list);
    }

    @ApiOperation(value = "Get product first-level categories", notes = "Get product first-level categories", httpMethod = "GET")
    @GetMapping("/cats")
    public JSONResult cats() {
        List<Category> list = categoryService.queryAllRootLevelCat();
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "Get product subCategories", notes = "Get product subCategories", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public JSONResult subCat(
            @ApiParam(name = "rootCatId", value = "first-level category id", required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return JSONResult.errorMsg("Category not exist!");
        }

        List<CategoryVO> list = categoryService.getSubCatList(rootCatId);
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "Get the latest 6 products under each first-level category", notes = "Get the latest 6 products under each first-level category", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public JSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "first-level category id", required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return JSONResult.errorMsg("Category not exist!");
        }

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return JSONResult.ok(list);
    }
}
