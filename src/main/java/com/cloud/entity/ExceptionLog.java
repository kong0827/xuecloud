package com.cloud.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Lee
 * @date 2020/1/8 23:03
 */
@Data
@Entity
@Table(name = "exceptionlog")
public class ExceptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column
    private  String ip;
    @Column
    private  String ipsource;
    @Column
    private  String description;
    @Column
    private  String browsertype;
    @Column
    private  String reqparam;
    @Column
    private  String excdetailt;
    @Column
    private  Date createdate;
    @Column
    private String remark;

}
