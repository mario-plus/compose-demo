package com.mario.rule;

import com.mario.enums.DataType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StringRule extends Rules {
    /**
     * 开始index
     */
    private int startIndex;

    /**
     * 字符串长度
     */
    private int length;

    /**
     * propertyKey
     */
    private String key;

    /**
     * 数据类型
     */
    private DataType type;
}
