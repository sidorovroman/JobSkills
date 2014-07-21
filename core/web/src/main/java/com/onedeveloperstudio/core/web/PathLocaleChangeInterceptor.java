package com.onedeveloperstudio.core.web;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Yuri Zakharov
 */
public class PathLocaleChangeInterceptor extends HandlerInterceptorAdapter {
  private static final String DEFAULT_LOCALE = "ru";
  private static final String[] AVAILABLE_LOCALES = new String[]{"en", "ru"};

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws ServletException, IOException {

    String newLocale = null;
    String currentLocale = getLocaleFromResolver(request);

    newLocale = getLocaleFromPath(request);

    if (newLocale == null) {
      newLocale = currentLocale;
    }

    if (newLocale == null) {
      newLocale = DEFAULT_LOCALE;
    }

    if (newLocale != null && !newLocale.equals(currentLocale)) {
      setLocale(request, response, newLocale);
    }

    return true;
  }

  private String getLocaleFromPath(HttpServletRequest request) {
    List<String> requestParts = this.getSevletRequestParts(request);
    return this.getLocaleFromRequestParts(requestParts);
  }

  private String getLocaleFromResolver(HttpServletRequest request) {
    Locale locale = RequestContextUtils.getLocale(request);
    String language = locale.getLanguage();
    for (String lang : AVAILABLE_LOCALES) {
      if (lang.equals(language)) {
        return lang;
      }
    }
    return null;
  }

  private void setLocale(HttpServletRequest request, HttpServletResponse response, String locale) {
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

    if (localeResolver == null) {
      throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
    }
    localeResolver.setLocale(request, response, StringUtils.parseLocaleString(locale));
  }

  private List<String> getSevletRequestParts(HttpServletRequest request) {
    String[] splitedParts = request.getServletPath().split("/");
    List<String> result = new ArrayList<>();

    for (String sp : splitedParts) {
      if (sp.trim().length() > 0)
        result.add(sp);
    }

    return result;
  }

  private String getLocaleFromRequestParts(List<String> parts) {
    if (parts.size() > 0) {
      for (String lang : AVAILABLE_LOCALES) {
        if (lang.equals(parts.get(0))) {
          return lang;
        }
      }
    }

    return null;
  }

  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
      throws Exception {
    if (modelAndView != null) {
      Locale locale = RequestContextUtils.getLocale(request);
      modelAndView.addObject("locale", locale.getLanguage());

      // добавляем ссылки для переключения локалей
      List<String> requestParts = this.getSevletRequestParts(request);
      StringBuilder sb;
      for (String lang : AVAILABLE_LOCALES) {
        sb = new StringBuilder();
        sb.append(request.getContextPath()).append("/");
        sb.append(lang);
        boolean includePart;
        for (int i = 0; i < requestParts.size(); i++) {
          includePart = true;
          for (String rmLang : AVAILABLE_LOCALES) {
            if (rmLang.equals(requestParts.get(i))) {
              includePart = false;
            }
          }
          if (includePart) {
            sb.append('/');
            sb.append(requestParts.get(i));
          }
        }

        if (request.getQueryString() != null && request.getQueryString().length() > 0) {
          sb.append("?").append(request.getQueryString());
        }
        modelAndView.addObject(lang + "_locale_link", sb.toString());
      }
    }
  }
}
