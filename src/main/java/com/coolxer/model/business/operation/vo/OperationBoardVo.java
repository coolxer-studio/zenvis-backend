package com.coolxer.model.business.operation.vo;

import com.coolxer.dao.mysql.entity.OperationBoard;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;

/**
 * 资产规则VO
 */
@Data
public class OperationBoardVo implements Serializable {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Long id;

    private String panelIcon;

    private String panelTitle;

    private String panelView;

    public OperationBoardVo(OperationBoard operationBoard) {
        if (operationBoard == null) {
            return;
        }

        // 基本信息
        this.id = operationBoard.getId() != null ? operationBoard.getId().longValue() : null;

        // 显示相关字段
        this.panelIcon = operationBoard.getIcon();
        this.panelTitle = operationBoard.getTitle();
        this.panelView = operationBoard.getView();

    }

} 