package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 25.08.14
 */
public interface RequiredSkillService extends BaseService<RequiredSkillDto> {
  List<RequiredSkillDto> loadAllbyJob(Long jobId);
}
