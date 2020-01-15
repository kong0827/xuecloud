package com.cloud.dao;


import com.cloud.entity.ExceptionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lee
 * @date 2020/1/8 23:14
 */
@Repository
public interface ExceptionLogRepository extends JpaRepository<ExceptionLog, Long> {
}
