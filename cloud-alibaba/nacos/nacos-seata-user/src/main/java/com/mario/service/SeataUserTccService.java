package com.mario.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface SeataUserTccService {

    @TwoPhaseBusinessAction(name = "prepare", commitMethod = "confirm", rollbackMethod = "cancel")
    void prepare(@BusinessActionContextParameter("id") int id, @BusinessActionContextParameter("score") int score) throws Exception;

    boolean confirm(BusinessActionContext context);

    boolean cancel(BusinessActionContext context);

}
