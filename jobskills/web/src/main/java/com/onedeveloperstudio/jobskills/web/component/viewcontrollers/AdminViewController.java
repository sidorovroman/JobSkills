package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.common.handler.BaseService;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
    BaseService service = (BaseService) beanFactory.getBean("simpleService");
    service.setAppObj(jobAppObj);

    JobDto dto = (JobDto) service.load(1l);
    return "/admin/index";
  }
}
