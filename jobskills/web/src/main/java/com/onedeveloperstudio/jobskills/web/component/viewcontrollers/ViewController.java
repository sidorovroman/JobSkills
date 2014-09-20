package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.core.web.exception.ClientJsonException;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.common.dto.NewsDto;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.server.service.JobService;
import com.onedeveloperstudio.jobskills.server.service.RequiredSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.HttpStatus;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class ViewController {
  @Autowired
  private AutowireCapableBeanFactory beanFactory;

  @Autowired
  private SysUserService service;
  private static AppObjDict dict;
  static {
     dict = AppObjDict.getInstance();
  }

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
  public ModelAndView getAllJobs(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("jobList");
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    List<JobDto> list = service.getAllParents();
    mv.addObject("jobs", list);
    return mv;
  }

  /**
   * Список всех работ/профессий
   * если необходимо возвращать лишь как часть страницы - нужно навесить аннотацию @ResponseBody и изменить jobList.jsp
   */
  @RequestMapping(value = "/job")
  public ModelAndView getThemeComments(@RequestParam Long jobId, HttpServletRequest request){
    ModelAndView mv = new ModelAndView("job");
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    service.setAppObj(jobAppObj);
    JobDto job = service.load(jobId);
    if(job == null){
      return errorPage("Не удалось найти выбранную профессию");
    }
    mv.addObject("job", job);
    AppObj rsAppObj = dict.getAppObj("requiredSkill");
    RequiredSkillService rsService = beanFactory.getBean(RequiredSkillService.class);
    rsService.setAppObj(rsAppObj);
    List<RequiredSkillDto> skills = rsService.loadAllbyJob(jobId);
    mv.addObject("skills", skills);
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
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    JobDto dto = new JobDto();
    dto.setCaption(request.getParameter("caption"));
    dto.setDescription(request.getParameter("description"));
    if(request.getParameter("parent") == null || request.getParameter("parent").equals("")){
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
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    List<JobDto> list = service.getAllParents();
    mv.addObject("jobs", list);
    return mv;
  }

  //todo возможность добавлять новые ресурсы только "эксператам в области" по оценке пользователей?
  @RequestMapping(value = "/addRequiredSkill", method = RequestMethod.GET)
  public ModelAndView addRequiredSkillPage(@RequestParam Long jobId, HttpServletRequest request){
    ModelAndView mv = new ModelAndView("addRequiredSkill");
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    JobDto job = service.load(jobId);
    if(job == null){
      return errorPage("Не удалось найти выбранную профессию");
    }
    mv.addObject("job", job);
    return mv;
  }

  @RequestMapping(value = "/addRequiredSkill", method = RequestMethod.POST)
  public ModelAndView addRequiredSkill(@RequestParam Long jobId, HttpServletRequest request){
    ModelAndView mv = new ModelAndView("requiredSkills");
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    JobDto job = service.load(jobId);
    mv.addObject("job", job);

    AppObj requiredSkillAppObj = dict.getAppObj("requiredSkill");
    RequiredSkillService requiredSkillService = beanFactory.getBean(RequiredSkillService.class);
    requiredSkillService.setAppObj(requiredSkillAppObj);

    RequiredSkillDto newDto = new RequiredSkillDto();
    newDto.setCaption(request.getParameter("caption"));
    newDto.setDescription(request.getParameter("description"));
    JobDto parent = new JobDto();
    parent.setId(jobId);
    newDto.setJob(parent);
    requiredSkillService.insert(newDto);

    List<RequiredSkillDto> skillList = requiredSkillService.loadAllbyJob(jobId);
    mv.addObject("skills", skillList);
    return mv;
  }

  @RequestMapping(value = "/error")
  public ModelAndView errorPage(String message){
    ModelAndView errorPage = new ModelAndView("error");
    errorPage.addObject("message", message);
    return errorPage;
  }

  @RequestMapping(value = "/news")
  public ModelAndView getNews(HttpServletRequest request){
    ModelAndView mv = new ModelAndView("news");
    AppObj newsAppObj = dict.getAppObj("news");
    BaseService<NewsDto> service = beanFactory.getBean(BaseService.class);
    service.setAppObj(newsAppObj);
    List<NewsDto> list = service.loadAll();
    mv.addObject("news", list);
    return mv;
  }
}
