package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.common.dto.RatedDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: y.zakharov
 * Date: 18.11.14
 */
public class BaseVoteServiceImlp<D extends RatedDto> extends BaseServiceImpl<D> implements VoteService {

  @Override
  @Transactional
  public void vote(SysUserDto user, Long entityId, VoteState state) {

  }

  @Transactional
  @Override
  public int getVotesCount(Long id) {
    return 0;
  }
}
