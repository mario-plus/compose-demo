package mario.service;

import mario.entity.SeataResource;

public interface SeataResourceService {

    SeataResource selectById(int id);

    void disCountById(int id, int count) throws Exception;

}
