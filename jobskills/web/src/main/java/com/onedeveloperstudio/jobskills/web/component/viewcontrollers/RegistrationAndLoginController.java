package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.google.gson.Gson;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.ULoginUser;
import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.core.server.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: y.zakharov
 * Date: 20.08.14
 */
@Controller
public class RegistrationAndLoginController {
  private final String USER_AGENT = "Mozilla/5.0";
  private final String DUMMY_PASSWORD = "DUMMY_PASSWORD";

  @Autowired
  private UserDetailsService service;
  
  @Autowired
  private SysUserService sysUserService;
  

  @Autowired
  private DaoAuthenticationProvider provider;
  /**
   * коллбэк при авторизации пользователя
   * @param request
   * @throws Exception
   */
  @RequestMapping("/login_callback")
  public String loginCallback(HttpServletRequest request) {
    String token = request.getParameter("token");
    try {
      String answer = sendGet(token);
      Gson gson = new Gson();
      InputStream stream =  new ByteArrayInputStream(answer.getBytes());
      Reader reader = new InputStreamReader(stream);
      ULoginUser user = gson.fromJson(reader, ULoginUser.class);
      user.setPassword(DUMMY_PASSWORD);
      SysUserDto sysUserDto = MappingUtils.fromULoginUserToDto(user);
      SysUserDto dto = sysUserService.loadByEmail(user.getEmail());
      if(dto.getId()!=null){
        sysUserDto.setId(dto.getId());
        sysUserService.update(sysUserDto);
      } else {
        sysUserService.insert(sysUserDto);
      }
      Authentication auth = provider.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), DUMMY_PASSWORD));
      SecurityContextHolder.getContext().setAuthentication(auth);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Не удалось получить данные о пользователе");
    }
    return "redirect:/";
  }

  /**
   * Отправляем запрос на получение данных о пользователе
   * @param token - токен, полученный от ulogin
   * @return json - ответ-информация о пользователе
   * @throws Exception
   */
  private String sendGet(String token) throws Exception {
    String url = "http://ulogin.ru/token.php?token=" + token + "&host=" + "localhost:8080/jobSkills";
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("User-Agent", USER_AGENT);
    int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    return response.toString();
  }

  /**
   * коллбэк при авторизации пользователя
   * @param request
   * @throws Exception
   */
  @RequestMapping("/login")
  public void login(HttpServletRequest request) {
    try {

    } catch (Exception e) {
      System.out.println("Не удалось получить данные о пользователе");
    }
  }
}
