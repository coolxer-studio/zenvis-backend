package com.coolxer.model.business.asset.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class AssetLevelCountVo implements Serializable {

    private List<String> yaxisData = new ArrayList<>();
    private List<String> seriesDataAuxiliary = new ArrayList<>();
    private List<String> seriesDataGeneral = new ArrayList<>();
    private List<String> seriesDataMinor = new ArrayList<>();
    private List<String> seriesDataImportant = new ArrayList<>();
    private List<String> seriesDataCritical = new ArrayList<>();

} 