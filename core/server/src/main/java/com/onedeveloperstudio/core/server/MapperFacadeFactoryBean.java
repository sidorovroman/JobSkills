package com.onedeveloperstudio.core.server;

import com.onedeveloperstudio.core.server.entity.BaseEntity;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;
import ma.glasnost.orika.unenhance.HibernateUnenhanceStrategy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * User: y.zakharov
 * Date: 18.07.14
 */
public class MapperFacadeFactoryBean implements FactoryBean<MapperFacade> {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public MapperFacade getObject() {
    DefaultMapperFactory.Builder builder = new DefaultMapperFactory.Builder();
    builder.unenhanceStrategy(new HibernateUnenhanceStrategy());
    MapperFactory mapperFactory = builder.build();
//    mapperFactory.registerMapper(new MergingMapper());
    for (String entityClassName : sessionFactory.getAllClassMetadata().keySet()) {
      try {
        Class<?> entityClass = Class.forName(entityClassName);
        if (BaseEntity.class.isAssignableFrom(entityClass)) {
          Class<BaseEntity> baseEntityClass = (Class<BaseEntity>) entityClass;
          Type<BaseEntity> baseEntityType = TypeFactory.valueOf(baseEntityClass);
          mapperFactory.registerObjectFactory(new EntityObjectFactory<>(baseEntityClass), baseEntityType);
        }
      } catch (ClassNotFoundException e) {
        throw new IllegalStateException(e);
      }
    }

    return mapperFactory.getMapperFacade();
  }

  @Override
  public Class<?> getObjectType() {
    return MapperFacade.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  private class EntityObjectFactory<Entity extends BaseEntity> implements ObjectFactory<Entity> {
    private EntityObjectFactory(Class<Entity> entityClass) {
      this.entityClass = entityClass;
    }

    private Class<Entity> entityClass;

    @Override
    public Entity create(Object source, MappingContext mappingContext) {
      if (source instanceof BaseEntity) {
        BaseEntity sourceDto = (BaseEntity) source;
        Session session = getSession();

//        Entity entity;
//        if(sourceDto.getId() == null){
//          entity = createNewEntity();
//          getSession().save(entity);
//        }else {
//          entity = getEntity(sourceDto.getId());
//          if(entity == null){
//
//          }
//        }
//
        if (sourceDto.getId() == null) {
          return (Entity) session.merge(BeanUtils.instantiate(entityClass));
        } else {
          return (Entity) session.load(entityClass, sourceDto.getId());
        }
      }
      return BeanUtils.instantiateClass(entityClass);
    }

    private Entity getEntity(Serializable id) {
      return (Entity) getSession().get(entityClass, id);
    }

    private Entity createNewEntity() {
      Entity newEntity = BeanUtils.instantiate(entityClass);
      getSession().save(newEntity);
      return newEntity;
    }
  }
  private static class MergingMapper extends CustomMapper<Collection<BaseEntity>, Collection<BaseEntity>> {

    public void mapAtoB(Collection<BaseEntity> a, Collection<BaseEntity> b, MappingContext context) {
      merge(a, b, context);
    }

    private Collection<BaseEntity> merge(Collection<BaseEntity> srcDtos, Collection<BaseEntity> dstEntities, MappingContext context) {
      Set<Serializable> ids = new HashSet<>(srcDtos.size());

      Type<BaseEntity> sourceType = context.getResolvedSourceType().getNestedType(0);
      Type<BaseEntity> destinationType = context.getResolvedDestinationType().getNestedType(0);

      for (BaseEntity memberDto : srcDtos) {
        BaseEntity memberEntity = findEntity(dstEntities, memberDto.getId());
        if (memberEntity == null) {
          dstEntities.add( mapperFacade.map(memberDto, sourceType, destinationType, context));
        } else {
          mapperFacade.map(memberDto, memberEntity);
        }
        ids.add(memberDto.getId());
      }

      for (Iterator<BaseEntity> iterator = dstEntities.iterator(); iterator.hasNext(); ) {
        BaseEntity dstEntity = iterator.next();
        if (dstEntity!=null && !ids.contains(dstEntity.getId())) {
          iterator.remove();
        }
      }

      return dstEntities;
    }

    public static BaseEntity findEntity(Collection<BaseEntity> dstEntities, Serializable id) {
      for (BaseEntity dstEntity : dstEntities) {
        if (id.equals(dstEntity.getId())) {
          return dstEntity;
        }
      }
      return null;
    }


  }

}
