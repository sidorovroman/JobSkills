package com.onedeveloperstudio.core.server.security;

import com.onedeveloperstudio.core.common.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * User: y.zakharov
 * Date: 23.01.15
 */
@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Authentication auth = super.authenticate(authentication);
    User user = (User) auth.getPrincipal();
    if(user.getDetails().getNetwork()!=null){
      return null;
    } else {
      return auth;
    }
  }

  @Autowired
  @Qualifier("userDetailsService")
  @Override
  public void setUserDetailsService(UserDetailsService userDetailsService) {
    super.setUserDetailsService(userDetailsService);
  }
}
