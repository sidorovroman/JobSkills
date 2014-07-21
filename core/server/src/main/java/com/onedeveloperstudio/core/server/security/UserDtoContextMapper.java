package com.onedeveloperstudio.core.server.security;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import java.util.Collection;

/**
 * User: y.zakharov
 * Date: 18.12.13
 */
public class UserDtoContextMapper extends LdapUserDetailsMapper {
  @Override
  public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
                                        Collection<? extends GrantedAuthority> authorities) {
    LdapUserDetails userDetails = (LdapUserDetails) super.mapUserFromContext(ctx, username, authorities);
    return userDetails;
  }
}
