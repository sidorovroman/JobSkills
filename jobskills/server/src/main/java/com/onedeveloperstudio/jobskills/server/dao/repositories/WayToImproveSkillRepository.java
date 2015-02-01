package com.onedeveloperstudio.jobskills.server.dao.repositories;

import com.onedeveloperstudio.jobskills.server.entity.WayToImproveSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 01.08.14
 */
public interface WayToImproveSkillRepository  extends JpaRepository<WayToImproveSkill, Long> {
  List<WayToImproveSkill> findAllBySkillsId(Long skillId);
}
