package com.mario.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mario.entity.SeataUser;
import com.mario.entity.SeataUserTcc;
import com.mario.mapper.SeataUserMapper;
import com.mario.mapper.SeataUserTccMapper;
import com.mario.service.SeataUserTccService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zxz
 * @date 2024年02月28日 14:14
 */
@Slf4j
@Service
public class SeataUserTccServiceImpl implements SeataUserTccService {

    @Autowired
    SeataUserTccMapper seataUserTccMapper;

    @Autowired
    SeataUserMapper seataUserMapper;

    @Override
    public void prepare(int id, int score) throws Exception {
        log.info("调用prepare......");
        //记录try事务
        String xid = RootContext.getXID();

        QueryWrapper<SeataUserTcc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("xid", xid);
        SeataUserTcc res = seataUserTccMapper.selectOne(queryWrapper);
        if (res != null) {//防悬挂 cancel执行过，拒绝try
            return;
        }
        SeataUser seataUser = seataUserMapper.selectById(id);
        if (seataUser.getScore() + score > 9999) {
            throw new Exception("积分已达上限");
        }
        seataUserMapper.updateById(seataUser.setScore(seataUser.getScore() + score));

        SeataUserTcc seataUserTcc = new SeataUserTcc();
        seataUserTcc.setUser_id(id);
        seataUserTcc.setScore(score);
        seataUserTcc.setState(0);
        seataUserTcc.setXid(xid);
        seataUserTccMapper.insert(seataUserTcc);
    }

    @Override
    public boolean confirm(BusinessActionContext context) {
        log.info("调用confirm......");
        QueryWrapper<SeataUserTcc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("xid", context.getXid());
        int delete = seataUserTccMapper.delete(queryWrapper);
        return delete == 1;

    }

    @Override
    public boolean cancel(BusinessActionContext context) {
        log.info("调用cancel......");
        int id = (int) context.getActionContext().get("id");
        int score = (int) context.getActionContext().get("score");
        //防止空回滚，也就是cancel做db层面的数据更改
        QueryWrapper<SeataUserTcc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("xid", context.getXid());
        SeataUserTcc seataUserTcc1 = seataUserTccMapper.selectOne(queryWrapper);
        if (seataUserTcc1 == null) {//空回滚
            SeataUserTcc seataUserTcc = new SeataUserTcc();
            seataUserTcc.setScore(0);
            seataUserTcc.setState(2);
            seataUserTcc.setUser_id(id);
            seataUserTcc.setXid(context.getXid());
            seataUserTccMapper.insert(seataUserTcc);
            return true;
        }
        if (seataUserTcc1.getState() == 2) {//幂等性问题，如果多次空回滚
            return true;
        }

        SeataUser seataUser = seataUserMapper.selectById(id);
        seataUser.setScore(seataUser.getScore() - score);
        seataUserMapper.updateById(seataUser);//恢复积分

        SeataUserTcc seataUserTcc = new SeataUserTcc();
        seataUserTcc.setScore(0);
        seataUserTcc.setState(2);
        int update = seataUserTccMapper.update(seataUserTcc, queryWrapper);

        return update == 1;
    }

}
