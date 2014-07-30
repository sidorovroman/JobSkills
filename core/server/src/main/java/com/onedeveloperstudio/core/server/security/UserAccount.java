package com.onedeveloperstudio.core.server.security;

import com.onedeveloperstudio.core.server.entity.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Collection;

@Entity
public class UserAccount extends BaseEntity implements UserDetails {

  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;


  public UserAccount(String userName, String password, Collection<? extends GrantedAuthority> authorities) {
    this.username = userName;
    this.password = password;
    this.authorities = authorities;
  }



  @Transient
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  public void setPassword(String password){
    this.password = password;
  }

  @Transient
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Transient
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Transient
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Transient
  @Override
  public boolean isEnabled() {
    return true;
  }
}
