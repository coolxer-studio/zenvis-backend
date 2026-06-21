package com.coolxer.dao.mysql.repository;

import com.coolxer.dao.mysql.constant.MysqlFinalTableName;
import com.coolxer.dao.mysql.entity.AssetRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetRuleRepository extends JpaRepository<AssetRule, Long>, JpaSpecificationExecutor<AssetRule> {

    @Query(nativeQuery = true,
            value = "SELECT r.* FROM " + MysqlFinalTableName.T_SHARE_ASSET_RULE + " r WHERE " +
                    "(:asset IS NULL OR r.asset = :asset) AND " +
                    "(:action IS NULL OR r.action = :action) AND " +
                    "(:status IS NULL OR r.status = :status) " +
                    "ORDER BY update_time DESC",
            countQuery = "SELECT count(*) FROM " + MysqlFinalTableName.T_SHARE_ASSET_RULE + " r WHERE " +
                    "(:asset IS NULL OR r.asset = :asset) AND " +
                    "(:action IS NULL OR r.action = :action) AND " +
                    "(:status IS NULL OR r.status = :status)")
    Page<AssetRule> findByPage(
            Pageable pageable,
            @Param("asset") String asset,
            @Param("action") String action,
            @Param("status") String status);
} 