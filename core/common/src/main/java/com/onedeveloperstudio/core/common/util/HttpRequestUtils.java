package com.onedeveloperstudio.core.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * User: y.zakharov
 * Date: 23.07.14
 */
public class HttpRequestUtils {
  public static String sendHttpRequest(String methodName, String url, String[] names, String[] values) throws HttpException, IOException {
    if (names.length != values.length) return null;
    if (!methodName.equalsIgnoreCase("GET") && !methodName.equalsIgnoreCase("POST")) return null;
    HttpUriRequest method;
    if (methodName.equalsIgnoreCase("GET")) {
      String[] parameters = new String[names.length];
      for (int i = 0; i < names.length; i++)
        parameters[i] = names[i] + "=" + values[i];
      method = new HttpGet(url + "?" + StringUtils.join(parameters, "&"));
    } else {
      method = new HttpPost(url);
      for (int i = 0; i < names.length; i++){
        method.getParams().setParameter(names[i], values[i]);
      }
      method.setHeader("Content-Type", "application/x-www-form-urlencoded");
    }
    HttpResponse  response = new DefaultHttpClient().execute(method);
/*    return getStringFromStream(respomethod.getResponseBodyAsStream());*/
    return response.toString();
  }

  public static Map<String, String> parseURLQuery(String query) {
    Map<String, String> result = new HashMap<String, String>();
    String params[] = query.split("&");
    for (String param : params) {
      String temp[] = param.split("=");
      try {
        result.put(temp[0], URLDecoder.decode(temp[1], "UTF-8"));
      } catch (UnsupportedEncodingException exception) {
        exception.printStackTrace();
      }
    }
    return result;
  }
}
