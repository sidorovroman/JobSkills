package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.core.web.exception.ClientJsonException;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.server.service.CommentaryService;
import com.onedeveloperstudio.jobskills.server.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class ViewController {
  @Autowired
  private AutowireCapableBeanFactory beanFactory;

  @RequestMapping(value = {"/"})
  public String getDefaultPage(ModelMap model) {
    return "index";
  }

  @ExceptionHandler(ClientJsonException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public void handleClientException(ClientJsonException exception, HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json");
    String json = exception.getMessage();
    PrintWriter out = null;
    try {
      out = response.getWriter();
      out.write(json);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/jobs")
  public ModelAndView getThemeComments(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("jobList");
    AppObjDict dict = AppObjDict.getInstance();
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    List<JobDto> list = service.getAllParents();
    mv.addObject("jobs", list);
    return mv;
  }

  @RequestMapping(value = "/addJob", method = RequestMethod.POST)
  public String addJob(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("jobList");
    AppObjDict dict = AppObjDict.getInstance();
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    JobDto dto = new JobDto();
    dto.setCaption(request.getParameter("caption"));
    if(request.getParameter("parent") == null){
      dto.setParent(null);
    } else {
      JobDto parent = service.load(Long.valueOf(request.getParameter("parent")));
      dto.setParent(parent);
    }
    service.insert(dto);
    return "redirect:jobs";
  }

  @RequestMapping(value = "/addJob", method = RequestMethod.GET)
  public ModelAndView addJobPage(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("addJob");
    AppObjDict dict = AppObjDict.getInstance();
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    List<JobDto> list = service.getAllParents();
    mv.addObject("jobs", list);
    System.out.println(printAllJobs(list ,0));
    return mv;
  }

  public String printAllJobs(List<JobDto> jobDtos, int countTab) {
    StringBuilder builder = new StringBuilder();
    for (JobDto dto : jobDtos) {
      builder.append("<option value='").append(dto.getId()).append("'>").append(getTabs(countTab)).append(dto.getCaption()).append("</option>\n");
      builder.append(printAllJobs(dto.getChildren(), countTab+=1));
      countTab-=1;
    }
    return builder.toString();
  }

  public String getTabs(int count){
    StringBuilder buider = new StringBuilder();
    for(int i = 0; i < count; i++){
      buider.append("&nbsp;&nbsp;&nbsp;&nbsp;");
    }
    return buider.toString();
  }

}
