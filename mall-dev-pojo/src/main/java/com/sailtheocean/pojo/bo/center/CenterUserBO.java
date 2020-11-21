package com.sailtheocean.pojo.bo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

@ApiModel(value="User object BO", description="The entity packaging user data sent from client")
public class CenterUserBO {

    @ApiModelProperty(value="Username", name="username", example="json", required = false)
    private String username;
    @ApiModelProperty(value="Password", name="password", example="123456", required = false)
    private String password;
    @ApiModelProperty(value="Confirm password", name="confirmPassword", example="123456", required = false)
    private String confirmPassword;


    @NotBlank(message = "Nickname cannot be empty")
    @Length(max = 12, message = "Nickname max length is 12")
    @ApiModelProperty(value="Nickname", name="nickname", example="Jack", required = false)
    private String nickname;

    @Length(max = 12, message = "Real name max length is 12")
    @ApiModelProperty(value="Real name", name="realname", example="Jack", required = false)
    private String realname;

    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$", message = "Invalid mobile number")
    @ApiModelProperty(value="Mobile", name="mobile", example="13999999999", required = false)
    private String mobile;

    @Email
    @ApiModelProperty(value="Email", name="email", example="imooc@imooc.com", required = false)
    private String email;

    @Min(value = 0, message = "Invalid sex")
    @Max(value = 2, message = "Invalid sex")
    @ApiModelProperty(value="Sex", name="sex", example="0:Women 1:Man 2:Secret", required = false)
    private Integer sex;
    @ApiModelProperty(value="Birthday", name="birthday", example="1900-01-01", required = false)
    private Date birthday;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "CenterUserBO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                '}';
    }
}