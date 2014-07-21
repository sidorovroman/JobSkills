package com.onedeveloperstudio.core.common.handler;

import com.onedeveloperstudio.core.common.dto.SysUserDto;

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
