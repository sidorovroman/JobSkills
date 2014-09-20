package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.google.gson.Gson;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.jobskills.common.dto.NewsDto;
import com.onedeveloperstudio.jobskills.server.service.NewsService;
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
@RequestMapping("/news")
public class NewsController {
  @Autowired
  private NewsService service;

  private Gson gson;

  @PostConstruct
  private void init(){
    gson = new Gson();
    AppObjDict dict = AppObjDict.getInstance();
    service.setAppObj(dict.getAppObj("news"));
  }


  @RequestMapping("/list")
  @ResponseBody
  public void getList(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    List<NewsDto> newss = service.loadAll();
    try{
      response.getOutputStream().write(gson.toJson(newss).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public void getNews(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    NewsDto news = service.load(id);
    try{
      response.getOutputStream().write(gson.toJson(news).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteNews(@PathVariable Long id, HttpServletRequest request){
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public void addNews(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    //todo
    NewsDto news = new NewsDto();
    news = service.insert(news);
    try{
      response.getOutputStream().write(gson.toJson(news).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public void updateNews(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    NewsDto news = new NewsDto();
    //todo
    news = service.update(news);
    try{
      response.getOutputStream().write(gson.toJson(news).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }  
}
