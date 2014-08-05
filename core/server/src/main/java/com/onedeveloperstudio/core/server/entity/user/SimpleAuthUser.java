package com.onedeveloperstudio.core.server.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: y.zakharov
 * Date: 23.07.14
 */
@Entity
@Table(name = "simple_auth_user")
public class SimpleAuthUser extends AuthUser {
  @Column(name = "password", length = 40, nullable = false)
  private String password;

  @Column(name = "uuid", length = 36, nullable = false)
  private String uuid;

  @Override
  public String getPassword() {
    return password;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}
