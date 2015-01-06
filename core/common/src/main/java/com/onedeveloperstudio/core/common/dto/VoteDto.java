package com.onedeveloperstudio.core.common.dto;

import com.onedeveloperstudio.core.common.VoteState;

import java.util.Date;

/**
 * User: y.zakharov
 * Date: 01.08.14
 */
public class VoteDto extends BaseDto {
  private Long voteDate;
  private SysUserDto user;
  private VoteState state;

  public Long getVoteDate() {
    return voteDate;
  }

  public void setVoteDate(Long voteDate) {
    this.voteDate = voteDate;
  }

  public VoteState getState() {
    return state;
  }

  public void setState(VoteState state) {
    this.state = state;
  }

  public SysUserDto getUser() {
    return user;
  }

  public void setUser(SysUserDto user) {
    this.user = user;
  }
}
