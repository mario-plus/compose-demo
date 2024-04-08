package com.mario;

import com.mario.service.impl.ZkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author zxz
 * @date 2023年08月18日 9:47
 */
@SpringBootApplication
public class ZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApplication.class, args);
    }

    @Autowired
    ZkServiceImpl zkService;

    @PostConstruct
    public void init() {
        try {
            String data = zkService.getData("/controller");
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
