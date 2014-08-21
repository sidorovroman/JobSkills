package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.server.service.BaseServiceImpl;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 22.07.14
 */
@Service
public class JobServiceImpl extends BaseServiceImpl<JobDto> implements JobService {

  @Transactional(readOnly = true)
  @Override
  public List<JobDto> loadAll() {
    List<JobDto> list = super.loadAll();
    return list;
  }
}
