package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.User;
import com.onedeveloperstudio.core.server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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


  @RequestMapping("/index")
  public ModelMap mainUserPage(HttpServletRequest request){
    ModelMap modelMap = new ModelMap("/user/index");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User regUser = (User) auth.getPrincipal();
    SysUserDto user = service.loadByEmail(regUser.getUsername());
    modelMap.put("user", user);
    return modelMap;
  }
}
