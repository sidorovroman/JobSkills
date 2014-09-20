package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.jobskills.common.VoteState;

/**
 * User: User
 * Date: 20.09.14
 */
public interface VoteService {
  void vote(SysUserDto user, Long entityId, VoteState state);
}
