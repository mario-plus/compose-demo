package com.mario.service;


import com.mario.entity.SeataUser;


public interface SeataUserService {

    SeataUser selectById(int id);


    void incScore(int id, int score) throws Exception;

}
