package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.server.service.BaseServiceImpl;
import com.onedeveloperstudio.jobskills.server.dao.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 22.07.14
 */
@Service
public class JobServiceImpl extends BaseServiceImpl implements JobService {
  @Autowired
  private JobRepository jobRepository;

  @Override
  public List loadAll() {
    return jobRepository.findAll();
  }
}
