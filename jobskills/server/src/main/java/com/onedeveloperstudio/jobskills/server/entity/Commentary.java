package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.user.AuthUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 25.07.14
 */
@Entity
public class Commentary  extends BaseEntity {
  private AuthUser author;
  private Date addDate;
  private String message;
  private Commentary head;
  private List<Commentary> children;

  @ManyToOne
  public AuthUser getAuthor() {
    return author;
  }

  public void setAuthor(AuthUser author) {
    this.author = author;
  }

  @Column(nullable = false)
  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

  @Column(nullable = false, length = 1000)
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @ManyToOne
  public Commentary getHead() {
    return head;
  }

  public void setHead(Commentary head) {
    this.head = head;
  }

  @OneToMany(mappedBy = "head")
  public List<Commentary> getChildren() {
    return children;
  }

  public void setChildren(List<Commentary> children) {
    this.children = children;
  }
}
