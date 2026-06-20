package com.coolxer.service.dih;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.commons.exception.ApiException;
import com.coolxer.utils.ModelsUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 模型基础服务
 */

@Service
public class AIBaseService {

    public List<Map<String, String>> getDashScope() {
        List<Map<String, String>> resultSet;
        try {
            resultSet = ModelsUtils.getDashScopeModels();
        } catch (IOException e) {
            throw new ApiException(ResultCodeEnum.NO_AUTHORITY.getCode(), "Get DashScope Model failed, " + e.getMessage());
        }
        return resultSet;
    }

}
