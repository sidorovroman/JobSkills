package com.onedeveloperstudio.jobskills.server.dao.repositories;

import com.onedeveloperstudio.jobskills.server.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 22.07.14
 */
public interface JobRepository extends JpaRepository<JobEntity, Long> {
  List<JobEntity> findAllByParent(JobEntity parent);
}
