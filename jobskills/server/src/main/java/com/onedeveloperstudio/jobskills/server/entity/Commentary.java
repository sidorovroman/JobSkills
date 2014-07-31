package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.security.UserAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 25.07.14
 */
@Table(name = "commentary")
@Entity(name="commentary")
public class Commentary  extends BaseEntity {
  private UserAccount author;
  private Date addDate;
  private String message;
  private Commentary head;
  private List<Commentary> children;
  private List<Vote> votes;

  @ManyToOne
  public UserAccount getAuthor() {
    return author;
  }

  public void setAuthor(UserAccount author) {
    this.author = author;
  }

  @Column(nullable = false, name = "adddate")
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

  @OneToMany
  public List<Vote> getVotes() {
    return votes;
  }

  public void setVotes(List<Vote> votes) {
    this.votes = votes;
  }
}
