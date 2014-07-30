package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.security.UserAccount;
import com.onedeveloperstudio.jobskills.common.VoteState;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * User: y.zakharov
 * Date: 25.07.14
 */
@Table(name = "Vote")
@Entity(name="Vote")
public class Vote extends BaseEntity {
  private UserAccount user;
  private Date voteDate;
  private VoteState state;

  @ManyToOne
  public UserAccount getUser() {
    return user;
  }

  public void setUser(UserAccount user) {
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
