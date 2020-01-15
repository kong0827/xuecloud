package com.cloud.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Column(length = 250)
    private  String ip;
    @Column(length = 250)
    //请求的地址——>省市区
    private  String ipSource;
    @Column(length = 250)
    private  String description;
    @Column(length = 250)
    //请求的浏览器类型
    private  String browserType;
    @Column(length = 250)
    private  String reqParam;
    @Column(length = 2000)
    //抛出的异常详情
    private  String excDetail;
    @Column
    private LocalDateTime createDate;
    @Column(length = 250)
    private String remark;
}
