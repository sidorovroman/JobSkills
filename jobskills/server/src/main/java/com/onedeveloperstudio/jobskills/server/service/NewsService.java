package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.server.service.BaseCommentaryService;
import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.core.server.service.VoteService;
import com.onedeveloperstudio.jobskills.common.dto.NewsDto;

/**
 * User: User
 * Date: 20.09.14
 */
public interface NewsService extends BaseService<NewsDto>, VoteService, BaseCommentaryService {
  Long countNewsAddedBetweenDates(Long from, Long to);
}
