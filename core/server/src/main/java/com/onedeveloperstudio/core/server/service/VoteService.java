package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.common.dto.SysUserDto;

/**
 * User: User
 * Date: 20.09.14
 */
public interface VoteService {
  void vote(Long entityId, VoteState state);
}
