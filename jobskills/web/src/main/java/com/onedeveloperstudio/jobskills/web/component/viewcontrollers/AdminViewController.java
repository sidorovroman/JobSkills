package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.server.service.JobService;
import com.onedeveloperstudio.jobskills.server.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 18.07.14
 */
@Controller
@RequestMapping("/admin")
public class AdminViewController {
  @Autowired
  private AutowireCapableBeanFactory beanFactory;

  @Autowired
  private JobService jobService;

  @Autowired
  private NewsService newsService;

  @Autowired
  private SysUserService userService;

  @RequestMapping(value = {"/index"})
  public ModelAndView getDefaultPage(ModelMap model) {
    List<JobDto>  list = jobService.loadAll();
    ModelAndView mv = new ModelAndView("admin/index");
    mv.addObject("jobsCount", jobService.getCount());
    mv.addObject("usersCount", userService.getCount());
    mv.addObject("newsCount", newsService.getCount());
    Long currentTime = new Date().getTime();
    Long monthAgoTime = currentTime - 3600*24*30;
    mv.addObject("newUsers", userService.countUsersRegisteredBetweenDates(monthAgoTime, currentTime));
    mv.addObject("newJobs", jobService.getCountBetweenDates(monthAgoTime, currentTime));
    mv.addObject("newNewss", newsService.countNewsAddedBetweenDates(monthAgoTime, currentTime));
    return mv;
  }
}
