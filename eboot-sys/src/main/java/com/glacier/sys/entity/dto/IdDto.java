package com.glacier.sys.entity.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2020-02-12 12:52
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdDto implements Serializable {
    private static final long serialVersionUID = 2074559073473281209L;
    private String id;
}
