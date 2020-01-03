package com.cloud.config;

import lombok.Data;

import java.util.List;

/**
 * @author kxj
 * @date 2020/1/3 22:11
 * @Desc 分页封装的数据
 */
@Data
public class PagingResult<T> {
    private Long total;
    private List<T> data;
}
