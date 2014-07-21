package com.onedeveloperstudio.core.server.dao;

import com.onedeveloperstudio.core.common.dto.Dto;

/**
 * DAO для справочника пользователей
 */
public interface SysUserDao extends Dao {

  /**
   * Загружает данные по имени пользователя
   * @param dtoClass
   * @param name имя пользователя
   * @param <D>
   * @return
   */
  <D extends Dto> D loadByName(Class<D> dtoClass, String name);
}
