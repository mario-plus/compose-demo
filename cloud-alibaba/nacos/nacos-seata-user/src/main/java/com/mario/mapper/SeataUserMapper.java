package com.mario.mapper;

import com.mario.entity.SeataUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zxz
 * @date 2024年02月23日 10:28
 */
@Repository
@Mapper
public interface SeataUserMapper {

    SeataUser selectById(@Param("id") int id);


    void incScore(@Param("id") int id, @Param("score") int score);

}
