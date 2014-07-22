package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.dto.BaseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseServiceImpl<D extends BaseDto> implements com.onedeveloperstudio.core.common.handler.BaseService<D> {

  private AppObj appObj;
  private Class<D> dtoClass;

  @Override
  public D insert(D dto) {
    return null;
  }

  @Override
  public D update(D dto) {
    return null;
  }

  @Override
  public D load(Long id) {
    return null;
  }

  @Override
  public <D1 extends BaseDto> List<D1> loadAll() {
    return null;
  }

  @Override
  public void delete(Long id) {
  }

  @Override
  public AppObj getAppObj() {
    return null;
  }

  @Override
  public void setAppObj(AppObj appObj) {
  }
}
