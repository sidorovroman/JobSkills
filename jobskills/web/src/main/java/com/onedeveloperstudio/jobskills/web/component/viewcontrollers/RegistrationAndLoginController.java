package com.onedeveloperstudio.jobskills.web.component.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: y.zakharov
 * Date: 20.08.14
 */
@Controller
public class RegistrationAndLoginController {
  private final String USER_AGENT = "Mozilla/5.0";

  /**
   * коллбэк при авторизации пользователя
   * @param request
   * @throws Exception
   */
  @RequestMapping("/login_callback")
  public void loginCallback(HttpServletRequest request) {
    String token = request.getParameter("token");
    try {
      String answer = sendGet(token);
    } catch (Exception e) {
      System.out.println("Не удалось получить данные о пользователе");
    }
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
