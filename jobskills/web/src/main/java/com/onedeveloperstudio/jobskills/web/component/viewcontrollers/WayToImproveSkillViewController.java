package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.common.dto.WayToImproveSkillDto;
import com.onedeveloperstudio.jobskills.server.service.WayToImproveSkillService;
import flexjson.JSONDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

  @PostConstruct
  private void init() {
    AppObjDict dict = AppObjDict.getInstance();
    service.setAppObj(dict.getAppObj("wayToImproveSkill"));
  }


  @RequestMapping("/list")
  @ResponseBody
  public List<WayToImproveSkillDto> getList(){
    List<WayToImproveSkillDto> wayToImproveSkills = service.loadAll();
    return wayToImproveSkills;
  }

  @RequestMapping("/{skillId}/list")
  @ResponseBody
  public List<WayToImproveSkillDto> getListBySkill(@PathVariable Long skillId){
    List<WayToImproveSkillDto> ways = service.loadAllbySkill(skillId);
    return ways;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public WayToImproveSkillDto getWayToImproveSkill(@PathVariable Long id) {
    WayToImproveSkillDto wayToImproveSkill = service.load(id);
    return wayToImproveSkill;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String deleteWayToImproveSkill(@PathVariable Long id) {
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public WayToImproveSkillDto addWayToImproveSkill(@RequestBody WayToImproveSkillDto wayToImproveSkill){
    wayToImproveSkill = service.insert(wayToImproveSkill);
    return wayToImproveSkill;
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public WayToImproveSkillDto updateWayToImproveSkill(@RequestBody WayToImproveSkillDto wayToImproveSkill){
    WayToImproveSkillDto existedWTIS = service.load(wayToImproveSkill.getId());
    if(wayToImproveSkill.getSkills() == null){
      wayToImproveSkill.setSkills(new ArrayList<>(existedWTIS.getSkills()));
    } else {
      if(!existedWTIS.getSkills().contains(wayToImproveSkill.getSkills().get(0))){
        wayToImproveSkill.getSkills().addAll(existedWTIS.getSkills());
      }
    }
    wayToImproveSkill = service.update(wayToImproveSkill);
    return wayToImproveSkill;
  }

  @RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
  public void comment(@PathVariable Long id, @RequestBody CommentaryDto comment){
    service.comment(id, comment);
  }

  @RequestMapping(value = "up/{id}")
  @ResponseBody
  public Integer up(@PathVariable Long id) {
    return service.vote(id, VoteState.UP);
  }


  @RequestMapping(value = "down/{id}")
  @ResponseBody
  public Integer down(@PathVariable Long id) {
    return service.vote(id, VoteState.DOWN);
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public String handleAllException(Exception ex) {
    if(ex instanceof AccessDeniedException){
      System.out.println(ex.getLocalizedMessage());
      return "{'error': 'Необходима авторизация'}";
    }
    ex.printStackTrace();
    return "{'error':'" + ex.getLocalizedMessage() + "'}";
  }
}
