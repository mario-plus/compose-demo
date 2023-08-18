package com.mario.listener.abs.impl;


import com.mario.listener.abs.ZkListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCacheListenerBuilder;

import java.nio.charset.StandardCharsets;

/**
 * @author zxz
 * @date 2023年08月18日 17:15
 */
@Slf4j
public class ZkChangeListener extends ZkListener implements CuratorCacheListenerBuilder.ChangeListener {

    @Override
    public void event(ChildData oldNode, ChildData node) {
        log.info("原始数据：" + new String(oldNode.getData(), StandardCharsets.UTF_8));
        log.info("新数据：" + new String(node.getData(), StandardCharsets.UTF_8));
    }

}
