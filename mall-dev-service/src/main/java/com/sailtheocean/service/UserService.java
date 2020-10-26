package com.sailtheocean.service;

import com.sailtheocean.pojo.Users;
import com.sailtheocean.pojo.bo.UserBO;

public interface UserService {

    /**
     * Check user exist
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username);
    /**
     * Create user
     * @param userBo
     * @return
     */
    public Users createUser(UserBO userBo);

    /**
     * Check username and password for user login
     * @param username
     * @param password
     * @return
     */
    public Users queryUserForLogin(String username, String password);
}
