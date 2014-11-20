package com.onedeveloperstudio.core.common.dto;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 18.11.14
 */
public abstract class RatedDto extends BaseDto {
  private List<VoteDto> votes;

  public List<VoteDto> getVotes() {
    return votes;
  }

  public void setVotes(List<VoteDto> votes) {
    this.votes = votes;
  }
}
