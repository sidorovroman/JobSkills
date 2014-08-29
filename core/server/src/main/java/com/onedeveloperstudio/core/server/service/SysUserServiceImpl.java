package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;
import com.onedeveloperstudio.core.server.repository.UserRepository;
import com.onedeveloperstudio.core.server.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * User: y.zakharov
 * Date: 21.08.14
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDto> implements SysUserService {
  @Autowired
  private UserRepository userRepository;

  @PostConstruct
  private  void init(){
    AppObjDict dict = AppObjDict.getInstance();
    AppObj appobj = dict.getAppObj("sysUser");
    setAppObj(appobj);
  }

  @Override
  public SysUserDto loadByUsername(String username) {
    SysUserEntity sysUserEntity = userRepository.findOneByUsername(username);
    return MappingUtils.sysUserToDto(sysUserEntity);
  }

  @Override
  public SysUserDto loadByUsernameAndPassword(String username, String password) {
    SysUserEntity sysUserEntity = userRepository.findOneByUsernameAndPassword(username, password);
    return MappingUtils.sysUserToDto(sysUserEntity);
  }

  @Override
  public SysUserDto loadByEmail(String email) {
    SysUserEntity sysUserEntity = userRepository.findOneByEmail(email);
    return MappingUtils.sysUserToDto(sysUserEntity);
  }
}
