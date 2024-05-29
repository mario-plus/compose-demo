package com.mario.rule;

import com.mario.enums.DataType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class HexRule extends Rules {


    /**
     * 字节为单位，下标
     */
    private Integer startIndex;

    /**
     * 数据长度，如一个值包含多个字节
     */
    private Integer length;


    /**
     * 大端模式（默认）
     */
    @Builder.Default
    private boolean bigEndian = true;

    /**
     * 属性名
     */
    private String key;

    /**
     * 属性类型
     * 默认返回HexStr
     */
    private DataType type;


    /**
     * 子节点，按位解析（大端模式）
     * eg：01011101  startIndex从右往前切分
     */
    private List<HexRule> child;

    /**
     * 添加子节点
     */
    public HexRule addChild(HexRule hexRule) {
        if (child == null) {
            child = new ArrayList<>();
        }
        child.add(hexRule);
        return this;
    }


}
