package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.dto.SysUserDto;

/**
 * User: y.zakharov
 * Date: 21.08.14
 */
public interface SysUserService extends BaseService<SysUserDto> {
  SysUserDto loadByUsername(String username);
  SysUserDto loadByUsernameAndPassword(String username, String password);
  SysUserDto loadByEmail(String email);
  SysUserDto authenticate();
}
