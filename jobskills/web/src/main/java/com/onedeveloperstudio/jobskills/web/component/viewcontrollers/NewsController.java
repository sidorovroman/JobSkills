package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.jobskills.common.dto.NewsDto;
import com.onedeveloperstudio.jobskills.server.service.NewsService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/news")
public class NewsController {
  @Autowired
  private NewsService service;

  private JSONSerializer serializer = new JSONSerializer();
  private JSONDeserializer<NewsDto> deserializer = new JSONDeserializer<>();
  private JSONDeserializer<CommentaryDto> commentDeserializer = new JSONDeserializer<>();
  @PostConstruct
  private void init() {
    AppObjDict dict = AppObjDict.getInstance();
    service.setAppObj(dict.getAppObj("news"));
  }


  @RequestMapping("/list")
  @ResponseBody
  public void getList(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    List<NewsDto> newss = service.loadAll();
    try {
      response.getOutputStream().write(serializer.serialize(newss).getBytes());
    } catch (Exception e) {
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public void getNews(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    NewsDto news = service.load(id);
    try {
      response.getOutputStream().write(serializer.deepSerialize(news).getBytes());
    } catch (Exception e) {
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String deleteNews(@PathVariable Long id, HttpServletRequest request) {
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public void addNews(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    try {
      NewsDto news = deserializer.deserialize(request.getReader(), NewsDto.class);
      //TODO проверить после появления формы
      news = service.insert(news);
      response.getOutputStream().write(serializer.deepSerialize(news).getBytes());
    } catch (Exception e) {
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public void updateNews(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    NewsDto news = new NewsDto();
    //todo
    news = service.update(news);
    try {
      response.getOutputStream().write(serializer.deepSerialize(news).getBytes());
    } catch (Exception e) {
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "up/{id}")
  public void up(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
    service.vote(id, VoteState.UP);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
  }


  @RequestMapping(value = "down/{id}")
  public String down(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
    service.vote(id, VoteState.DOWN);
    response.setContentType("application/json");
    return "{status : 1}";
  }

  @RequestMapping(value = "/comment/{id}", method = RequestMethod.POST)
  public void comment(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
    try {
      CommentaryDto comment = commentDeserializer.deserialize(request.getReader());
      service.comment(id, comment);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
