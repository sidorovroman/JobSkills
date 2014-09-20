package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.google.gson.Gson;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.jobskills.common.dto.WayToImproveSkillDto;
import com.onedeveloperstudio.jobskills.server.service.WayToImproveSkillService;
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
@RequestMapping("/wayToImproveSkill")
public class WayToImproveSkillViewController {
  @Autowired
  private WayToImproveSkillService service;

  private Gson gson;

  @PostConstruct
  private void init(){
    gson = new Gson();
    AppObjDict dict = AppObjDict.getInstance();
    service.setAppObj(dict.getAppObj("wayToImproveSkill"));
  }


  @RequestMapping("/list")
  @ResponseBody
  public void getList(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    List<WayToImproveSkillDto> wayToImproveSkills = service.loadAll();
    try{
      response.getOutputStream().write(gson.toJson(wayToImproveSkills).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public void getWayToImproveSkill(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    WayToImproveSkillDto wayToImproveSkill = service.load(id);
    try{
      response.getOutputStream().write(gson.toJson(wayToImproveSkill).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteWayToImproveSkill(@PathVariable Long id, HttpServletRequest request){
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public void addWayToImproveSkill(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    //todo
    WayToImproveSkillDto wayToImproveSkill = new WayToImproveSkillDto();
    wayToImproveSkill = service.insert(wayToImproveSkill);
    try{
      response.getOutputStream().write(gson.toJson(wayToImproveSkill).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public void updateWayToImproveSkill(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    WayToImproveSkillDto wayToImproveSkill = new WayToImproveSkillDto();
    //todo
    wayToImproveSkill = service.update(wayToImproveSkill);
    try{
      response.getOutputStream().write(gson.toJson(wayToImproveSkill).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }
}
