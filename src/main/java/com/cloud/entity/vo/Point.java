package com.cloud.entity.vo;

import lombok.Data;

/**
 * @author QFu
 * @date 2020/1/17 15:17
 * @description
 */
@Data
public class Point {

    /**
     * 当前城市中心点经度
     */
    private String x;

    /**
     * 当前城市中心点维度
     */
    private String y;
}
