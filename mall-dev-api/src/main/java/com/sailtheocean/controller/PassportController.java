package com.sailtheocean.controller;

import com.sailtheocean.pojo.Users;
import com.sailtheocean.pojo.bo.UserBO;
import com.sailtheocean.service.UserService;
import com.sailtheocean.utils.CookieUtils;
import com.sailtheocean.utils.JSONResult;
import com.sailtheocean.utils.JsonUtils;
import com.sailtheocean.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "Register and Login", tags = {"API interface for register and login"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Username exists", notes = "Username exists or not", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam String username) {
        // Username cannot be empty
        if (StringUtils.isBlank(username)) {
            return JSONResult.errorMsg("Username cannot be empty!");
        }
        // Check username is exist
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return JSONResult.errorMsg("Username exists!");
        }
        return JSONResult.ok();
    }

    @ApiOperation(value = "User register", notes = "User register", httpMethod = "POST")
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // Username and password cannot be empty
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPwd)) {
            return JSONResult.errorMsg("Username and password cannot be empty!");
        }
        // Check user exist
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return JSONResult.errorMsg("Username exists!");
        }
        // Password length not less than 6
        if (password.length() < 6) {
            return JSONResult.errorMsg("Password length cannot less than 6!");
        }
        // Check password and confirmPassword match
        if (!password.equals(confirmPwd)) {
            return JSONResult.errorMsg("Passwords not match!");
        }

        Users userResult = userService.createUser(userBO);

        userResult = setNullProperty(userResult);

        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        return JSONResult.ok();
    }

    @ApiOperation(value = "User login", notes = "User login", httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        // Username and password cannot be empty
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("Username and password cannot be empty!");
        }

        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));

        if (userResult == null) {
            return JSONResult.errorMsg("Username or password not correct!");
        }

        userResult = setNullProperty(userResult);

        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        // TODO 生成用户token，存入redis会话
        // TODO 同步购物车数据

        return JSONResult.ok(userResult);
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

    @ApiOperation(value = "User logout", notes = "User logout", httpMethod = "POST")
    @PostMapping("/logout")
    public  JSONResult logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        // 清除用户的相关信息的cookie
        CookieUtils.deleteCookie(request, response, "user");
        return JSONResult.ok();
    }
}
