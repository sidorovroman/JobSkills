//package com.onedeveloperstudio.core.server.security;
//
//import com.onedeveloperstudio.core.common.Authority;
//import com.onedeveloperstudio.core.common.dto.SysUserDto;
//import com.onedeveloperstudio.core.common.handler.SysUserBaseService;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
//@Component("userDetailsService")
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//  @Autowired
//  private BeanFactory beanFactory;
//
//  @Override
//  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//    SysUserBaseService userHandler = beanFactory.getBean(SysUserBaseService.class);
//    SysUserDto userDto = userHandler.loadByName(userName);
//
//    return new UserAccount(userDto.getUserName(), userDto.getPassword(),
//            Collections.singletonList(new Authority(userDto.getAuthorityName())));
//  }
//
//}
