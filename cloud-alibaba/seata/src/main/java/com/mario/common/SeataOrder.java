package com.mario.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zxz
 * @date 2024年02月23日 10:43
 */
@Data
@Accessors(chain = true)
public class SeataOrder implements Serializable {

    private int id;

    private int resourceId;

    private int cost;

    private int customerId;
}
