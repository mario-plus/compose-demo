package com.mario.service;

import java.util.Map;

public interface CustomHandler {

    /**
     * 消息体
     * */
    Map<String, Object> contentConverter(byte[] data);
}
