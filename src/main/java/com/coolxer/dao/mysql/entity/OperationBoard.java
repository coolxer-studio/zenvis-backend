package com.coolxer.dao.mysql.entity;

import com.coolxer.dao.mysql.constant.MysqlFinalTableName;
import com.coolxer.model.business.operation.dto.OperationBoardDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 运营看板表
 */

@Data
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = MysqlFinalTableName.T_SHARE_OPERATION_BOARD)
public class OperationBoard extends BaseEntity {

    /**
     * 一组中的上个board
     */
    @Column(name = "last_board")
    private Long lastBoard;

    /**
     * 一组中的下个board
     */
    @Column(name = "next_board")
    private Long nextBoard;

    @Column
    private String policy;

    @Column
    private String event;
    @Column
    private String metrics;

    /**
     * 判断条件，json格式字符串
     */
    @Column(columnDefinition = "TEXT")
    private String conditions;

    /**
     * icon
     */
    @Column
    private String icon;
    @Column
    private String title;
    @Column
    private String view;

    /**
     * 从DTO更新实体属性
     *
     * @param operationBoardDto 运营看板DTO
     */
    public void updateFromDto(OperationBoardDto operationBoardDto) {
        if (operationBoardDto == null) {
            return;
        }

        // 更新关联关系字段
        if (operationBoardDto.getLastBoard() != null && operationBoardDto.getLastBoard() > 0) {
            this.lastBoard = operationBoardDto.getLastBoard();
        }

        // 更新核心业务字段
        if (operationBoardDto.getConditions() != null) {
            this.conditions = operationBoardDto.getConditions();
        }

        if (operationBoardDto.getPolicy() != null) {
            this.policy = operationBoardDto.getPolicy();
        }

        if (operationBoardDto.getEvent() != null) {
            this.event = operationBoardDto.getEvent();
        }

        if (operationBoardDto.getMetrics() != null) {
            this.metrics = operationBoardDto.getMetrics();
        }

        // 更新显示相关字段
        if (operationBoardDto.getPanelTitle() != null) {
            this.title = operationBoardDto.getPanelTitle();
        }

        if (operationBoardDto.getPanelView() != null) {
            this.view = operationBoardDto.getPanelView();
        }
    }
}
