package com.onedeveloperstudio.core.server.entity;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * User: y.zakharov
 * Date: 25.07.14
 */
@Table(name = "vote")
@Entity(name="vote")
public class Vote extends BaseEntity {
  private SysUserEntity user;
  private Long voteDate;
  private VoteState state;

  @ManyToOne
  public SysUserEntity getUser() {
    return user;
  }

  public void setUser(SysUserEntity user) {
    this.user = user;
  }

  @Column(name = "votedate")
  public Long getVoteDate() {
    return voteDate;
  }

  public void setVoteDate(Long voteDate) {
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
