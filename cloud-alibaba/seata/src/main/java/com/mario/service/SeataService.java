package com.mario.service;


import com.mario.common.SeataOrder;

public interface SeataService {

    /**
     * @param resourceId 商品id
     * @param cost       费用
     * @param userId     用户id
     */
    void shopping(int resourceId, int cost, int userId) throws Exception;


    void testIncScore(int id, int score) throws Exception;

    void testGetResource(int id);

    void testDecrResource(int id, int count) throws Exception;


    void testCreateOrder(SeataOrder seataOrder);
}
