package com.onedeveloperstudio.core.web;

import com.onedeveloperstudio.core.common.Authority;
import com.onedeveloperstudio.core.common.AuthorityType;
import com.onedeveloperstudio.core.common.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.EnumSet;
import java.util.Map;

/**
 * User: y.zakharov
 * Date: 23.07.14
 */
@RequestMapping(value = "/callback/facebook", method = RequestMethod.GET)
public class FacebookController/* extends ExternalController*/ implements Constants {
  @RequestMapping(value = "/registration", params = "code")
  public ModelAndView registrationAccessCode(@RequestParam("code") String code, HttpServletRequest request) throws Exception {
/*    String authRequest = Utils.sendHttpRequest("GET", FACEBOOK_URL_ACCESS_TOKEN, new String[]{"client_id", "redirect_uri", "client_secret", "code"}, new String[]{FACEBOOK_API_KEY, FACEBOOK_URL_CALLBACK_REGISTRATION, FACEBOOK_API_SECRET, code});
    String token = Utils.parseURLQuery(authRequest).get("access_token");
    String tokenRequest = Utils.sendHttpRequest("GET", FACEBOOK_URL_ME, new String[]{"access_token"}, new String[]{token})
    Map<String, Json> userInfoResponse = Json.read(tokenRequest).asJsonMap();
    String email = userInfoResponse.get("email").asString().toLowerCase();
    String id = userInfoResponse.get("id").asString();
    //verifying ... is new? is email in DB?
    //creating objects
    Customer customer = new Customer();
    customer.setEmail(email);
    //...
    customerer = (Customerer) userDAO.put(customer);
    FacebookAuthUser user = new FacebookAuthUser();
    user.setFirstName(firstName);
    //...
    user.setIdentificationName(id);
    user.setToken(token);
    user.setType(AuthorityType.FACEBOOK);
    user.setEnabled(true);
    user.setAuthority(EnumSet.of(Authority.CUSTOMER));
    user.setUser(customer);
    authenticationDAO.put(user);*/
    return new ModelAndView(new RedirectView("/registrate.complete", true, true, false));
  }

  @RequestMapping(value = "/registration", params = "error_reason")
  public ModelAndView registrationError(@RequestParam("error_description") String errorDescription, HttpServletRequest request, HttpServletResponse response) {
    //return client to registration page with errorDescription
    return new ModelAndView(new RedirectView("/registrate", true, true, false));
  }
  //will signin and signinError
}
