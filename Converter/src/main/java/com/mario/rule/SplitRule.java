package com.mario.rule;


import com.mario.enums.DataType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SplitRule extends Rules {


    /**
     * propertyKey
     */
    private String key;


    /**
     * 数据类型
     */
    private DataType type;


    /**
     * 子分割符
     */
    private String split;

    /**
     * 分割后的下标
     */
    private Integer index;


    private List<SplitRule> child;

}
