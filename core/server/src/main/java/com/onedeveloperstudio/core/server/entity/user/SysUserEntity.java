package com.onedeveloperstudio.core.server.entity.user;

import com.onedeveloperstudio.core.server.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "useraccount")
@Table(name = "useraccount")
public class SysUserEntity extends BaseEntity {

  private String email;
  private String username;
  private String password;

  @Column(nullable = false, length = 30)
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

  @Column(nullable = false, length = 20)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
