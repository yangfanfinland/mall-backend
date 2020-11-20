package com.sailtheocean.service;

import com.sailtheocean.pojo.OrderStatus;
import com.sailtheocean.pojo.bo.SubmitOrderBO;
import com.sailtheocean.pojo.vo.OrderVO;

public interface OrderService {

    /**
     * 用于创建订单相关信息
     * @param submitOrderBO
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     * @param orderId
     * @return
     */
    OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 关闭超时未支付订单
     */
    public void closeOrder();
}
