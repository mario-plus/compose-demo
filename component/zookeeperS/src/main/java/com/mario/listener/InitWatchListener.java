package com.mario.listener;

import com.mario.listener.abs.impl.ZkChangeListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * @author zxz
 * @date 2023年08月18日 17:01
 */
@Component
public class InitWatchListener implements CommandLineRunner {
    final ZkListenerService zkListenerService;

    public InitWatchListener(ZkListenerService zkListenerService) {
        this.zkListenerService = zkListenerService;
    }

    /***
     * 监听可以在其他地方初始化
     * */
    @Override
    public void run(String... args) throws Exception {
        zkListenerService.listenForChanges("/", new ZkChangeListener());
    }


}
