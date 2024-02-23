package com.mario.service.impl;

import com.mario.entity.SeataOrder;
import com.mario.mapper.SeataOrderMapper;
import com.mario.service.SeataOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author zxz
 * @date 2024年02月23日 10:53
 */
@Service
public class SeataOrderServiceImpl implements SeataOrderService {

    SeataOrderMapper seataOrderMapper;

    @Autowired
    public void setSeataOrderMapper(SeataOrderMapper seataOrderMapper) {
        this.seataOrderMapper = seataOrderMapper;
    }

    @Override
    public void createOrder(SeataOrder seataOrder) {
        seataOrderMapper.insert(seataOrder);
    }
}
