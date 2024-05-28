package com.mario.rule;

import com.mario.DataType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StringRule extends Rules {

    private int startIndex;

    private int length;

    private String key;

    private DataType type;
}
