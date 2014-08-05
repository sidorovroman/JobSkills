package com.onedeveloperstudio.core.web;

import com.onedeveloperstudio.core.common.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * User: y.zakharov
 * Date: 23.07.14
 */
public class AuthViewController {
  @RequestMapping(value = "/registrate/facebook", method = RequestMethod.POST)
  public ModelAndView facebookRegistration() throws Exception {
    return new ModelAndView(new RedirectView(Constants.FACEBOOK_URL + "?client_id=" + Constants.FACEBOOK_API_KEY
        + "&redirect_uri=" + Constants.FACEBOOK_URL_CALLBACK_REGISTRATION
        + "&scope=email,user_location&state=registration", true, true, true));
  }
}
