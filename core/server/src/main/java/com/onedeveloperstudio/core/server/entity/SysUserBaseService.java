package com.onedeveloperstudio.core.server.entity;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.service.BaseService;

/**
 * Хэндлер справочника пользователей
 */
public interface SysUserBaseService extends BaseService {

  SysUserDto loadByName(String userName);

  /**
   * Отправляет пользователю письмо с его паролем
   * @param userName логин пользователя
   */
/*  void sendPassword(String userName);*/
}
