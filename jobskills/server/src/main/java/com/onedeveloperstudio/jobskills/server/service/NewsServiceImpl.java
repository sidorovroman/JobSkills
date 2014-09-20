package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.service.BaseServiceImpl;
import com.onedeveloperstudio.jobskills.common.VoteState;
import com.onedeveloperstudio.jobskills.common.dto.NewsDto;
import com.onedeveloperstudio.jobskills.common.dto.VoteDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * User: User
 * Date: 20.09.14
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsDto> implements NewsService {

  @Transactional
  @Override
  public void vote(SysUserDto user, Long entityId, VoteState state) {
    NewsDto dto = load(entityId);
    VoteDto vote = new VoteDto();
    vote.setVoteDate(new Date());
    vote.setState(state);
    vote.setUser(user);
    dto.getVotes().add(vote);
    update(dto);
  }
}
