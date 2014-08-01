package com.onedeveloperstudio.jobskills.common.dto;

import com.onedeveloperstudio.core.common.dto.BaseDto;
import com.onedeveloperstudio.jobskills.common.VoteState;

import java.util.Date;

/**
 * User: y.zakharov
 * Date: 01.08.14
 */
public class VoteDto extends BaseDto {
  private Date voteDate;
  private VoteState state;

  public Date getVoteDate() {
    return voteDate;
  }

  public void setVoteDate(Date voteDate) {
    this.voteDate = voteDate;
  }

  public VoteState getState() {
    return state;
  }

  public void setState(VoteState state) {
    this.state = state;
  }
}
