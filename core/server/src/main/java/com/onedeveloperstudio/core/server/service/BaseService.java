package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.dto.BaseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaseService<D extends BaseDto> {

  D insert(D dto);

  D update(D dto);

  D load(Long id);

  List<D> loadAll();

  List<D> loadAny(Pageable pageRequest);

  void delete(Long id);

  public AppObj getAppObj();

  public void setAppObj(AppObj appObj);

  public JpaRepository getRepository();

  public void setRepository(JpaRepository repository);

}