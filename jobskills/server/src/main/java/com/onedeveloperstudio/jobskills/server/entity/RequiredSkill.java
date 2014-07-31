package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.common.util.FieldSize;
import com.onedeveloperstudio.core.server.entity.BaseEntity;

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
@Entity(name="requiredskill")
public class RequiredSkill extends BaseEntity {
  private JobEntity job;
  private String caption;
  private List<WayToImproveSkill> ways;
  private List<Commentary> comments;
  private List<Vote> votes;

  @ManyToMany
  @JoinTable(name = "requiredskill_waytoimproveskill", joinColumns = {
      @JoinColumn(name = "ways_id", nullable = false, updatable = false) },
      inverseJoinColumns = { @JoinColumn(name = "skill_id",
          nullable = false, updatable = false) })
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
}
