package mario.service.impl;

import mario.entity.SeataResource;
import mario.mapper.SeataResourceMapper;
import mario.service.SeataResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zxz
 * @date 2024年02月23日 9:49
 */
@Service
public class SeataResourceServiceImpl implements SeataResourceService {

    @Autowired
    SeataResourceMapper seataResourceMapper;

    @Override
    public SeataResource selectById(int id) {
        return seataResourceMapper.selectById(id);
    }

    @Override
    public void disCountById(int id, int count) throws Exception {
        SeataResource seataResource = seataResourceMapper.selectById(id);
        if (seataResource.getCount() - count < 0) {
            throw new Exception("库存不足");
        }
        seataResourceMapper.disCountById(id, seataResource.getCount() - count);
    }
}
