package com.mario.controller;

import com.mario.common.SeataOrder;
import com.mario.service.SeataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zxz
 * @date 2024年02月23日 11:18
 */
@RestController
@RequestMapping("client")
public class SeataClientController {

    SeataService seataService;

    @Autowired
    public void setSeataService(SeataService seataService) {
        this.seataService = seataService;
    }

    @GetMapping("shopping")
    public void shopping(@RequestParam("resourceId") int resourceId,
                         @RequestParam("cost") int cost,
                         @RequestParam("userId") int userId) throws Exception {
        seataService.shopping(resourceId, cost, userId);
    }


    // 以下是测试子服务是否正常，与seata无关
    @GetMapping("testIncScore")
    public void testIncScore(@RequestParam("id") int id, @RequestParam("score") int score) throws Exception {
        seataService.testIncScore(id, score);
    }

    @GetMapping("testDisCountById")
    public void testDisCountById(@RequestParam("id") int id, @RequestParam("count") int count) throws Exception {
        seataService.testDecrResource(id, count);
    }

    @PostMapping("testCreateOrder")
    public void testCreateOrder(@RequestBody SeataOrder seataOrder) {
        seataService.testCreateOrder(seataOrder);
    }


}
