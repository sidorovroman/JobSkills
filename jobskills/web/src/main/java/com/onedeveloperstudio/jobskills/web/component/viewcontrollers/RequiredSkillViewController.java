package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.jobskills.common.dto.NewsDto;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.server.service.RequiredSkillService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

  private JSONSerializer serializer = new JSONSerializer();
  private JSONDeserializer<RequiredSkillDto> deserializer = new JSONDeserializer<>();
  private JSONDeserializer<CommentaryDto> commentDeserializer = new JSONDeserializer<>();

  @RequestMapping("/list")
  @ResponseBody
  public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<RequiredSkillDto> requiredSkills = service.loadAll();
    response.getOutputStream().write(serializer.deepSerialize(requiredSkills).getBytes());
  }

  @RequestMapping("/{jobId}/list")
  @ResponseBody
  public void getListByJob(@PathVariable Long jobId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<RequiredSkillDto> requiredSkills = service.loadAllbyJob(jobId);
    response.getOutputStream().write(serializer.deepSerialize(requiredSkills).getBytes());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public void getRequiredSkill(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
    RequiredSkillDto requiredSkill = service.load(id);
    response.getOutputStream().write(serializer.deepSerialize(requiredSkill).getBytes());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String deleteRequiredSkill(@PathVariable Long id, HttpServletRequest request) {
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public void addRequiredSkill(HttpServletRequest request, HttpServletResponse response) throws Exception {
    RequiredSkillDto requiredSkill = deserializer.deserialize(request.getReader(), RequiredSkillDto.class);
    requiredSkill = service.insert(requiredSkill);
    response.getOutputStream().write(serializer.deepSerialize(requiredSkill).getBytes());
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public void updateRequiredSkill(HttpServletRequest request, HttpServletResponse response) throws Exception {
    RequiredSkillDto requiredSkill = new RequiredSkillDto();
    //todo
    requiredSkill = service.update(requiredSkill);
    response.getOutputStream().write(serializer.deepSerialize(requiredSkill).getBytes());
  }

  @RequestMapping(value = "/comment/{id}", method = RequestMethod.POST)
  public void comment(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
    CommentaryDto comment = commentDeserializer.deserialize(request.getReader());
    service.comment(id, comment);
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public String handleAllException(Exception ex) {
    return "{error:" + ex.getLocalizedMessage() + "}";
  }
}
