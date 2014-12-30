package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.server.service.JobService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

  @Autowired
  private SysUserService sysUserService;

  private JSONSerializer serializer = new JSONSerializer();
  private JSONDeserializer<JobDto> deserializer = new JSONDeserializer<>();

  @RequestMapping("/list")
  @ResponseBody
  public void getList(HttpServletRequest request, HttpServletResponse response){
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    List<JobDto> jobs = service.loadAll();
    try{
      response.getOutputStream().write(serializer.deepSerialize(jobs).getBytes());
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
      response.getOutputStream().write(serializer.deepSerialize(job).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public String deleteJob(@PathVariable Long id, HttpServletRequest request){
    //А стоит ли вот так давать возможность удалять?
    service.delete(id);
    return "{status : 1}";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public void addJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF8");
    JobDto job = deserializer.deserialize(request.getReader(), JobDto.class);
    if(job.getParent()!=null && job.getParent().getId() == null){
      job.setParent(null);
    }
    job = service.insert(job);
    try{
      response.getOutputStream().write(serializer.deepSerialize(job).getBytes());
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
      response.getOutputStream().write(serializer.deepSerialize(job).getBytes());
    } catch (Exception e){
      System.out.println("ERROR EBAT'");
    }
  }
}
