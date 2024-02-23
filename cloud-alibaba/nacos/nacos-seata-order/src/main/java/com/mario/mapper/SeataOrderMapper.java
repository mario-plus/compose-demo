package com.mario.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mario.entity.SeataOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SeataOrderMapper extends BaseMapper<SeataOrder> {


}
