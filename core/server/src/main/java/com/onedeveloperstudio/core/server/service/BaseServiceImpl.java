package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.dto.BaseDto;
import com.onedeveloperstudio.core.server.dao.Dao;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.Entity;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BaseServiceImpl<D extends BaseDto> implements com.onedeveloperstudio.core.common.handler.BaseService<D> {

  @Autowired
  private BeanFactory beanFactory;

  @Autowired
  private Dao<? extends BaseEntity> dao;

  private AppObj appObj;
  private Class<D> dtoClass;

  @Override
  public D insert(D dto) {
    return save(dto, true);
  }

  @Override
  public D update(D dto) {
    return save(dto, false);
  }

  @Transactional(readOnly = true)
  @Override
  public D load(Long id) {
    return (D) getDao().loadDto(getDtoClass(), id);
  }

  protected D save(D dto, boolean isNew) {
    return getEntityDao().saveDto(dto);
  }



  protected Class<? extends Entity> getEntityClass() {
    try {
      return (Class<? extends Entity>) Class.forName(appObj.getProperty(AppObj.ENTITY));
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  protected Class<? extends BaseDto> getDtoClass() {
    try {
      return (Class<? extends BaseDto>) Class.forName(appObj.getProperty(AppObj.EDIT_DTO));
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public <Entity extends BaseEntity> Dao<Entity> getEntityDao() {
    return (Dao<Entity>) dao;
  }

  public Dao<? extends BaseEntity> getDao() {
    return dao;
  }

  public void setDao(Dao<? extends BaseEntity> dao) {
    this.dao = dao;
  }

  @Override
  public void delete(Long id) {
    getEntityDao().remove(id);
  }

  public AppObj getAppObj() {
    return appObj;
  }

  public void setAppObj(AppObj appObj) {
    this.appObj = appObj;
    try {
      getDao().setType((Class<? extends Entity>)Class.forName(appObj.getProperty(AppObj.ENTITY)));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public BeanFactory getBeanFactory() {
    return beanFactory;
  }

  public void setBeanFactory(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }
}
