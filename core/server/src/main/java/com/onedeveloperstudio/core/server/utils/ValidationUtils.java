package com.onedeveloperstudio.core.server.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: y.zakharov
 * Date: 11.02.15
 */
public abstract class ValidationUtils {
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  public static String validateEmail(String email){
    Matcher matcher = pattern.matcher(email);
    return (matcher.matches() ? "" : "Email заполнен неверно\n");
  }

  public static String validatePassword(String password){
    StringBuilder builder = new StringBuilder();
    if(password.length()<8){
      builder.append("Пароль должен быть длинее 8 символов\n");
    }
    return builder.toString();
  }
}
