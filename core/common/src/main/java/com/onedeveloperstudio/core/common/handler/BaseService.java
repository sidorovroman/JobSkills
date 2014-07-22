package com.onedeveloperstudio.core.common.handler;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.dto.BaseDto;

import java.util.List;

public interface BaseService<D extends BaseDto> {

  D insert(D dto);

  D update(D dto);

  D load(Long id);

  <D extends BaseDto> List<D> loadAll();

  void delete(Long id);

  public AppObj getAppObj();

  public void setAppObj(AppObj appObj);
}