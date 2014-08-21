package com.onedeveloperstudio.core.server.security;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.User;
import com.onedeveloperstudio.core.server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class AuthorizedUserDetailsService implements UserDetailsService {

  @Autowired
  private SysUserService userService;

  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    SysUserDto sysUser = userService.loadByUsername(username);
    return new User(sysUser.getUserName(), sysUser.getPassword(), "ROLE_USER", sysUser);
  }
}
