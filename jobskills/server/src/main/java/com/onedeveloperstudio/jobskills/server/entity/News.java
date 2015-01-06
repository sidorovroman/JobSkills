package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.common.util.FieldSize;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.Commentary;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;
import com.onedeveloperstudio.core.server.entity.Vote;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
  private Long addDate;
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

  @Column(length = FieldSize.BODY)
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Column(nullable = false, name = "adddate")
  public Long getAddDate() {
    return addDate;
  }

  public void setAddDate(Long addDate) {
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

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "news_commentary", joinColumns = {
      @JoinColumn(name = "news_id", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "commentary_id",
          nullable = false, updatable = false)})
  public List<Commentary> getCommentaries() {
    return commentaries;
  }

  public void setCommentaries(List<Commentary> commentaries) {
    this.commentaries = commentaries;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "news_votes", joinColumns = {
      @JoinColumn(name = "news_id", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "vote_id",
          nullable = false, updatable = false)})
  public List<Vote> getVotes() {
    return votes;
  }

  public void setVotes(List<Vote> votes) {
    this.votes = votes;
  }
}
