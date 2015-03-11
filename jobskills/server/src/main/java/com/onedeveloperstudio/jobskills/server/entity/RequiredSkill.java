package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.common.util.FieldSize;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.Commentary;
import com.onedeveloperstudio.core.server.entity.Vote;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 20.07.14
 */
@Table(name = "requiredskill")
@Entity(name = "requiredskill")
public class RequiredSkill extends BaseEntity {
  private JobEntity job;
  private String caption;
  private String description;
  private SysUserEntity author;
  private List<WayToImproveSkill> ways;
  private List<Commentary> comments;
  private List<Vote> votes;
  private Long addDate;

  @Column(nullable = false, name = "adddate")
  public Long getAddDate() {
    return addDate;
  }

  public void setAddDate(Long addDate) {
    this.addDate = addDate;
  }

  @ManyToMany
  @JoinTable(name = "requiredskill_waytoimproveskill", joinColumns = {
      @JoinColumn(name = "skill_id", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "ways_id",
          nullable = false, updatable = false)})
  public List<WayToImproveSkill> getWays() {
    return ways;
  }

  public void setWays(List<WayToImproveSkill> ways) {
    this.ways = ways;
  }

  @ManyToOne(optional = false)
  public JobEntity getJob() {
    return job;
  }

  public void setJob(JobEntity job) {
    this.job = job;
  }

  @Column(nullable = false, length = FieldSize.CAPTION)
  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  @OneToMany
  public List<Commentary> getComments() {
    return comments;
  }

  public void setComments(List<Commentary> comments) {
    this.comments = comments;
  }

  @Column(nullable = false, length = FieldSize.DESCRIPTION)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "requiredskill_votes", joinColumns = {
      @JoinColumn(name = "skill_id", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "vote_id",
          nullable = false, updatable = false)})
  public List<Vote> getVotes() {
    return votes;
  }

  public void setVotes(List<Vote> votes) {
    this.votes = votes;
  }

  @ManyToOne
  public SysUserEntity getAuthor() {
    return author;
  }

  public void setAuthor(SysUserEntity author) {
    this.author = author;
  }
}
