package com.onedeveloperstudio.core.server.service.auth;

import com.onedeveloperstudio.core.server.entity.user.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * User: y.zakharov
 * Date: 05.08.14
 */
public class CustomUserDetailsManager implements UserDetailsService {
  /*@Resource
  private AuthenticationDAO authenticationDAO;
*/
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return/* authenticationDAO.findAuthUser(username)*/ new AuthUser();
  }
}