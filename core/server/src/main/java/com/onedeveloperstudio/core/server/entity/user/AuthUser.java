package com.onedeveloperstudio.core.server.entity.user;

import com.onedeveloperstudio.core.common.AuthorityType;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.common.Authority;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

/**
 * User: y.zakharov
 * Date: 23.07.14
 */
@javax.persistence.Entity
@Table(name = "auth_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class AuthUser extends BaseEntity implements Serializable, UserDetails {

  @Column(name = "identification_name", length = 64, nullable = false)
  private String identificationName;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private AuthorityType type;

  @Column(name = "binary_authorities", nullable = false)
  private Long binaryAuthorities;

  @Column(name = "enabled", nullable = false, columnDefinition = "tinyint")
  private Boolean enabled;

  @Transient
  private Set<Authority> authorities;

  @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
  @Cascade({CascadeType.ALL})
  @JoinColumn(name="user_id")
  private User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    authorities = EnumSet.noneOf(Authority.class);
    for (Authority authority : Authority.values())
      if ((binaryAuthorities & (1 << authority.ordinal())) != 0)
        authorities.add(authority);
    return authorities;
  }
  public void setAuthority(Set<Authority> authorities) {
    binaryAuthorities = 0L;
    for (Authority authority : authorities)
      binaryAuthorities |= 1 << authority.ordinal();
  }
  @Override
  public String getPassword() {
    return type.name();
  }
  @Override
  public String getUsername() {
    return identificationName;
  }
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  public String getIdentificationName() {
    return identificationName;
  }

  public void setIdentificationName(String identificationName) {
    this.identificationName = identificationName;
  }

  public AuthorityType getType() {
    return type;
  }

  public void setType(AuthorityType type) {
    this.type = type;
  }

  public Long getBinaryAuthorities() {
    return binaryAuthorities;
  }

  public void setBinaryAuthorities(Long binaryAuthorities) {
    this.binaryAuthorities = binaryAuthorities;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
