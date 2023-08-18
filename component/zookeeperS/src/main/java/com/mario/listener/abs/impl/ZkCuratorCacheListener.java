package com.mario.listener.abs.impl;

import com.mario.listener.abs.ZkListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;

import java.nio.charset.StandardCharsets;

/**
 * @author zxz
 * @date 2023年08月18日 17:22
 */
@Slf4j
public class ZkCuratorCacheListener extends ZkListener implements CuratorCacheListener {

    @Override
    public void event(Type type, ChildData oldData, ChildData data) {
        log.info("监测类型：" + type.name());
        log.info("原始数据：" + new String(oldData.getData(), StandardCharsets.UTF_8));
        log.info("新数据：" + new String(data.getData(), StandardCharsets.UTF_8));
    }

}
