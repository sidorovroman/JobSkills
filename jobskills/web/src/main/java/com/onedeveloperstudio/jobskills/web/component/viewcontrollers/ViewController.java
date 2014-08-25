package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.core.web.exception.ClientJsonException;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.server.service.CommentaryService;
import com.onedeveloperstudio.jobskills.server.service.JobService;
import com.onedeveloperstudio.jobskills.server.service.RequiredSkillService;
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

  /**
   * Список всех работ/профессий
   * если необходимо возвращать лишь как часть страницы - нужно навесить аннотацию @ResponseBody и изменить jobList.jsp
   * @param request
   * @return
   */
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

  /**
   * POST метод сохраняет данные
   * @param request
   * @return
   */
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

  //todo добавить редактирование после появления нормальных форм

  /**
   *  GET методом загружаем форму сохранения, потом сюда же можно будет отправлять id редактируемой записи
   * @param request
   * @return
   */
  @RequestMapping(value = "/addJob", method = RequestMethod.GET)
  public ModelAndView addJobPage(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("addJob");
    AppObjDict dict = AppObjDict.getInstance();
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    List<JobDto> list = service.getAllParents();
    mv.addObject("jobs", list);
    return mv;
  }

  //todo возможность добавлять новые ресурсы только "эксператам в области" по оценке пользователей?
  @RequestMapping(value = "/addRequeredSkill", method = RequestMethod.GET)
  public ModelAndView addRequiredSkillPage(@RequestParam Long jobId, HttpServletRequest request){
    ModelAndView mv = new ModelAndView("listRequiredSkills");
    AppObjDict dict = AppObjDict.getInstance();
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    JobDto job = service.load(jobId);
    mv.addObject("job", job);
    return mv;
  }

  @RequestMapping(value = "/addRequeredSkill", method = RequestMethod.POST)
  public ModelAndView addRequiredSkill(@RequestParam Long jobId, HttpServletRequest request){
    ModelAndView mv = new ModelAndView("listRequiredSkills");
    AppObjDict dict = AppObjDict.getInstance();
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    JobDto job = service.load(jobId);
    mv.addObject("job", job);

    AppObj requiredSkillAppObj = dict.getAppObj("requiredSkill");
    RequiredSkillService requiredSkillService = beanFactory.getBean(RequiredSkillService.class);
    service.setAppObj(requiredSkillAppObj);

    List<RequiredSkillDto> skillList = requiredSkillService.loadAllbyJob(jobId);
    mv.addObject("skills", skillList);

    return mv;
  }
}
