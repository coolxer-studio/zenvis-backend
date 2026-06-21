package com.coolxer.model.business.asset.dto;

import lombok.Data;

/**
 * 城市信息类
 */
@Data
public class CityInfo {
    /**
     * 区域编码
     */
    private Integer code;

    /**
     * 省份编码
     */
    @com.fasterxml.jackson.annotation.JsonProperty(value = "provinceCode", access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
    @com.fasterxml.jackson.annotation.JsonAlias("province_code")
    private Integer provinceCode;

    /**
     * 省份名称
     */
    private String province;

    /**
     * 城市编码
     */
    @com.fasterxml.jackson.annotation.JsonProperty(value = "cityCode", access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
    @com.fasterxml.jackson.annotation.JsonAlias("city_code")
    private Integer cityCode;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 区县编码
     */
    @com.fasterxml.jackson.annotation.JsonProperty(value = "districtCode", access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
    @com.fasterxml.jackson.annotation.JsonAlias("district_code")
    private Integer districtCode;

    /**
     * 区县名称
     */
    private String district;

    /**
     * 街道
     */
    private String street;
}
