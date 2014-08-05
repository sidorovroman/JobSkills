package com.onedeveloperstudio.core.common;

/**
 * User: y.zakharov
 * Date: 23.07.14
 */
public interface Constants {
  final public static String SITE_ADDRESS = "";
  final public static String FACEBOOK_API_KEY = "XXXXXXXXXXXXXXXX";
  final public static String FACEBOOK_API_SECRET = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  final public static String FACEBOOK_URL = "https://www.facebook.com/dialog/oauth";
  final public static String FACEBOOK_URL_ACCESS_TOKEN = "https://graph.facebook.com/oauth/access_token";
  final public static String FACEBOOK_URL_ME = "https://graph.facebook.com/me";
  final public static String FACEBOOK_URL_CALLBACK_REGISTRATION = SITE_ADDRESS + "/callback/facebook/registration";
  final public static String FACEBOOK_URL_CALLBACK_SIGNIN = SITE_ADDRESS + "/callback/facebook/signin";
}
