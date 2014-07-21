package com.onedeveloperstudio.core.common.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class BeanMapper {

  public static void map(Object sourceObj, Object destObj) {
    MapperFactory factory = new DefaultMapperFactory.Builder().build();
    MapperFacade mapper = factory.getMapperFacade();
    mapper.map(sourceObj, destObj);
  }

  public static <T> T map(Object sourceObj, Class<T> destObjClass) {
    MapperFactory factory = new DefaultMapperFactory.Builder().build();
    MapperFacade mapper = factory.getMapperFacade();
    return mapper.map(sourceObj, destObjClass);
  }

  public static Object map(Object sourceObj, String destObjClassName) {
    Class cl = null;
    try {
      cl = Class.forName(destObjClassName);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return map(sourceObj, cl);
  }
}
