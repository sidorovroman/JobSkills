package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.service.BaseServiceImpl;
import com.onedeveloperstudio.jobskills.common.VoteState;
import com.onedeveloperstudio.jobskills.common.dto.VoteDto;
import com.onedeveloperstudio.jobskills.common.dto.WayToImproveSkillDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * User: User
 * Date: 20.09.14
 */

@Service
public class WayToImproveSkillServiceImpl extends BaseServiceImpl<WayToImproveSkillDto> implements WayToImproveSkillService {

  @Transactional
  @Override
  public void vote(SysUserDto user, Long entityId, VoteState state) {
    WayToImproveSkillDto dto = load(entityId);
    VoteDto vote = new VoteDto();
    vote.setVoteDate(new Date());
    vote.setState(state);
    vote.setUser(user);
    dto.getVotes().add(vote);
    update(dto);
  }

}
