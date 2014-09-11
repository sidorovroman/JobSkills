package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.google.gson.Gson;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.server.service.RequiredSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 11.09.14
 */
@Controller
@RequestMapping("/requiredSkill")
public class RequiredSkillViewRequest {
  @Autowired
  private RequiredSkillService service;

  private Gson gson;

  @PostConstruct
  private void init(){
    gson = new Gson();
  }


  @RequestMapping("/list")
  @ResponseBody
  public void getList(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    List<RequiredSkillDto> requiredSkills = service.loadAll();
    try{
      response.getOutputStream().write(gson.toJson(requiredSkills).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public void getRequiredSkill(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    RequiredSkillDto requiredSkill = service.load(id);
    try{
      response.getOutputStream().write(gson.toJson(requiredSkill).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteRequiredSkill(@PathVariable Long id, HttpServletRequest request){
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public void addRequiredSkill(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    //todo
    RequiredSkillDto requiredSkill = new RequiredSkillDto();
    requiredSkill = service.insert(requiredSkill);
    try{
      response.getOutputStream().write(gson.toJson(requiredSkill).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public void updateRequiredSkill(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    RequiredSkillDto requiredSkill = new RequiredSkillDto();
    //todo
    requiredSkill = service.update(requiredSkill);
    try{
      response.getOutputStream().write(gson.toJson(requiredSkill).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }
}
