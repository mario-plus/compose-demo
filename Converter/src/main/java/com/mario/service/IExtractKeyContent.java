package com.mario.service;

public interface IExtractKeyContent {

    /**
     * 消息标识
     */
    String getMessageKey(byte[] data);


    /**
     * 消息体
     * */
    byte[] getContent(byte[] data);
}
