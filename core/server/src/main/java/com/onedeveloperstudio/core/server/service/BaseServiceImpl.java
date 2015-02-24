package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.dto.BaseDto;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import org.dozer.Mapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class BaseServiceImpl<D extends BaseDto> implements BaseService<D> {

  public BaseServiceImpl() {
  }

  public BaseServiceImpl(Class<? extends BaseEntity> entityClass, Class<D> dtoClass) {
    this.entityClass = entityClass;
    this.dtoClass = dtoClass;
  }

  @Autowired
  private BeanFactory beanFactory;

  @Autowired
  private Mapper mapper;

  private AppObj appObj;
  private JpaRepository repository;
  private Class<? extends BaseEntity> entityClass;
  private Class<D> dtoClass;

  @Override
  @Secured("ROLE_USER")
  @Transactional
  public D insert(D dto) {
    save(dto);
    return dto;
  }

  @Transactional
  public void save(D dto) {
    BaseEntity entity = mapper.map(dto, entityClass);
    repository.save(entity);
  }

  @Override
  @Secured("ROLE_USER")
  @Transactional
  public D update(D dto) {
    save(dto);
    return dto;
  }

  @Override
  @Transactional(readOnly = true)
  public D load(Long id) {
    Object entity = repository.findOne(id);
    if (entity == null){
      return null;
    }
    D result = mapper.map(entity, dtoClass);
    return result;
  }

  @Override
  @Transactional(readOnly = true)
  public List<D> loadAll() {
    List list = repository.findAll();
    List<D> result = new ArrayList<>(list.size());
    for (Object entity : list) {
      result.add(mapper.map(entity, dtoClass));
    }
    return result;
  }

  @Override
  public List<D> loadAny(Pageable pageRequest) {
    List list = repository.findAll(pageRequest).getContent();
    List<D> result = new ArrayList<>(list.size());
    for (Object entity : list) {
      result.add(mapper.map(entity, dtoClass));
    }
    return result;
  }

  @Override
  public void delete(Long id) {
  }

  @Override
  public AppObj getAppObj() {
    return appObj;
  }

  @Override
  public void setAppObj(AppObj appObj) {
    this.appObj = appObj;
    try {
      entityClass = (Class<? extends BaseEntity>) Class.forName(appObj.getProperty(AppObj.ENTITY));
      dtoClass = (Class<D>) Class.forName(appObj.getProperty(AppObj.EDIT_DTO));
      setRepository((JpaRepository) beanFactory.getBean(Class.forName(appObj.getProperty(AppObj.REPOSITORY))));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public JpaRepository getRepository() {
    return repository;
  }

  @Override
  public void setRepository(JpaRepository repository) {
    this.repository = repository;
  }
}
