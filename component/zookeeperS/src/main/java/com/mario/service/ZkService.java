package com.mario.service;

import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public interface ZkService {

    /**
     * 节点不存在返回null
     */
    Stat checkNode(String node) throws Exception;

    /**
     * 节点存在就会报错
     * 创建模式：常用的有
     * PERSISTENT：持久化节点，客户端与zookeeper断开连接后，该节点依旧存在，只要不手动删除，该节点就会永远存在。
     * PERSISTENT_SEQUENTIAL：持久化顺序编号目录节点，客户端与zookeeper断开连接后，该节点依旧存在，只是zookeeper给该节点名称进行顺序编号。
     * EPHEMERAL：临时目录节点，客户端与zookeeper断开连接后，该节点被删除。
     * EPHEMERAL_SEQUENTIAL：临时顺序编号目录节点，客户端与zookeeper断开连接后，该节点被删除，只是zookeeper给该节点名称进行顺序编号。
     */
    void createNode(String node) throws Exception;

    /**
     * 只能给已存在的节点赋值
     */
    void setData(String node, String data) throws Exception;

    /**
     * 查询一个不存在的节点，会报错
     */
    String getData(String node) throws Exception;

    /**
     * 删除一个不存在的节点，会报错
     */
    void deleteNode(String node) throws Exception;


    /**
     * 查询不存在的节点，会报错
     * 只能查子节点，不能查孙节点
     */
    List<String> getChildNode(String node) throws Exception;

}
