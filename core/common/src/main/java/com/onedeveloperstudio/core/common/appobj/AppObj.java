package com.onedeveloperstudio.core.common.appobj;

import java.util.HashMap;
import java.util.Map;

public class AppObj {

  public static final String ENTITY = "entity";
  public static final String EDIT_DTO = "editDto";
  public static final String SERVICE = "service";

  private String name;
  private String caption;
  private Map<String, String> props = new HashMap();

  public AppObj(String name, String caption) {
    this.name = name;
    this.caption = caption;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public void setProperty(String name, String value) {
    props.put(name, value);
  }

  public String getProperty(String name) {
    return props.get(name);
  }
}
