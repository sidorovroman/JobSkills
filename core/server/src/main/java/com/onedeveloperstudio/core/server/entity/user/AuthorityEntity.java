package com.onedeveloperstudio.core.server.entity.user;

import com.onedeveloperstudio.core.server.entity.BaseEntity;

public class AuthorityEntity extends BaseEntity {

  private String authority;

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }
}
