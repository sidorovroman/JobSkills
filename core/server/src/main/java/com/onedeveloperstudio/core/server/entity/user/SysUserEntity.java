package com.onedeveloperstudio.core.server.entity.user;

import com.onedeveloperstudio.core.common.util.FieldSize;
import com.onedeveloperstudio.core.server.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "useraccount")
@Table(name = "useraccount")
public class SysUserEntity extends BaseEntity {
  private String email;
  private String username;
  private String password;
  private String userFullName;
  private String sex;
  private Date birthday;
  private String phone;
  private String city;
  private String country;
  private String network;

  @Column(nullable = false, length = FieldSize.EMAIL)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(nullable = false, length = FieldSize.PASSWORD_HASH)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "userfullname", length = FieldSize.FIO)
  public String getUserFullName() {
    return userFullName;
  }

  public void setUserFullName(String userFullName) {
    this.userFullName = userFullName;
  }

  @Column(length = FieldSize.SEX)
  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  @Column(length = FieldSize.PHONE)
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Column(length = FieldSize.CITY)
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Column(length = FieldSize.COUNTRY)
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Column(length = FieldSize.NETWORK)
  public String getNetwork() {
    return network;
  }

  public void setNetwork(String network) {
    this.network = network;
  }
}
