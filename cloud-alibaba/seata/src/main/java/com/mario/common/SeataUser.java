package com.mario.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zxz
 * @date 2024年02月23日 10:25
 */
@Data
@Accessors(chain = true)
public class SeataUser implements Serializable {

    private int id;

    private int score;

    private String name;
}
