package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.common.util.FieldSize;
import com.onedeveloperstudio.core.server.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * User: y.zakharov
 * Date: 17.07.14
 */
@Table(name = "jobs")
@Entity(name="jobs")
public class JobEntity extends BaseEntity {
  public String caption;
  public JobEntity parent;
  public Set<JobEntity> children;

  @Column(nullable = false, length = FieldSize.CAPTION)
  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  @ManyToOne
  public JobEntity getParent() {
    return parent;
  }

  public void setParent(JobEntity parent) {
    this.parent = parent;
  }

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  public Set<JobEntity> getChildren() {
    return children;
  }

  public void setChildren(Set<JobEntity> children) {
    this.children = children;
  }
}
