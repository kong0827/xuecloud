package com.cloud.entity.vo;

import lombok.Data;

/**
 * @author QFu
 * @date 2020/1/17 15:07
 * @description
 */
@Data
public class ipAddressVo {

    /**
     * 详细地址信息
     */
    private String  address;

    /**
     *返回的主体内容
     */
    private Content content;

    /**
     * 状态
     */
    private  String status;
}
