package com.mario.controller;

import com.mario.entity.SeataUser;
import com.mario.service.SeataUserService;
import com.mario.service.SeataUserTccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @date 2024年02月23日 10:38
 */
@RestController
@RequestMapping("/user")
public class SeataUserController {

    SeataUserService seataUserService;



    SeataUserTccService seataUserTccService;

    @Autowired
    public void setSeataUserTccService(SeataUserTccService seataUserTccService) {
        this.seataUserTccService = seataUserTccService;
    }

    @Autowired
    public void setSeataUserService(SeataUserService seataUserService) {
        this.seataUserService = seataUserService;
    }

    @GetMapping("/getUserInfo")
    public SeataUser selectById(@RequestParam("id") int id) {
        return seataUserService.selectById(id);
    }

    @GetMapping("/incScore")
    public void incScore(int id, int score) throws Exception {
        seataUserService.incScore(id, score);
    }

    @GetMapping("/incTccScore")
    public void incTccScore(int id, int score) throws Exception {
        seataUserTccService.prepare(id, score);
    }
}
