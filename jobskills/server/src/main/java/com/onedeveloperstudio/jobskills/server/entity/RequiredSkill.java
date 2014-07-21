package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.common.util.FieldSize;
import com.onedeveloperstudio.core.server.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 20.07.14
 */
@Table(name = "requiredSkill")
@Entity(name="requiredSkill")
public class RequiredSkill extends BaseEntity {
  private JobEntity job;
  private String caption;
  private List<WayToImproveSkill> ways;

  @ManyToMany
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
}
