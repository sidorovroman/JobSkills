package com.onedeveloperstudio.core.server.utils;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.ULoginUser;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * User: y.zakharov
 * Date: 21.08.14
 */
public class MappingUtils {
  public static SysUserDto sysUserToDto(SysUserEntity userEntity){
    SysUserDto sysUserDto = new SysUserDto();
    if(userEntity == null){
      return null;
    }
    sysUserDto.setId(userEntity.getId());
    sysUserDto.setUserName(userEntity.getUsername());
    sysUserDto.setEmail(userEntity.getEmail());
    sysUserDto.setPassword(userEntity.getPassword());
    sysUserDto.setUserFullName(userEntity.getUserFullName());
    sysUserDto.setBirthday(userEntity.getBirthday());
    sysUserDto.setCity(userEntity.getCity());
    sysUserDto.setCountry(userEntity.getCountry());
    sysUserDto.setNetwork(userEntity.getNetwork());
    sysUserDto.setPhone(userEntity.getPhone());
    sysUserDto.setSex(userEntity.getSex());
    sysUserDto.setRating(userEntity.getRating());
    return sysUserDto;
  }

  public static SysUserEntity sysUserToEntity(SysUserDto dto){
    SysUserEntity entity = new SysUserEntity();
    entity.setId(dto.getId());
    entity.setUsername(dto.getUserName());
    entity.setEmail(dto.getEmail());
    entity.setPassword(dto.getPassword());
    entity.setUserFullName(dto.getUserFullName());
    entity.setBirthday(dto.getBirthday());
    entity.setCity(dto.getCity());
    entity.setCountry(dto.getCountry());
    entity.setNetwork(dto.getNetwork());
    entity.setPhone(dto.getPhone());
    entity.setSex(dto.getSex());
    entity.setRating(dto.getRating());
    return entity;
  }

  public static SysUserDto fromULoginUserToDto(ULoginUser uloginUser){
    SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
    SysUserDto dto = new SysUserDto();
    dto.setUserName(uloginUser.getNickname());
    dto.setEmail(uloginUser.getEmail());
    dto.setPassword(uloginUser.getPassword());
    dto.setUserFullName(uloginUser.getFirst_name() +" " + uloginUser.getLast_name());
    try {
      dto.setBirthday(sdf.parse(uloginUser.getBdate()).getTime());
    } catch (ParseException e) {
      System.out.println("Неверный формат даты: " + uloginUser.getBdate());
    }
    dto.setCity(uloginUser.getCity());
    dto.setCountry(uloginUser.getCountry());
    dto.setNetwork(uloginUser.getNetwork());
    dto.setPhone(uloginUser.getPhone());
    dto.setSex(uloginUser.getSex());
    return dto;
  }
}
