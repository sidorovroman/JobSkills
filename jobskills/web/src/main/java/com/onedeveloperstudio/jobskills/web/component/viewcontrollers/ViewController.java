package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
