package com.mario.rule;


import com.mario.DataType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SplitRule extends Rules {


    private String key;


    private DataType type;


    /**
     * 首个分隔符不能为空
     * */
    private String split;

    private int index;

    private List<SplitRule> child;

}
