package mario.mapper;

import mario.entity.SeataResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zxz
 * @date 2024年02月23日 9:43
 */
@Mapper
@Repository
public interface SeataResourceMapper {

    SeataResource selectById(int id);


    void disCountById(@Param("id") int id, @Param("count") int count);

}
