package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.server.service.BaseService;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 22.07.14
 */
public interface JobService extends BaseService<JobDto> {
  List<JobDto> getAllParents();
  Long getCount();
  Long getCountBetweenDates(Long dateFrom, Long dateTo);
}
