package com.mario.controller;

import com.mario.entity.SeataOrder;
import com.mario.service.SeataOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @date 2024年02月23日 10:55
 */
@RestController
@RequestMapping("order")
public class SeataOrderController {

    SeataOrderService seataOrderService;

    @Autowired
    public void setSeataOrderService(SeataOrderService seataOrderService) {
        this.seataOrderService = seataOrderService;
    }

    @PostMapping("createOrder")
    public void createOrder(@RequestBody SeataOrder seataOrder) {
        seataOrderService.createOrder(seataOrder);
    }
}
