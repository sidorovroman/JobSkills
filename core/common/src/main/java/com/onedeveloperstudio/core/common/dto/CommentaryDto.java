package com.onedeveloperstudio.core.common.dto;

import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 01.08.14
 */
public class CommentaryDto extends RatedDto {
  private Date addDate;
  private String message;
  private CommentaryDto head;
  private List<CommentaryDto> children;
  private List<VoteDto> votes;

  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public CommentaryDto getHead() {
    return head;
  }

  public void setHead(CommentaryDto head) {
    this.head = head;
  }

  public List<CommentaryDto> getChildren() {
    return children;
  }

  public void setChildren(List<CommentaryDto> children) {
    this.children = children;
  }

  public List<VoteDto> getVotes() {
    return votes;
  }

  public void setVotes(List<VoteDto> votes) {
    this.votes = votes;
  }
}
