package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;
import com.onedeveloperstudio.core.server.repository.UserRepository;
import com.onedeveloperstudio.core.server.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: y.zakharov
 * Date: 21.08.14
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDto> implements SysUserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public SysUserDto loadByUsername(String username) {
    SysUserEntity sysUserEntity = userRepository.findOneByUsername(username);
    return MappingUtils.SysUserToDto(sysUserEntity);
  }

  @Override
  public SysUserDto loadByUsernameAndPassword(String username, String password) {
    SysUserEntity sysUserEntity = userRepository.findOneByUsernameAndPassword(username, password);
    return MappingUtils.SysUserToDto(sysUserEntity);
  }
}
