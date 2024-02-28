package com.mario.service.impl;

import com.mario.entity.SeataUser;
import com.mario.mapper.SeataUserMapper;
import com.mario.service.SeataUserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zxz
 * @date 2024年02月23日 10:33
 */
@Service
public class SeataUserServiceImpl implements SeataUserService {

    SeataUserMapper seataUserMapper;

    @Autowired
    public void setSeataUserMapper(SeataUserMapper seataUserMapper) {
        this.seataUserMapper = seataUserMapper;
    }

    @Override
    public SeataUser selectById(int id) {
        return seataUserMapper.selectById(id);
    }

    @Override
    public void incScore(int id, int score) throws Exception {
        SeataUser seataUser = seataUserMapper.selectById(id);
        if (seataUser.getScore() + score > 9999) {
            throw new Exception("积分已达上限");
        }
        seataUserMapper.incScore(id, seataUser.getScore() + score);
    }
}
