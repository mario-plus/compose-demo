package com.mario.service;

public interface IContentHandler {

    /**
     * 消息体
     * */
    byte[] getContent(byte[] data);
}
