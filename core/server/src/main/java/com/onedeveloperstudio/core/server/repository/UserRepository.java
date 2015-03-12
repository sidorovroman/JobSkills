package com.onedeveloperstudio.core.server.repository;

import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * User: y.zakharov
 * Date: 21.08.14
 */
public interface UserRepository extends JpaRepository<SysUserEntity, Long> {
  SysUserEntity findOneByUsername(String username);
  SysUserEntity findOneByEmail(String email);
  SysUserEntity findOneByUsernameAndPassword(String username, String password);
  Long countByRegdateBetween(Long from, Long to);
}
