package com.onedeveloperstudio.core.server.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

  private String authorityName;

  public Authority(String authorityName) {
    this.authorityName = authorityName;
  }

  @Override
  public String getAuthority() {
    return authorityName;
  }
}
