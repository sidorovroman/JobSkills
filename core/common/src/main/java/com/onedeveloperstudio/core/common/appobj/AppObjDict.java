package com.onedeveloperstudio.core.common.appobj;

import com.onedeveloperstudio.core.common.util.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AppObjDict {

  private Map<String, AppObj> appObjMap = new HashMap<String, AppObj>();

  private static class InstanceHolder {
    static final AppObjDict INSTANCE = new AppObjDict();
  }

  private AppObjDict() {
    load();
  }

  public static AppObjDict getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public AppObj getAppObj(String name) {
    return appObjMap.get(name);
  }

  private void load() {
    InputStream resourceStream = AppObjDict.class.getResourceAsStream("/appobj.xml");
    Document document = XMLUtil.createDocument(resourceStream);
    Element docElem = document.getDocumentElement();
    Element objectElem = XMLUtil.getChildElement(docElem);
    while (objectElem != null) {
      String name = objectElem.getAttribute("name");
      String caption = objectElem.getAttribute("caption");

      AppObj appObj = new AppObj(name, caption);
      setAllProperties(appObj, objectElem);

      appObjMap.put(name, appObj);

      objectElem = XMLUtil.getNextElement(objectElem);
    }
  }

  private void setAllProperties(AppObj appObj, Element objectElem) {
    Element childElem = XMLUtil.getChildElement(objectElem);
    while (childElem != null) {
      appObj.setProperty(childElem.getTagName(), childElem.getAttribute("class"));
      childElem = XMLUtil.getNextElement(childElem);
    }
  }

}
