package com.coolxer.service.business.operation;

import com.coolxer.model.business.operation.dto.OperationBoardDto;
import com.coolxer.model.business.operation.vo.OperationBoardVo;

import java.util.List;

public interface OperationBoardService {

    /**
     * 创建
     */
    Boolean add(OperationBoardDto operationBoardDto);

    /**
     * 删除
     */
    void delete(Long id);

    List<List<OperationBoardVo>> getAll();

    Object getChartById(long id);
}