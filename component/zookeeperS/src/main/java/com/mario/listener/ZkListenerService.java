package com.mario.listener;

import com.mario.listener.abs.ZkListener;

/**
 * @author zxz
 * @date 2023年08月18日 16:31
 * {@link org.apache.curator.framework.recipes.cache.PathChildrenCacheListenerWrapper}
 */
public interface ZkListenerService {


    boolean listForAll(String node, ZkListener zkListener);

    boolean listenForChanges(String node, ZkListener zkListener);

    boolean listenForCreatesAndChanges(String node, ZkListener zkListener);


}
