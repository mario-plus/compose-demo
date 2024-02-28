package com.mario.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zxz
 * @date 2024年02月23日 10:25
 */
@Data
@Accessors(chain = true)
public class SeataUserTcc implements Serializable {

    private int user_id;

    private int score;

    /**
     * 0 try
     * 1 confirm
     * 2 cancel
     * */
    private int state;

    private String xid;
}
