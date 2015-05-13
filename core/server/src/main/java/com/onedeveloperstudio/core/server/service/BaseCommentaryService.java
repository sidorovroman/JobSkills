package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.dto.CommentaryDto;

/**
 * User: y.zakharov
 * Date: 24.11.14
 */
public interface BaseCommentaryService {
  public CommentaryDto comment(Long id, CommentaryDto comment);
}
