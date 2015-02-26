package com.onedeveloperstudio.core.common.dto;

public class SysUserDto extends BaseDto {
  private String userFullName;
  private String email;
  private String userName;
  private String password;
  private String sex;
  private Long birthday;
  private String phone;
  private String city;
  private String country;
  private String network;
  private Long rating;
  private String authorityName;

  public String getUserFullName() {
    return userFullName;
  }

  public void setUserFullName(String userFullName) {
    this.userFullName = userFullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAuthorityName() {
    return authorityName;
  }

  public void setAuthorityName(String authorityName) {
    this.authorityName = authorityName;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Long getBirthday() {
    return birthday;
  }

  public void setBirthday(Long birthday) {
    this.birthday = birthday;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getNetwork() {
    return network;
  }

  public void setNetwork(String network) {
    this.network = network;
  }

  public Long getRating() {
    return rating;
  }

  public void setRating(Long rating) {
    this.rating = rating;
  }
}
