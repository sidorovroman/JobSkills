package com.onedeveloperstudio.jobskills.server.dao.repositories;

import com.onedeveloperstudio.core.server.entity.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: y.zakharov
 * Date: 01.08.14
 */
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
}
