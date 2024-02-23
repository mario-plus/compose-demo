package com.mario.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zxz
 * @date 2024年02月22日 21:23
 */
@Data
@Accessors(chain = true)
public class SeataResource implements Serializable {

    private int id;

    private String name;

    private int count;

}
