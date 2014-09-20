package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.google.gson.Gson;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.server.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 11.09.14
 */
@Controller
@RequestMapping("/jobs")
public class JobsViewController {

  @Autowired
  private JobService service;

  private Gson gson;

  @PostConstruct
  private void init(){
    gson = new Gson();
  }


  @RequestMapping("/list")
  @ResponseBody
  public void getList(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    List<JobDto> jobs = service.getAllParents();
    try{
      response.getOutputStream().write(gson.toJson(jobs).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public void getJob(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    JobDto job = service.load(id);
    try{
      response.getOutputStream().write(gson.toJson(job).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteJob(@PathVariable Long id, HttpServletRequest request){
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public void addJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    StringBuffer jb = new StringBuffer();
    String line = null;
    BufferedReader reader = request.getReader();
    while ((line = reader.readLine()) != null){
      jb.append(line);
    }
    JobDto job = gson.fromJson(jb.toString(), JobDto.class);
    job = service.insert(job);
    try{
      response.getOutputStream().write(gson.toJson(job).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseBody
  public void updateJob(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    JobDto job = new JobDto();
    //todo
    job = service.update(job);
    try{
      response.getOutputStream().write(gson.toJson(job).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }
}
