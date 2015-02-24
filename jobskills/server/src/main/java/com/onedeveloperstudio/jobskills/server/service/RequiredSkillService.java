package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.server.service.BaseCommentaryService;
import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.core.server.service.VoteService;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 25.08.14
 */
public interface RequiredSkillService extends BaseService<RequiredSkillDto>,
                                              VoteService,
                                              BaseCommentaryService {
  List<RequiredSkillDto> loadAllbyJob(Long jobId, Pageable pageable);
}
