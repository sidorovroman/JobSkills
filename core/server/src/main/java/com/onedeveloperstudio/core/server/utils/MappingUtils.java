package com.onedeveloperstudio.core.server.utils;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;

/**
 * User: y.zakharov
 * Date: 21.08.14
 */
public class MappingUtils {
  public static SysUserDto sysUserToDto(SysUserEntity userEntity){
    SysUserDto sysUserDto = new SysUserDto();
    sysUserDto.setId(userEntity.getId());
    sysUserDto.setUserName(userEntity.getUsername());
    sysUserDto.setEmail(userEntity.getEmail());
    sysUserDto.setPassword(userEntity.getPassword());
    return sysUserDto;
  }

  public static SysUserEntity sysUserToEntity(SysUserDto dto){
    SysUserEntity entity = new SysUserEntity();
    entity.setId(dto.getId());
    entity.setUsername(dto.getUserName());
    entity.setEmail(dto.getEmail());
    entity.setPassword(dto.getPassword());
    return entity;
  }

}
