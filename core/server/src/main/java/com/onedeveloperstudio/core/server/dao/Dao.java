package com.onedeveloperstudio.core.server.dao;

import com.onedeveloperstudio.core.common.dto.BaseDto;
import com.onedeveloperstudio.core.server.entity.Entity;
import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.List;

public interface Dao<E extends Entity> {
  List<E> getAll();

  E load(Serializable id);

  E findById(Serializable id);

  void remove(Serializable id);

  <D extends BaseDto> D loadDto(Class<D> dtoClass, Serializable id);

  <D extends BaseDto> List<D> getAllDtos(Class<D> dtoClass);

  <D extends BaseDto> D saveDto(D dto);

  <D extends BaseDto> E mergeDto(D dto);

  <D extends BaseDto> E mergeDto(D dto, E entity);

  Criteria createBaseCriteria();

  void flush();

  Class<E> getType();

  void setType(Class<? extends Entity> clazz);

  <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass);

  <S, D> D map(S sourceObject, Class<D> destinationClass);

  Serializable save(Object obj);
}