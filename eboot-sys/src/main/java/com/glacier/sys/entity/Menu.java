package com.glacier.sys.entity;

import com.glacier.common.core.entity.BaseTreeEntity;
import lombok.*;

/**
 * @author hebin
 * @version 1.0
 * @description 菜单
 * @date 2019-10-09 11:03
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu extends BaseTreeEntity<Menu> {

    private static final long serialVersionUID = 1207728347319595982L;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 端点路径
     */
    private String url;
    /**
     * 权限
     */
    private String perms;
    /**
     * 1 目录  2 端点  3 权限标识
     */
    private int type;
    /**
     * 端点打开方式  默认 1 正常  2 iframe  3 新窗口
     */
    private int open;
    /**
     * 1 正常  2 隐藏
     */
    private int status;
    /**
     * 图标
     */
    private String icon;
}
