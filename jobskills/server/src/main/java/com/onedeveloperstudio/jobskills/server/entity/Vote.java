package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.user.AuthUser;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * User: y.zakharov
 * Date: 25.07.14
 */
@Entity
public class Vote extends BaseEntity {
  private AuthUser user;
  private Date voteDate;
  private VoteState state;

  @ManyToOne
  public AuthUser getUser() {
    return user;
  }

  public void setUser(AuthUser user) {
    this.user = user;
  }

  public Date getVoteDate() {
    return voteDate;
  }

  public void setVoteDate(Date voteDate) {
    this.voteDate = voteDate;
  }

  @Enumerated
  public VoteState getState() {
    return state;
  }

  public void setState(VoteState state) {
    this.state = state;
  }
}
