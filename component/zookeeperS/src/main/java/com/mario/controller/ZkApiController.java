package com.mario.controller;

import com.mario.service.ZkService;
import org.apache.zookeeper.data.Stat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zxz
 * @date 2023年08月18日 10:20
 */
@RestController
public class ZkApiController {

    final ZkService zkService;


    public ZkApiController(ZkService zkService) {
        this.zkService = zkService;
    }

    @GetMapping(value = "checkNode")
    public Stat checkNode(@RequestParam(name = "node") String node) throws Exception {
        Stat stat = zkService.checkNode(node);
        System.out.println(stat);
        return stat;
    }

    @GetMapping(value = "createNode")
    public void createNode(@RequestParam(name = "node") String node) throws Exception {
        zkService.createNode(node);
    }

    @GetMapping(value = "deleteNode")
    public void deleteNode(@RequestParam(name = "node") String node) throws Exception {
        zkService.deleteNode(node);
    }

    @GetMapping(value = "getNodeData")
    public String getNodeData(@RequestParam(name = "node") String node) throws Exception {
        return zkService.getData(node);
    }

    @GetMapping(value = "setNodeData")
    public void setNodeData(@RequestParam(name = "node") String node, @RequestParam(name = "value") String value) throws Exception {
        zkService.setData(node, value);
    }

    @GetMapping(value = "getChildNode")
    public List<String> getChildNode(@RequestParam(name = "node") String node) throws Exception {
        return zkService.getChildNode(node);
    }
}
