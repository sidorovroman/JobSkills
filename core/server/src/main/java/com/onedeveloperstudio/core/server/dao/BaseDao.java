package com.onedeveloperstudio.core.server.dao;

import com.onedeveloperstudio.core.common.dto.BaseDto;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.Entity;
import org.hibernate.Criteria;
import org.hibernate.StaleObjectStateException;

import java.io.Serializable;
import java.util.List;

public class BaseDao<E extends BaseEntity> extends AbstractDao implements Dao<E>{
  private Class<E> entityClass;

  public BaseDao(Class<E> entityClass) {
    init(entityClass);
  }


  protected final void init(Class<E> entityClass) {
    this.entityClass = entityClass;
  }

  public void setEntityClass(Class<? extends BaseEntity> entityClass) {
    init((Class<E>) entityClass);
  }

  public Class<? extends BaseEntity> getEntityClass() {
    return (Class<? extends BaseEntity>) getType();
  }

  @Override
  public List<E> getAll() {
    return createBaseCriteria().list();
  }

  @Override
  public E load(Serializable id) {
    return load(entityClass, id);
  }

  @Override
  public E findById(Serializable id) {
    return findById(entityClass, id);
  }

  @Override
  public void remove(Serializable id) {
    remove(load(id));
  }

  public void remove(E entity) {
    getSession().delete(entity);
  }

  @Override
  public <D extends BaseDto> D loadDto(Class<D> dtoClass, Serializable id) {
    return loadDto(entityClass, dtoClass, id);
  }

  @Override
  public <D extends BaseDto> List<D> getAllDtos(Class<D> dtoClass) {
    return mapAsList(getAll(), dtoClass);
  }

  @Override
  public <D extends BaseDto> D saveDto(D dto) {
    try {
      E entity = mergeDto(dto);
      flush();
      return map(entity, (Class<D>) dto.getClass());
    } catch (StaleObjectStateException e) {
      logger.debug(e.getMessage(), e);
      throw new RuntimeException("Запись была изменена другим пользователем", e);
    }
  }

  @Override
  public <D extends BaseDto> E mergeDto(D dto) {
    return mergeDto(dto, getType());
  }

  @Override
  public <D extends BaseDto> E mergeDto(D dto, E entity) {
    return mergeDto(dto, entity, getType());
  }


  @Override
  public Criteria createBaseCriteria() {
    return getSession().createCriteria(getType());
  }

  public Class<E> getType() {
    return entityClass;
  }

  @Override
  public void setType(Class<? extends Entity> clazz) {
    this.entityClass = (Class<E>) clazz;
  }

}