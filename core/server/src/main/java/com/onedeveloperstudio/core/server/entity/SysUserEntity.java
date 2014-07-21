package com.onedeveloperstudio.core.server.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

public class SysUserEntity extends BaseEntity {

  private String userFullName;
  private Integer department;
  private String email;
  private String userName;
  private String password;

  private AuthorityEntity authority;

  @Column(name = "FULL_NAME", nullable = false, length = 100)
  public String getUserFullName() {
    return userFullName;
  }

  public void setUserFullName(String userFullName) {
    this.userFullName = userFullName;
  }

  @Column(nullable = false, length = 15)
  public Integer getDepartment() {
    return department;
  }

  public void setDepartment(Integer department) {
    this.department = department;
  }

  @Column(nullable = false, length = 30)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "NAME", nullable = false, length = 15)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(nullable = false, length = 20)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @OneToOne
  @JoinColumn(name = "authority_id")
  public AuthorityEntity getAuthority() {
    return authority;
  }

  public void setAuthority(AuthorityEntity authority) {
    this.authority = authority;
  }

  @Transient
  public String getAuthorityName() {
    return (authority != null) ? authority.getAuthority() : null;
  }
}
