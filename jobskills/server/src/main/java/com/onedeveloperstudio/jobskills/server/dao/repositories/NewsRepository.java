package com.onedeveloperstudio.jobskills.server.dao.repositories;

import com.onedeveloperstudio.jobskills.server.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: y.zakharov
 * Date: 03.09.14
 */
public interface NewsRepository extends JpaRepository<News, Long> {
}
