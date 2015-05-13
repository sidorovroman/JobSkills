package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.server.service.JobService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 11.09.14
 */
@Controller
@RequestMapping("/jobs")
public class JobsViewController {
  public String imgsPath;

  @Autowired
  private JobService service;

  @Value("${jobsViewController.imgsPath}")
  public void setImgsPath(String imgsPath) {
    //Validate.notBlank(imgsPath, "Не определён параметр 'saiku.schema.path'");
    this.imgsPath = imgsPath;
  }

  @RequestMapping("/list")
  @ResponseBody
  public List<JobDto> getList(@RequestBody(required = false) Pageable pageable){
    List<JobDto> jobs = null;
    if (pageable != null) {
      jobs = service.loadAny(pageable);
    } else {
      jobs = service.getAllParents();
    }
    return jobs;
  }

  @RequestMapping("/allJobs")
  @ResponseBody
  public List<JobDto> getJobsWithSkills(){
    List<JobDto> all = service.loadAll();
    return all;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public JobDto getJob(@PathVariable Long id) throws Exception {
    JobDto job = service.load(id);
    return job;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String deleteJob(@PathVariable Long id) {
    //А стоит ли вот так давать возможность удалять?
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public JobDto addJob(@RequestBody JobDto job, @RequestBody(required = false) byte[] img) {
    if (job.getParent() != null && job.getParent().getId() == null) {
      job.setParent(null);
    }
    job.setAddDate(new Date().getTime());
    job = service.insert(job);
    return job;
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public JobDto updateJob(@RequestBody JobDto job){
    if (job.getParent() != null && job.getParent().getId() == null) {
      job.setParent(null);
    }
    job = service.update(job);
    return job;
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public String handleAllException(Exception ex) {
    if(ex instanceof AccessDeniedException){
      System.out.println(ex.getLocalizedMessage());
      return "{\"error\":\"" + ex.getLocalizedMessage() + "\"}";
    } else if(ex instanceof HibernateException){
      System.out.println(ex.getLocalizedMessage());
      return "{\"error\":\"" + ex.getLocalizedMessage() + "\"}";
    }
    ex.printStackTrace();
    return "{\"error\":\"" + ex.getLocalizedMessage() + "\"}";
  }
}
