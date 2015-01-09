package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.common.dto.WayToImproveSkillDto;
import com.onedeveloperstudio.jobskills.server.service.WayToImproveSkillService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

  private JSONSerializer serializer = new JSONSerializer();
  private JSONDeserializer<CommentaryDto> commentDeserializer = new JSONDeserializer<>();

  @PostConstruct
  private void init() {
    AppObjDict dict = AppObjDict.getInstance();
    service.setAppObj(dict.getAppObj("wayToImproveSkill"));
  }


  @RequestMapping("/list")
  @ResponseBody
  public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    List<WayToImproveSkillDto> wayToImproveSkills = service.loadAll();
    response.getOutputStream().write(serializer.deepSerialize(wayToImproveSkills).getBytes());
  }

  @RequestMapping("/{skillId}/list")
  @ResponseBody
  public void getListBySkill(@PathVariable Long skillId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    List<WayToImproveSkillDto> ways = service.loadAllbySkill(skillId);
    response.getOutputStream().write(serializer.deepSerialize(ways).getBytes());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public void getWayToImproveSkill(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    WayToImproveSkillDto wayToImproveSkill = service.load(id);
    response.getOutputStream().write(serializer.deepSerialize(wayToImproveSkill).getBytes());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String deleteWayToImproveSkill(@PathVariable Long id, HttpServletRequest request) {
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public void addWayToImproveSkill(HttpServletRequest request, HttpServletResponse response) throws Exception {
    //todo
    WayToImproveSkillDto wayToImproveSkill = new WayToImproveSkillDto();
    wayToImproveSkill = service.insert(wayToImproveSkill);
    response.getOutputStream().write(serializer.deepSerialize(wayToImproveSkill).getBytes());
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public WayToImproveSkillDto updateWayToImproveSkill(HttpServletRequest request, HttpServletResponse response) throws Exception {
    WayToImproveSkillDto wayToImproveSkill = new WayToImproveSkillDto();
    //todo
    wayToImproveSkill = service.update(wayToImproveSkill);
    return wayToImproveSkill;
  }

  @RequestMapping(value = "/comment/{id}", method = RequestMethod.POST)
  public void comment(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
    CommentaryDto comment = commentDeserializer.deserialize(request.getReader());
    service.comment(id, comment);
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public String handleAllException(Exception ex) {
    return "{error:" + ex.getLocalizedMessage()+"}";
  }
}
