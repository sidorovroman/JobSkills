package com.onedeveloperstudio.core.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class AuthorizedUserDetailsService implements UserDetailsService {

  @Autowired
  private UserManager userManager;

  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    return userManager.getUser(username);
  }
}
