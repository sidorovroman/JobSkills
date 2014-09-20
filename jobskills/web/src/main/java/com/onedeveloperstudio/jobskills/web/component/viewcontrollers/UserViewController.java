package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.google.gson.Gson;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.User;
import com.onedeveloperstudio.core.server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: y.zakharov
 * Date: 21.08.14
 */
@RequestMapping("/users")
@Controller
public class UserViewController {

  @Autowired
  private SysUserService service;

  private Gson gson;

  @PostConstruct
  private void init(){
    gson = new Gson();
  }

  @RequestMapping("/index")
  public ModelMap mainUserPage(HttpServletRequest request){
    ModelMap modelMap = new ModelMap("/user/index");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User regUser = (User) auth.getPrincipal();
    SysUserDto user = service.loadByEmail(regUser.getUsername());
    modelMap.put("user", user);
    return modelMap;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public void addUser(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    SysUserDto user = service.load(id);
    try{
      response.getOutputStream().write(gson.toJson(user).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String deleteUser(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
    try{
      service.delete(id);
      return "{status : 1}";
    } catch (Exception e){
      return "{status : 0}";
    }
  }
}
