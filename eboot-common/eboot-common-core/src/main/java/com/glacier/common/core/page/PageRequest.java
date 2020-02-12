package com.glacier.common.core.page;

import lombok.*;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-14 15:53
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequest<T> implements Serializable {
    private static final long serialVersionUID = 4076080201697869870L;
    /**
     * 当前页码
     */
    private int current = 1;
    /**
     * 每页数量
     */
    private int size = 10;
    /**
     * 查询参数
     */
    private T params;

}
