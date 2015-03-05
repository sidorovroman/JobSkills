package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.common.dto.UserJobRatingDto;

/**
 * User: y.zakharov
 * Date: 05.03.15
 */
public interface UserJobRatingService extends BaseService<UserJobRatingDto> {
  UserJobRatingDto findByUserAndJob(SysUserDto user, JobDto job);
}
