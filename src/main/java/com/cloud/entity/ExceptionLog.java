package com.cloud.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Lee
 * @date 2020/1/8 23:03
 */
@Data
@Entity
@ApiModel("异常实体")
@Table(name = "exceptionlog")
public class ExceptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(name = "ip",columnDefinition = "varchar(20)")
    private  String ip;
    @Column(name = "ipSource",columnDefinition = "varchar(250)")
    /**
     *请求的地址——>省市区
     */
    private  String ipSource;
    @Column(name = "description",columnDefinition = "varchar(250)")
    private  String description;
    @Column(name = "browserType",columnDefinition = "varchar(250)")
    /**
     * 请求的浏览器类型
     */
    private  String browserType;
    @Column(name = "reqParam",columnDefinition = "varchar(250)")
    private  String reqParam;
    @Column(name = "exceptionDetail",columnDefinition = "varchar(2000)")
    /**
     * 抛出的异常详情
     */
    private  String excDetail;
    @Column
    private LocalDateTime createDate;
}
