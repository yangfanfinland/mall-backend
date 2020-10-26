package com.sailtheocean.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User object BO", description = "The entity packaging user data sent from client")
public class UserBO {
    @ApiModelProperty(value = "Username", name = "username", example = "fan", required = true)
    private String username;
    @ApiModelProperty(value = "Password", name = "password", example = "password", required = true)
    private String password;
    @ApiModelProperty(value = "Confirm password", name = "confirmPassword", example = "password", required = false)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
