package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.jobskills.common.dto.NewsDto;
import com.onedeveloperstudio.jobskills.server.service.NewsService;
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

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Comparator;
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

  @PostConstruct
  private void init() {
    AppObjDict dict = AppObjDict.getInstance();
    service.setAppObj(dict.getAppObj("news"));
  }

  @RequestMapping(value = "/top10")
  @ResponseBody
  public List<NewsDto> getTop10News() {
    List<NewsDto> newss = service.loadAll();
    Collections.sort(newss, new Comparator<NewsDto>() {
      @Override
      public int compare(NewsDto o1, NewsDto o2) {
        return -1 * o1.getRating().compareTo(o2.getRating());
      }
    });
    return newss.size() > 10 ? newss.subList(0, 10) : newss;
  }

  @RequestMapping(value = "/latest10")
  @ResponseBody
  public List<NewsDto> getLatest10News(){
    List<NewsDto> newss = service.loadAll();
    Collections.sort(newss, new Comparator<NewsDto>() {
      @Override
      public int compare(NewsDto o1, NewsDto o2) {
        return -1 * o1.getAddDate().compareTo(o2.getAddDate());
      }
    });
    return newss.size() > 10 ? newss.subList(0, 10) : newss;
  }

  @RequestMapping(value = "/list")
  @ResponseBody
  public List<NewsDto> getList(@RequestBody(required = false) Pageable pageable) {
    List<NewsDto> newss = null;
    if (pageable == null) {
      newss = service.loadAll();
    } else {
      newss = service.loadAny(pageable);
    }
    return newss;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public NewsDto getNews(@PathVariable Long id){
    NewsDto news = service.load(id);
    return news;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String deleteNews(@PathVariable Long id) {
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public NewsDto addNews(@RequestBody NewsDto news){
    SysUserDto user = sysUserService.getAuthentication();
    news.setAddDate(new Date().getTime());
    news.setAuthor(user);
    news = service.insert(news);
    return news;
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public NewsDto updateNews(@RequestBody NewsDto news){
    news = service.update(news);
    return news;
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

  @RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public CommentaryDto comment(@PathVariable Long id, @RequestBody CommentaryDto comment){
    return service.comment(id, comment);
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
