package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.server.service.RequiredSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 11.09.14
 */
@Controller
@RequestMapping("/requiredSkill")
public class RequiredSkillViewController {
  @Autowired
  private RequiredSkillService service;

  @RequestMapping("/list")
  @ResponseBody
  public List<RequiredSkillDto> getList(@RequestBody(required = false) Pageable pageable){
    List<RequiredSkillDto> requiredSkills = service.loadAll();
    return requiredSkills;
  }

  @RequestMapping("/{jobId}/list")
  @ResponseBody
  public List<RequiredSkillDto> getListByJob(@PathVariable Long jobId, @RequestBody(required = false) Pageable pageable){
    List<RequiredSkillDto> requiredSkills = service.loadAllbyJob(jobId, pageable);
    return requiredSkills;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public RequiredSkillDto getRequiredSkill(@PathVariable Long id){
    RequiredSkillDto requiredSkill = service.load(id);
    return requiredSkill;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String deleteRequiredSkill(@PathVariable Long id) {
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public RequiredSkillDto addRequiredSkill(@RequestBody RequiredSkillDto requiredSkill){
    if(requiredSkill.getWays().size()==1 && requiredSkill.getWays().get(0).getCaption() == null){
      requiredSkill.setWays(null);
    }
    requiredSkill.setAddDate(new Date().getTime());
    requiredSkill = service.insert(requiredSkill);
    return requiredSkill;
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public RequiredSkillDto updateRequiredSkill(@RequestBody RequiredSkillDto requiredSkill){
    requiredSkill = service.update(requiredSkill);
    return requiredSkill;
  }

  @RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public CommentaryDto comment(@PathVariable Long id, @RequestBody CommentaryDto comment){
    return service.comment(id, comment);
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
      return "{\"error\": \"Необходима авторизация\"}";
    }
    ex.printStackTrace();
    return "{\"error\":\"" + ex.getLocalizedMessage() + "\"}";
  }
}
