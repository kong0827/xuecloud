package com.cloud.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author kxj
 * @date 2020/1/3 22:18
 * @Desc 实体类基类
 */
@Data
public class Base {
    // 创建人
    private String creator;
    // 创建时间
    private LocalDate creatTime;
    // 修改人
    private String modifier;
    // 修改时间
    private LocalDate ModifiedTime;
}
