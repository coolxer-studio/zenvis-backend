package com.coolxer.service.business.operation;

import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.operation.dto.NetworkEventDto;
import com.coolxer.model.business.operation.vo.NetworkEventVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NetworkEventService {
    boolean add(NetworkEventDto dto);

    boolean delete(String id);

    boolean deleteAll(List<String> ids);

    boolean update(String id, NetworkEventDto dto);

    NetworkEventVo getOne(String id);

    PageRowsVo<NetworkEventVo> getPageList(NetworkEventDto searchCondition);

    long countTotal();

    long countIncrease();

    List<String> getSimilarIds(String term);

    List<String> getSimilarSourceIps(String term);

    List<String> getSimilarTargetIps(String term);
}