package com.onedeveloperstudio.core.server.dao;

import com.onedeveloperstudio.core.server.entity.BaseEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * User: y.zakharov
 * Date: 18.07.14
 */
@Repository("simpleDao")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class SimpleDao<Entity extends BaseEntity> extends BaseDao<Entity>  {
  public SimpleDao() {
    super(null);
  }
}

