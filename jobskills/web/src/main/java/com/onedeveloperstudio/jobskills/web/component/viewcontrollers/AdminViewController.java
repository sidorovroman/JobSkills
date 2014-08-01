package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.core.server.service.BaseServiceImpl;
import com.onedeveloperstudio.core.server.service.SimpleService;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.server.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 18.07.14
 */
@Controller
public class AdminViewController {
  @Autowired
  private AutowireCapableBeanFactory beanFactory;

  @RequestMapping(value = {"/admin/index"})
  public String getDefaultPage(ModelMap model) {
    AppObjDict dict = AppObjDict.getInstance();
    AppObj jobAppObj = dict.getAppObj("job");
    JobService service = beanFactory.getBean(JobService.class);
    service.setAppObj(jobAppObj);
    List<JobDto>  list = service.loadAll();
    return "/admin/index";
  }
}
