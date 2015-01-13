package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.ULoginUser;
import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.core.server.utils.MappingUtils;
import flexjson.JSONDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

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

  private JSONDeserializer<ULoginUser> serializer = new JSONDeserializer<>();

  @Autowired
  private DaoAuthenticationProvider provider;

  @RequestMapping("/login")
  public String loginPage(HttpServletRequest request){
    return "/login";
  }

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
      InputStream stream =  new ByteArrayInputStream(answer.getBytes());
      Reader reader = new InputStreamReader(stream);
      ULoginUser user = serializer.deserialize(reader, ULoginUser.class);
      user.setPassword(DUMMY_PASSWORD);
      SysUserDto sysUserDto = MappingUtils.fromULoginUserToDto(user);
      SysUserDto dto = sysUserService.loadByEmail(user.getEmail());
      if (dto.getId() != null) {
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
   * Регистрируем пользователя
   */
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String register(HttpServletRequest request) throws ParseException {
    if(StringUtils.isEmpty(request.getParameter("email")) || StringUtils.isEmpty(request.getParameter("password"))){
      return "error";
    }
    SysUserDto dto = new SysUserDto();
    dto.setEmail(request.getParameter("email"));
    dto.setPassword(request.getParameter("password"));
    dto.setUserFullName(request.getParameter("userFullName"));
    dto.setSex(request.getParameter("sex"));
    dto.setPhone(request.getParameter("phone"));
    dto.setCountry(request.getParameter("country"));
    dto.setBirthday(Long.valueOf(request.getParameter("birthday")));
    dto.setCity(request.getParameter("city"));
    try{
      sysUserService.insert(dto);
    } catch (Exception e){
      return "error";
    }
    Authentication auth = provider.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(auth);
    return "redirect:/";
  }
}
