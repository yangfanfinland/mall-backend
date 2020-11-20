package com.sailtheocean.service;

import com.sailtheocean.pojo.UserAddress;
import com.sailtheocean.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {

    /**
     * Query all delivery address base on user id
     * @param userId
     * @return
     */
    List<UserAddress> queryAll(String userId);

    /**
     * Add new delivery address
     * @param addressBO
     */
    void addNewUserAddress(AddressBO addressBO);

    /**
     * Modify delivery address
     * @param addressBO
     */
    void updateUserAddress(AddressBO addressBO);

    /**
     * Delete delivery address base on user id and address id
     * @param userId
     * @param addressId
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * Modify default delivery address
     * @param userId
     * @param addressId
     */
    void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * Query specific address base on user id and address id
     * @param userId
     * @param addressId
     * @return
     */
    UserAddress queryUserAddres(String userId, String addressId);
}
