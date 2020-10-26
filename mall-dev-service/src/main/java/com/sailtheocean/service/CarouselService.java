package com.sailtheocean.service;

import com.sailtheocean.pojo.Carousel;

import java.util.List;

public interface CarouselService {

    /**
     * Query all carousel list
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(Integer isShow);
}
