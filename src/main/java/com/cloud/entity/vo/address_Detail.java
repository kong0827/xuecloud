package com.cloud.entity.vo;

import lombok.Data;

/**
 * @author QFu
 * @date 2020/1/17 15:18
 * @description
 */
@Data
public class address_Detail {

    /**
     * 城市
     */
    private String city;
    /**
     * 百度城市代码
     */
    private String city_code;
    /**
     * 区县
     */
    private String district;
    /**
     * 省市
     */
    private String province;
    /**
     * 街道
     */
    private String street;
    /**
     * 门牌号
     */
    private String  street_number;
    /**
     * 地理位置坐标
     */
    private Point point;
}
