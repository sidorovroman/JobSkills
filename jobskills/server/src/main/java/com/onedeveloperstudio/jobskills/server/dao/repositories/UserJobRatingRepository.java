package com.onedeveloperstudio.jobskills.server.dao.repositories;

import com.onedeveloperstudio.jobskills.server.entity.UserJobRating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: y.zakharov
 * Date: 05.03.15
 */
public interface UserJobRatingRepository extends JpaRepository<UserJobRating, Long> {
  UserJobRating findOneByUserIdAndJobId(Long userId, Long jobId);
}
