package com.cloud.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Lee
 * @date 2020/1/10 22:55
 */
@Data
@Entity
@Table(name = "operationlog")
public class OperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(name = "ip",columnDefinition = "varchar(20)")
    private  String ip;
    @Column(name = "ipSource",columnDefinition = "varchar(250)")
    //请求地址来源——>省市区
    private  String ipSource;
    @Column(name = "description",columnDefinition = "varchar(2000)")
    private  String description;
    @Column(name = "browserType",columnDefinition = "varchar(250)")
    //发出请求的浏览器类型
    private  String browserType;
    @Column(name = "reqParam",columnDefinition = "varchar(250)")
    //请求的参数
    private  String reqParam;
    @Column
    private LocalDateTime createDate;
    @Column(name = "reMark",columnDefinition = "varchar(250)")
    private String reMark;
}
