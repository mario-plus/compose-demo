package com.mario.service;

import com.mario.enums.DataType;
import com.mario.rule.HexRule;
import com.mario.utils.ByteUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TestIKeyContentMapping extends IKeyContentMapping<HexRule> {

    @Override
    void doInitRules() {
        addKeyMapping(129, programRules());
        addKeyMapping(275, pictureRules());
        addKeyMapping(309, deviceName());
    }




    @Override
    public byte[] getContent(byte[] data) {
        byte[] bytes = new byte[data.length - 16];
        System.arraycopy(data, 16, bytes, 0, bytes.length);
        return bytes;
    }

    @Override
    public String getMessageKey(byte[] data) {
        byte[] bytes = new byte[2];
        System.arraycopy(data, 12, bytes, 0, 2);
        String key = ByteUtil.exchangeBytes(ByteUtil.byte2Hex(bytes));
        String s = String.valueOf(ByteUtil.getDecFromHex(key));
        log.info("messageKey is {}", s);
        return s;
    }


    //节目列表
    private List<HexRule> programRules() {
        List<HexRule> hexRules = new ArrayList<>();
        hexRules.add(HexRule.builder().key("programCount").startIndex(0).length(4).bigEndian(false).type(DataType.INTEGER).build());
        hexRules.add(HexRule.builder().key("programIndex").startIndex(4).length(4).bigEndian(false).type(DataType.INTEGER).build());
        hexRules.add(HexRule.builder().key("programId").startIndex(8).length(4).bigEndian(false).type(DataType.INTEGER).build());
        hexRules.add(HexRule.builder().startIndex(12).handler(new CustomHandler() {
            @Override
            public Map<String, Object> contentConverter(byte[] data) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("eee", 34354);
                return map;
            }
        }).build());
        return hexRules;
    }

    private List<HexRule> pictureRules() {
        List<HexRule> hexRules = new ArrayList<>();
        hexRules.add(HexRule.builder().key("pictureId").startIndex(0).length(4).bigEndian(false).type(DataType.INTEGER).build());
        hexRules.add(HexRule.builder().key("LayerName").startIndex(4).length(32).type(DataType.STRING).build());
        return hexRules;
    }

    private List<HexRule> deviceName() {
        List<HexRule> hexRules = new ArrayList<>();
        hexRules.add(HexRule.builder().key("triggerID").startIndex(0).length(36).type(DataType.HEX).build());
        hexRules.add(HexRule.builder().key("deviceName").startIndex(36).length(32).type(DataType.STRING).build());
        return hexRules;
    }

}
