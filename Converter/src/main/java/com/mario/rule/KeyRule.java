package com.mario.rule;

import com.mario.DataType;
import com.mario.service.IKeyHandler;
import lombok.*;

/**
 * 消息体中的key
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class KeyRule extends Rules{

    private IKeyHandler keyService;

    private Integer startIndex;

    private Integer length;

    private DataType dataType;

}
