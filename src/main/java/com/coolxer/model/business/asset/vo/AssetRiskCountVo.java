package com.coolxer.model.business.asset.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class AssetRiskCountVo implements Serializable {
    private List<String> yaxisData = new ArrayList();
    private List<String> seriesDataNone = new ArrayList();
    private List<String> seriesDataLow = new ArrayList();
    private List<String> seriesDataMedium = new ArrayList();
    private List<String> seriesDataHigh = new ArrayList();
    private List<String> seriesDataExtreme = new ArrayList();

} 