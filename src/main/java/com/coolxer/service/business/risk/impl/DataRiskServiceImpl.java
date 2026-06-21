package com.coolxer.service.business.risk.impl;

import com.coolxer.dao.clickhouse.entity.risk.DataRisk;
import com.coolxer.dao.clickhouse.repository.risk.DataRiskRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.business.risk.dto.DataRiskDto;
import com.coolxer.model.business.risk.dto.DataRiskSearchDto;
import com.coolxer.model.business.risk.vo.DataRiskVo;
import com.coolxer.service.business.risk.DataRiskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataRiskServiceImpl implements DataRiskService {

    @Autowired
    private DataRiskRepository dataRiskRepository;

    @Override
    public boolean add(DataRiskDto dto) {
        DataRisk risk = new DataRisk();
        BeanUtils.copyProperties(dto, risk);
        risk.setInsertTime(new Date());
        risk.setUpdateTime(new Date());
        dataRiskRepository.save(risk);
        return true;
    }

    @Override
    public boolean delete(String id) {
        dataRiskRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll(List<String> ids) {
        ids.forEach(this::delete);
        return true;
    }

    @Override
    public boolean update(String id, DataRiskDto dto) {
        Optional<DataRisk> optional = dataRiskRepository.findById(id);
        if (optional.isPresent()) {
            DataRisk risk = optional.get();
            BeanUtils.copyProperties(dto, risk);
            risk.setUpdateTime(new Date());
            dataRiskRepository.save(risk);
            return true;
        }
        return false;
    }

    @Override
    public DataRiskVo getOne(String id) {
        return dataRiskRepository.findById(id).map(DataRiskVo::new).orElse(null);
    }

    @Override
    public PageRowsVo<DataRiskVo> getPageList(DataRiskSearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage() - 1, searchDto.getPerPage());
        Page<DataRisk> page;
        if (searchDto.getOrderBy() != null && searchDto.getOrderBy().equals("asc")) {
            page = dataRiskRepository.findByPageAsc(pageable, searchDto.getId(), searchDto.getUserId(),
                    searchDto.getStartId(), searchDto.getAssetId(), searchDto.getType(),
                    searchDto.getRiskLevel(), searchDto.getNetType(), searchDto.getLanIp(), searchDto.getWanIp(),
                    null, null, null, null);
        } else {
            page = dataRiskRepository.findByPageDesc(pageable, searchDto.getId(), searchDto.getUserId(),
                    searchDto.getStartId(), searchDto.getAssetId(), searchDto.getType(),
                    searchDto.getRiskLevel(), searchDto.getNetType(), searchDto.getLanIp(), searchDto.getWanIp(),
                    null, null, null, null);
        }
        List<DataRiskVo> list = page.getContent().stream().map(DataRiskVo::new).collect(Collectors.toList());
        return new PageRowsVo<>(list, page.getTotalElements());
    }

    @Override
    public List<String> getDistinctLabels() {
        return dataRiskRepository.findDistinctLabels();
    }

    @Override
    public List<String> getSimilarIds(String term) {
        return dataRiskRepository.findLikeIds(term);
    }

    @Override
    public List<String> getSimilarTypes(String term) {
        return dataRiskRepository.findLikeTypes(term);
    }

    @Override
    public long countTotal() {
        return dataRiskRepository.countTotal();
    }

    @Override
    public long countIncrease() {
        return dataRiskRepository.countTodayIncrease();
    }

} 