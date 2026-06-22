package com.coolxer.dao.mysql.entity;

import com.coolxer.dao.mysql.constant.MysqlFinalTableName;
import com.coolxer.model.business.asset.dto.AssetRuleDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 资产规则表
 */

@Data
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = MysqlFinalTableName.T_SHARE_ASSET_RULE)
public class AssetRule extends BaseEntity {

    /**
     * 规则名称
     */
    @Column
    private String name;

    /**
     * 规则描述
     */
    @Column
    private String description;

    /**
     * 资产对象
     */
    @Column
    private String asset;

    /**
     * 判断条件，json格式字符串
     */
    @Column(columnDefinition = "TEXT")
    private String conditions;

    /**
     * 执行动作
     */
    @Column
    private String action;

    /**
     * 状态
     */
    @Column
    private String status;

    /**
     * 执行结果
     */
    @Column
    private String result;

    /**
     * 从DTO更新实体属性
     *
     * @param assetRuleDto 资产规则DTO
     */
    public void updateFromDto(AssetRuleDto assetRuleDto) {
        if (assetRuleDto == null) {
            return;
        }

        // 更新名称
        if (assetRuleDto.getName() != null) {
            this.name = assetRuleDto.getName();
        }

        // 更新描述
        if (assetRuleDto.getDescription() != null) {
            this.description = assetRuleDto.getDescription();
        }

        // 更新资产类型
        if (assetRuleDto.getAsset() != null) {
            this.asset = assetRuleDto.getAsset().name();
        }

        // 更新判断条件
        if (assetRuleDto.getConditions() != null) {
            this.conditions = assetRuleDto.getConditions();
        }

        // 更新执行动作
        if (assetRuleDto.getAction() != null) {
            this.action = assetRuleDto.getAction().name();
        }

        // 更新状态
        if (assetRuleDto.getStatus() != null) {
            this.status = assetRuleDto.getStatus().name();
        }

        // 更新执行结果
        if (assetRuleDto.getResult() != null) {
            this.result = assetRuleDto.getResult();
        }
    }
}
