package com.onedeveloperstudio.core.server.entity;

import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;
import com.onedeveloperstudio.core.common.VoteState;

import javax.persistence.CascadeType;
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
  private Date voteDate;
  private VoteState state;

  @ManyToOne
  public SysUserEntity getUser() {
    return user;
  }

  public void setUser(SysUserEntity user) {
    this.user = user;
  }

  @Column(name = "votedate")
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
