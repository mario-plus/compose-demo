package com.mario.service;

public interface IKeyHandler {

    /**
     * 消息标识
     */
    String getMessageKey(byte[] data);

}
