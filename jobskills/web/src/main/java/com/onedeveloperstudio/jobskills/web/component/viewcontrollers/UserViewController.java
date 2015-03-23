package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.User;
import com.onedeveloperstudio.core.server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * User: y.zakharov
 * Date: 21.08.14
 */
@RequestMapping("/user")
@Controller
public class UserViewController {

  @Autowired
  private SysUserService service;

  @RequestMapping("/info")
  @ResponseBody
  public SysUserDto mainUserInfo(HttpServletRequest request) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User regUser = (User) auth.getPrincipal();
    SysUserDto user = service.loadByEmail(regUser.getUsername());
    return user;
  }

  @RequestMapping("/update")
  @ResponseBody
  public SysUserDto updateUserInfo(@RequestBody SysUserDto updatedInfo){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User regUser = (User) auth.getPrincipal();
    SysUserDto user = service.loadByEmail(regUser.getUsername());
    if(user.getId().equals(updatedInfo.getId())){
      user.setCountry(updatedInfo.getCountry());
      user.setPhone(updatedInfo.getPhone());
      user.setUserFullName(updatedInfo.getUserFullName());
      user.setBirthday(updatedInfo.getBirthday());
      user.setCity(updatedInfo.getCity());
      user.setSex(updatedInfo.getSex());
      return service.update(user);
    } else {
      throw new AccessDeniedException("Запрещено обновлять информацию другого пользователя");
    }
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public String handleAllException(Exception ex) {
    ex.printStackTrace();
    return "{\"error\":\"" + ex.getLocalizedMessage() + "\"}";
  }
}
