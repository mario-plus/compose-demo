package com.mario;

import com.mario.converter.HexMapConverter;
import com.mario.converter.SplitMapConverter;
import com.mario.converter.StringMapConverter;
import com.mario.rule.HexRule;
import com.mario.rule.SplitRule;
import com.mario.rule.StringRule;
import com.mario.utils.ByteUtil;

import java.util.Arrays;
import java.util.Map;

public class T {

    public static void main(String[] args) {

        String message = "zhouxiaoze,zm,18,19.00,test#fuyong";


//        Map<String, Object> stringObjectMap = new StringMapConverter()
//                .addRule(StringRule.builder().startIndex(0).length(10).key("name").type(DataType.STRING).build())
//                .addRule(StringRule.builder().startIndex(11).length(2).key("company").type(DataType.STRING).build())
//                .addRule(StringRule.builder().startIndex(14).length(2).key("age").type(DataType.INTEGER).build())
//                .addRule(StringRule.builder().startIndex(17).length(4).key("money").type(DataType.DOUBLE).build())
//                .doMapConverter(message);
//        System.out.println(stringObjectMap.toString());


//        Map<String, Object> stringObjectMap1 = new SplitMapConverter()
//                .addRule(SplitRule.builder().split(",").index(0).key("name").type(DataType.STRING).build())
//                .addRule(SplitRule.builder().index(1).key("company").type(DataType.STRING).build())
//                .addRule(SplitRule.builder().index(2).key("age").type(DataType.INTEGER).build())
//                .addRule(SplitRule.builder().index(3).key("money").type(DataType.DOUBLE).build())
//                .addRule(SplitRule.builder().split(",").index(4).key("info")
//                        .child(Arrays.asList(
//                                SplitRule.builder().split("#").index(0).key("infoName").type(DataType.STRING).build(),
//                                SplitRule.builder().split("#").index(1).key("infoMsg").type(DataType.STRING).build()
//                        ))
//                        .build())
//                .addRule(SplitRule.builder().index(5).key("tt").type(DataType.STRING).build())
//                .convert(message);
//        System.out.println(stringObjectMap1.toString());

        String message2 = "03 CC 55 CC 55 01 00 00 01 0200 11 00 81 00 0D 00 05 00 00 00 02 00 00 00 02 00 00 00 01".replace(" ", "");

        Map<String, Object> convert = new HexMapConverter()
                .addRule(HexRule.builder().key("info").length(1).type(DataType.BIT).build()
                        .addChild(HexRule.builder().key("a1").startIndex(0).length(3).build())
                        .addChild(HexRule.builder().key("a2").startIndex(3).length(2).build())
                        .addChild(HexRule.builder().key("a3").startIndex(5).length(3).build()))
                .addRule(HexRule.builder().key("head").length(4).build())
                .addRule(HexRule.builder().key("packetType").length(2).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("protocolVersion").length(2).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("seq").length(2).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("length").length(2).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("tag").length(2).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("contentLength").length(2).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("programCount").startIndex(16).length(4).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("programIndex").length(4).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("programId").length(4).bigEndian(false).type(DataType.INTEGER).build())
                .addRule(HexRule.builder().key("programName").length(128).bigEndian(false).type(DataType.INTEGER).build())
                .convert(ByteUtil.Hex2Bytes(message2));
        System.out.println(convert);

    }
}
