package com.onedeveloperstudio.core.web;

import com.onedeveloperstudio.core.common.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: y.zakharov
 * Date: 23.07.14
 */
@RequestMapping(value = "/callback/google", method = RequestMethod.GET)
public class GoogleController /*extends ExternalController*/ implements Constants {
  @RequestMapping(value = {"/", ""}, params = "code")
  public ModelAndView googleProxy(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return new ModelAndView();
  }

  @RequestMapping(value = {"/", ""}, params = "error")
  public ModelAndView googleErrorProxy(@RequestParam("error") String error, @RequestParam("state") String state, HttpServletRequest request) throws Exception {
    return new ModelAndView();
  }
}
