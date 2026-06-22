package com.coolxer.dao.mysql.repository;

import com.coolxer.dao.mysql.entity.RetrievalRule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据检索管理
 */
public interface RetrievalRuleRepository extends JpaRepository<RetrievalRule, Integer> {

}
