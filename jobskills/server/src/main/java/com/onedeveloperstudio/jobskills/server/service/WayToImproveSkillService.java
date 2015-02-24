package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.server.service.BaseCommentaryService;
import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.core.server.service.VoteService;
import com.onedeveloperstudio.jobskills.common.dto.WayToImproveSkillDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * User: User
 * Date: 20.09.14
 */
public interface WayToImproveSkillService extends BaseService<WayToImproveSkillDto>,
                                                  VoteService,
                                                  BaseCommentaryService {
  public List<WayToImproveSkillDto> loadAllbySkill(Long jobId, Pageable pageable);
}
