package com.onedeveloperstudio.core.server.repository;

import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: y.zakharov
 * Date: 21.08.14
 */
public interface UserRepository extends JpaRepository<SysUserEntity, Long> {
  SysUserEntity findOneByUsername(String username);
  SysUserEntity findOneByUsernameAndPassword(String username, String password);
}
