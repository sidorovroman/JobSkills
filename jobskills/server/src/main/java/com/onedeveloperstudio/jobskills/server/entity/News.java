package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.common.util.FieldSize;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.swing.*;
import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 02.09.14
 */
@Entity
public class News extends BaseEntity {
  private String caption;
  private String body;
  private Date addDate;
  private SysUserEntity author;
  private String link;
  private List<Commentary> commentaries;
  private List<Vote> votes;

  @Column(nullable = false, length = FieldSize.CAPTION)
  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Column(nullable = false, name = "adddate")
  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

  @ManyToOne
  public SysUserEntity getAuthor() {
    return author;
  }

  public void setAuthor(SysUserEntity author) {
    this.author = author;
  }

  @Column(nullable = false, length = FieldSize.LINK)
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @OneToMany
  public List<Commentary> getCommentaries() {
    return commentaries;
  }

  public void setCommentaries(List<Commentary> commentaries) {
    this.commentaries = commentaries;
  }

  @OneToMany
  public List<Vote> getVotes() {
    return votes;
  }

  public void setVotes(List<Vote> votes) {
    this.votes = votes;
  }
}
