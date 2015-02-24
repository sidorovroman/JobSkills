package com.onedeveloperstudio.jobskills.server.dao.repositories;

import com.onedeveloperstudio.jobskills.server.entity.RequiredSkill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 01.08.14
 */
public interface RequiredSkillRepository extends JpaRepository<RequiredSkill, Long> {
  List<RequiredSkill> findAllByJobId(Long jobId, Pageable pageable);
}
