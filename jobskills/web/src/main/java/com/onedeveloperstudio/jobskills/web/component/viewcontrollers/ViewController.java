package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.web.exception.ClientJsonException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class ViewController{
  private static final String NAV_ITEM = "navItem";

  @RequestMapping(value = {"/"})
  public String getDefaultPage(ModelMap model) {
    return "index";
  }

/*  @RequestMapping(value = {"/{locale}"})*/

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
}
