package com.mario.lock;

import com.mario.service.RedisService;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @author zxz
 * @date 2023年08月22日 15:13
 * 抢占式锁原理和zookeeper锁原理一样，利用node/key创建成功表示抢占锁成功，删除node/key释放锁
 */
@Component
public class RedisLockUtil {

    final RedisService redisService;

    public RedisLockUtil(RedisService redisService) {
        this.redisService = redisService;
    }

    public void unLock(String lockName) {
        redisService.delete(lockName);
    }

    public boolean getLock(String lockName) {
        if (!lock(lockName)) {
            return reLock(lockName);
        }
        return true;
    }


    public boolean getLock(String lockName, Long lockTime) {
        if (!lock(lockName, lockTime))
            return reLock(lockName, lockTime);
        return true;
    }


    private boolean lock(String lockName) {
        long millis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10);
        return redisService.setIfAbsent(lockName, String.valueOf(millis), 10L, TimeUnit.SECONDS);
    }


    private boolean reLock(String lockName) {
        Object value = redisService.get(lockName);
        if (value != null && Long.parseLong(value.toString()) < System.currentTimeMillis()) {//未过期锁
            return false;
        } else {
            return lock(lockName);
        }
    }

    private boolean lock(String lockName, Long lockTime) {
        long millis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(lockTime);
        return redisService.setIfAbsent(lockName, String.valueOf(millis), lockTime, TimeUnit.SECONDS);
    }


    private boolean reLock(String lockName, Long lockTime) {
        Object value = redisService.get(lockName);
        if (value != null && Long.parseLong(value.toString()) < System.currentTimeMillis()) {//未过期锁
            return false;
        } else {
            return lock(lockName, lockTime);
        }
    }
}
