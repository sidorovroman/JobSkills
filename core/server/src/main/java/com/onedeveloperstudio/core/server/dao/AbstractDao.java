package com.onedeveloperstudio.core.server.dao;

import com.onedeveloperstudio.core.common.dto.BaseDto;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 17.07.14
 */
@Repository
public class AbstractDao {
  protected Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private MapperFacade mapperFacade;

  @Autowired
  private SessionFactory sessionFactory;

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  public void setSessionFactory(SessionFactory aSessionFactory) {
    sessionFactory = aSessionFactory;
  }

  public Serializable save(Object obj) {
    return getSession().save(obj);
  }

  public void saveOrUpdate(Object obj) {
    getSession().saveOrUpdate(obj);
  }

  public void update(Object obj) {
    getSession().update(obj);
  }


  public <E> List<E> getAll(Class<E> persistentClass) {
    return getSession().createCriteria(persistentClass).list();
  }

  public void flashAndEvict(Object entity) {
    flush();
    getSession().evict(entity);
  }

  public <E> E merge(E entity) {
    return (E) getSession().merge(entity);
  }

  public void evict(Object entity) {
    getSession().evict(entity);
  }

  public <E> E load(Class<E> entityClass, Serializable id) {
    return (E) getSession().load(entityClass, id);
  }

  public <E> E findById(String entityClass, Serializable id) {
    try {
      return (E) getSession().get(entityClass, id);
    } catch (ObjectNotFoundException e) {
      return null;
    }
  }

  public <E> E findById(Class<E> entityClass, Serializable id) {
    try {
      return (E) getSession().get(entityClass, id);
    } catch (ObjectNotFoundException e) {
      return null;
    }
  }

  public <E extends BaseEntity, D extends BaseDto> D saveDto(D dto, Class<E> entityClass) {
    try {
      E entity = mergeDto(dto, entityClass);
      flush();
      return map(entity, (Class<D>) dto.getClass());
    } catch (StaleObjectStateException e) {
      logger.debug(e.getMessage(), e);
      throw new RuntimeException("Запись была изменена другим пользователем", e);
    }
  }

  public void flush() {
    getSession().flush();
  }

  public void flushAndClear() {
    getSession().flush();
    clearSession();
  }

  public void clearSession() {
    getSession().clear();
  }

  public <E extends BaseEntity, D extends BaseDto> E mergeDto(D dto, Class<E> entityClass) {
    return mapperFacade.map(dto, entityClass);
  }

  public <E extends BaseEntity, D extends BaseDto> E mergeDto(D dto, E entity,
                                                              Class<E> entityClass) {
    return mapperFacade.map(dto, entityClass);
  }

  public <S, D> D map(S sourceObject, Class<D> destinationClass) {
    return sourceObject == null ? null : mapperFacade.map(sourceObject, destinationClass);
  }

  public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
    return mapperFacade.mapAsList(source, destinationClass);
  }

  public <E extends BaseEntity, D extends BaseDto> D loadDto(Class<E> entityClass, Class<D> dtoClass,
                                                             Serializable id) {
    return map(load(entityClass, id), dtoClass);
  }


  public <E extends BaseEntity, D extends BaseDto> List<D> getAllDtos(Class<E> entityClass,
                                                                      Class<D> dtoClass
  ) {
    return mapAsList(getAll(entityClass), dtoClass);
  }

}

