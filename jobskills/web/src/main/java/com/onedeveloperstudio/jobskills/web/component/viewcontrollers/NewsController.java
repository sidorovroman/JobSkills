package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.User;
import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.jobskills.common.dto.NewsDto;
import com.onedeveloperstudio.jobskills.server.service.NewsService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 11.09.14
 */
@Controller
@RequestMapping("/news")
public class NewsController {
  @Autowired
  private NewsService service;
  @Autowired
  private SysUserService sysUserService;

  private JSONSerializer serializer = new JSONSerializer();
  private JSONDeserializer<NewsDto> deserializer = new JSONDeserializer<>();
  private JSONDeserializer<CommentaryDto> commentDeserializer = new JSONDeserializer<>();

  @PostConstruct
  private void init() {
    AppObjDict dict = AppObjDict.getInstance();
    service.setAppObj(dict.getAppObj("news"));
  }


  @RequestMapping(value = "/list")
  @ResponseBody
  public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
    List<NewsDto> newss = service.loadAll();
    response.getOutputStream().write(serializer.serialize(newss).getBytes());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public void getNews(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
    NewsDto news = service.load(id);
    response.getOutputStream().write(serializer.deepSerialize(news).getBytes());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String deleteNews(@PathVariable Long id, HttpServletRequest request) {
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public void addNews(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User regUser = (User) auth.getPrincipal();
    SysUserDto user = sysUserService.loadByEmail(regUser.getUsername());
    NewsDto news = deserializer.deserialize(request.getReader(), NewsDto.class);
    news.setAddDate(new Date().getTime());
    news.setAuthor(user);
    news = service.insert(news);
    response.getOutputStream().write(serializer.deepSerialize(news).getBytes());
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public void updateNews(HttpServletRequest request, HttpServletResponse response) throws Exception {
    NewsDto news = deserializer.deserialize(request.getReader(), NewsDto.class);
    //todo
    news = service.update(news);
    response.getOutputStream().write(serializer.deepSerialize(news).getBytes());
  }

  @RequestMapping(value = "up/{id}")
  @ResponseBody
  public String up(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
    service.vote(id, VoteState.UP);
    return "{status : 1}";
  }


  @RequestMapping(value = "down/{id}")
  @ResponseBody
  public String down(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
    service.vote(id, VoteState.DOWN);
    return "{status : 1}";
  }

  @RequestMapping(value = "/comment/{id}", method = RequestMethod.POST)
  public void comment(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
    CommentaryDto comment = commentDeserializer.deserialize(request.getReader());
    service.comment(id, comment);
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public String handleAllException(Exception ex) {
    ex.printStackTrace();
    return "{error:" + ex.getLocalizedMessage() + "}";
  }
}
