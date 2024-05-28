package com.mario.common;

/**
 * @author zxz
 * @date 2024年04月09日 17:51
 */
public interface IMQProvider<M> {

    /**
     * 初始化客户端
     */
    void initClient(M config);


}
