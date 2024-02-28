package com.mario.service.impl;

import com.mario.common.SeataOrder;
import com.mario.feign.OrderServiceFeign;
import com.mario.feign.ResourceServiceFeign;
import com.mario.feign.UserServiceFeign;
import com.mario.service.SeataService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author zxz
 * @date 2024年02月23日 11:18
 */
@Service
@Slf4j
public class SeataServiceImpl implements SeataService {

    UserServiceFeign userServiceFeign;
    ResourceServiceFeign resourceServiceFeign;


    OrderServiceFeign orderServiceFeign;

    @Autowired
    public void setOrderServiceFeign(OrderServiceFeign orderServiceFeign) {
        this.orderServiceFeign = orderServiceFeign;
    }

    @Autowired
    public void setResourceServiceFeign(ResourceServiceFeign resourceServiceFeign) {
        this.resourceServiceFeign = resourceServiceFeign;
    }

    @Autowired
    public void setUserServiceFeign(UserServiceFeign userServiceFeign) {
        this.userServiceFeign = userServiceFeign;
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void shopping(int resourceId, int cost, int userId) throws Exception {

        SeataOrder seataOrder = new SeataOrder();
        Random random = new Random();
        int i = random.nextInt(100000);
        seataOrder.setId(i);
        seataOrder.setCost(cost);
        seataOrder.setCustomerId(userId);
        seataOrder.setResourceId(resourceId);
        //创建订单
        orderServiceFeign.createOrder(seataOrder);

        //减库存
        resourceServiceFeign.disCountById(seataOrder.getResourceId(), 1);

        //加用户积分
        userServiceFeign.incScore(seataOrder.getCustomerId(), cost);

    }


    @Override
    public void testIncScore(int id, int score) throws Exception {
        userServiceFeign.incScore(id, score);
    }

    @Override
    public void testGetResource(int id) {
        resourceServiceFeign.selectById(id);
    }

    @Override
    public void testDecrResource(int id, int count) throws Exception {
        resourceServiceFeign.disCountById(id, count);
    }

    @Override
    public void testCreateOrder(SeataOrder seataOrder) {
        orderServiceFeign.createOrder(seataOrder);
    }


}
