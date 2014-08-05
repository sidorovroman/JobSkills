package com.onedeveloperstudio.core.server.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: y.zakharov
 * Date: 23.07.14
 */
@Entity
@Table(name = "facebook_auth_user")
public class FacebookAuthUser extends AuthUser {
  @Column(name = "first_name", length = 32)
  private String firstName;

  @Column(name = "last_name", length = 32)
  private String lastName;

  @Column(name = "email", length = 64)
  private String email;

  @Column(name = "token", length = 128)
  private String token;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
