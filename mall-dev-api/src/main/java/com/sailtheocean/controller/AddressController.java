package com.sailtheocean.controller;

import com.sailtheocean.pojo.UserAddress;
import com.sailtheocean.pojo.bo.AddressBO;
import com.sailtheocean.service.AddressService;
import com.sailtheocean.utils.JSONResult;
import com.sailtheocean.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(value = "Delivery address", tags = {"API interface for delivery address"})
@RequestMapping("address")
@RestController
public class AddressController {

    /**
     * 用户在确认订单页面，可以针对收货地址做如下操作：
     * 1. 查询用户的所有收货地址列表
     * 2. 新增收货地址
     * 3. 删除收货地址
     * 4. 修改收货地址
     * 5. 设置默认地址
     */

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Get delivery address list base on user id", notes = "Get delivery address list base on user id", httpMethod = "POST")
    @PostMapping("/list")
    public JSONResult list(
            @RequestParam String userId) {

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("");
        }

        List<UserAddress> list = addressService.queryAll(userId);
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "Add new delivery address", notes = "Add new delivery address", httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(@RequestBody AddressBO addressBO) {

        JSONResult checkRes = checkAddress(addressBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }

        addressService.addNewUserAddress(addressBO);

        return JSONResult.ok();
    }
    private JSONResult checkAddress(AddressBO addressBO) {
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return JSONResult.errorMsg("Receiver cannot be empty");
        }
        if (receiver.length() > 12) {
            return JSONResult.errorMsg("收货人姓名不能太长");
        }

        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return JSONResult.errorMsg("收货人手机号不能为空");
        }
        if (mobile.length() != 11) {
            return JSONResult.errorMsg("收货人手机号长度不正确");
        }
        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!isMobileOk) {
            return JSONResult.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();
        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(district) ||
                StringUtils.isBlank(detail)) {
            return JSONResult.errorMsg("Receive address cannot be empty");
        }

        return JSONResult.ok();
    }

    @ApiOperation(value = "Modify delivery address", notes = "Modify delivery address", httpMethod = "POST")
    @PostMapping("/update")
    public JSONResult update(@RequestBody AddressBO addressBO) {

        if (StringUtils.isBlank(addressBO.getAddressId())) {
            return JSONResult.errorMsg("Modify address failed：addressId cannot be empty");
        }

        JSONResult checkRes = checkAddress(addressBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }

        addressService.updateUserAddress(addressBO);

        return JSONResult.ok();
    }

    @ApiOperation(value = "Delete delivery address", notes = "Delete delivery address", httpMethod = "POST")
    @PostMapping("/delete")
    public JSONResult delete(
            @RequestParam String userId,
            @RequestParam String addressId) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)) {
            return JSONResult.errorMsg("");
        }

        addressService.deleteUserAddress(userId, addressId);
        return JSONResult.ok();
    }

    @ApiOperation(value = "Set default delivery address", notes = "Set default delivery address", httpMethod = "POST")
    @PostMapping("/setDefalut")
    public JSONResult setDefalut(
            @RequestParam String userId,
            @RequestParam String addressId) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)) {
            return JSONResult.errorMsg("");
        }

        addressService.updateUserAddressToBeDefault(userId, addressId);
        return JSONResult.ok();
    }
}
