package com.mario.converter;

import com.mario.enums.DataType;
import com.mario.rule.HexRule;
import com.mario.service.CustomHandler;
import com.mario.utils.ByteUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 已知数据格式情况下转换成Map（即已知每一字节代表的意思）
 */
@Slf4j
public class HexMapConverter extends MapConverter<HexRule, byte[]> {


    @Override
    protected Map<String, Object> doMapConverter(byte[] params) {
        String contentHex = ByteUtil.byte2Hex(params);
        List<HexRule> rules = getRules();
        if (contentHex == null || rules == null || rules.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap<String, Object> data = new HashMap<>();
        rules.forEach(hexRule -> {
            int start = hexRule.getStartIndex() * 2;
            //未知长度，则元素长度为剩余长度
            int end = hexRule.getLength() == null ? contentHex.length() : start + hexRule.getLength() * 2;
            if (start > contentHex.length() || end > contentHex.length()) {
                log.error("rule error,key is {}", hexRule.getKey());
                data.put(hexRule.getKey(), null);
                return;
                //throw new RuntimeException("rule error,key is " + hexRule.getKey());
            }
            String hexSub = contentHex.substring(start, end);

            if (!hexRule.isBigEndian()) {
                hexSub = ByteUtil.exchangeBytes(hexSub);
            }
            if (hexRule.getHandler() != null) {
                Map<String, Object> stringObjectMap = hexRule.getHandler().contentConverter(ByteUtil.Hex2Bytes(hexSub));
                data.putAll(stringObjectMap);
                return;
            }
            if (hexRule.getType() == DataType.BIT) {
                String binString = ByteUtil.getBinLFromDec(ByteUtil.getDecFromHex(hexSub), hexSub.length() * 4);
                assert binString != null;
                if (hexRule.getChild() == null || hexRule.getChild().isEmpty()) {
                    throw new RuntimeException("error config child,key is " + hexRule.getKey());
                }
                hexRule.getChild().forEach(child -> {
                    String binSub = binString.substring(binString.length() - child.getLength() - child.getStartIndex(), binString.length() - child.getStartIndex());
                    data.put(child.getKey(), Integer.parseInt(binSub, 2));
                });
            } else {
                data.put(hexRule.getKey(), DataFormater.formatHex(ByteUtil.Hex2Bytes(hexSub), hexRule.getType()));
            }

        });
        return data;
    }

    @Override
    public byte[] beforeConverter(byte[] params) {
        return super.beforeConverter(params);
    }

    @Override
    public Map<String, Object> afterConverter(Map<String, Object> result) {
        return super.afterConverter(result);
    }

    public static void main(String[] args) {

        String message2 = "CC 55 CC 55 01 00 00 01 0200 11 00 81 00 0D 00 05 00 00 00 02 00 00 00 02 00 00 00 01".replace(" ", "");
        Map<String, Object> convert = new HexMapConverter()
                .addRule(HexRule.builder().key("head").startIndex(0).length(4).build())
                .addRule(HexRule.builder().key("packetType").startIndex(4).length(2).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("protocolVersion").startIndex(6).length(2).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("seq").startIndex(8).length(2).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("length").startIndex(10).length(2).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("tag").startIndex(12).length(2).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("contentLength").startIndex(14).length(2).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("programCount").startIndex(16).length(4).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("programIndex").startIndex(20).length(4).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("programId").startIndex(24).length(4).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().startIndex(28).handler(new CustomHandler() {
                    @Override
                    public Map<String, Object> contentConverter(byte[] data) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("eee", 34354);
                        return map;
                    }
                }).build())
                .convert(ByteUtil.Hex2Bytes(message2));
        System.out.println(convert);


        String message1 = "A5";
        Map<String, Object> convert1 = new HexMapConverter().addRule(HexRule.builder().key("tt").startIndex(0).length(1).type(DataType.BIT).child(Arrays.asList(HexRule.builder().key("m1").startIndex(0).length(3).build(), HexRule.builder().key("m2").startIndex(3).length(3).build(), HexRule.builder().key("m3").startIndex(6).length(2).build()

        )).build()).convert(ByteUtil.Hex2Bytes(message1));
        System.out.println(convert1);
    }
}
