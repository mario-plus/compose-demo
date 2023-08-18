package com.mario.listener;

import com.mario.listener.abs.ZkListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.springframework.stereotype.Service;

/**
 * @author zxz
 * @date 2023年08月18日 16:37
 */
@Service
@Slf4j
public class ZkListenerServiceImpl implements ZkListenerService {

    final CuratorFramework curatorFramework;

    public ZkListenerServiceImpl(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    @Override
    public boolean listForAll(String node, ZkListener zkListener) {
        CuratorCache curatorCache = CuratorCache.builder(curatorFramework, node).build();
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forAll((CuratorCacheListener) zkListener).build();
        curatorCache.listenable().addListener(listener);
        curatorCache.start();
        return true;

    }

    @Override
    public boolean listenForChanges(String node, ZkListener zkListener) {
        CuratorCache curatorCache = CuratorCache.builder(curatorFramework, node).build();
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forChanges((CuratorCacheListenerBuilder.ChangeListener) zkListener).build();
        curatorCache.listenable().addListener(listener);
        curatorCache.start();
        return true;
    }

    @Override
    public boolean listenForCreatesAndChanges(String node,ZkListener zkListener) {
        CuratorCache curatorCache = CuratorCache.builder(curatorFramework, node).build();
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forCreatesAndChanges((CuratorCacheListenerBuilder.ChangeListener) zkListener).build();
        curatorCache.listenable().addListener(listener);
        curatorCache.start();
        return true;
    }
}
