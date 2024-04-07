package com.mario.service.impl;


import com.mario.service.ZkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zxz
 * @date 2023年08月18日 10:10
 * zookeeper基本使用
 */
@Slf4j
@Service
public class ZkServiceImpl implements ZkService {

    final CuratorFramework curatorFramework;

    public ZkServiceImpl(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }


    public Stat checkNode(String node) throws Exception {
        return curatorFramework.checkExists().forPath(node);
    }

    public void createNode(String node) throws Exception {
        curatorFramework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(node);

    }


    public void setData(String node, String data) throws Exception {
        curatorFramework.setData().forPath(node, data.getBytes(StandardCharsets.UTF_8));
    }

    public String getData(String node) throws Exception {
        return new String(curatorFramework.getData().forPath(node), StandardCharsets.UTF_8);
    }

    public void deleteNode(String node) throws Exception {
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(node);
    }

    public List<String> getChildNode(String node) throws Exception {
        return curatorFramework.getChildren().forPath(node);
    }




    public void getReadLock(String path) throws Exception {
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(curatorFramework, path);
        lock.readLock().acquire();
        System.out.println("获取-ReadLock");//do something
        lock.readLock().release();
    }


    public void getWriteLock(String path) throws Exception {
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(curatorFramework, path);
        lock.writeLock().acquire();
        System.out.println("获取-WriteLock");//do something
        lock.writeLock().release();
    }

}
