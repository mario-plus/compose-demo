package com.mario.converter;

import com.mario.DataType;
import com.mario.rule.HexRule;
import com.mario.utils.ByteUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class HexMapConverter extends MapConverter<HexRule, byte[]> {

    /**
     * 记录起始坐标
     */
    private int startIndex;

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }


    //全局属性，比如大小端模式
    @Override
    protected Map<String, Object> doMapConverter(byte[] params) {
        String contentHex = ByteUtil.byte2Hex(params);
        List<HexRule> rules = getRules();
        if (contentHex == null || rules == null || rules.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap<String, Object> data = new HashMap<>();
        rules.forEach(hexRule -> {
            int start = hexRule.getStartIndex() == null ? startIndex : hexRule.getStartIndex();
            int end = hexRule.getLength() == null ? contentHex.length() : (start + hexRule.getLength()) * 2;
            log.info("key: {},   startIndex：{},  endIndex: {},    length: {}", hexRule.getKey(), startIndex, end / 2, end / 2 - start);
            setStartIndex(end / 2);
            if (end > contentHex.length()) {
                data.put(hexRule.getKey(), null);
                return;
            }
            String hexSub = contentHex.substring(start * 2, end);
            //统一以大端处理
            if (!hexRule.isBigEndian()) {
                hexSub = ByteUtil.exchangeBytes(hexSub);
            }
            if (hexRule.getType() == DataType.BIT) {
                String binString = ByteUtil.getBinLFromDec(ByteUtil.getDecFromHex(hexSub), hexSub.length() * 4);
                assert binString != null;
                hexRule.getChild().forEach(child -> {
                    String binSub = binString.substring(binString.length() - child.getLength() - child.getStartIndex(), binString.length() - child.getStartIndex());
                    data.put(child.getKey(), Integer.parseInt(binSub, 2));
                });
            }
            data.put(hexRule.getKey(), DataFormater.formatHex(ByteUtil.Hex2Bytes(hexSub), hexRule.getType()));
        });
        return data;
    }

    @Override
    public byte[] beforeConverter(byte[] params) {
        return super.beforeConverter(params);
    }


//            if (hexRule.getChild() != null) {
//                HexMapConverter hexMapConverter = new HexMapConverter();
//                hexMapConverter.addRules(hexRule.getChild());
//                Map<String, Object> hexObjectMap = hexMapConverter.doMapConverter(ByteUtil.Hex2Bytes(hexSub));
//                data.put(hexRule.getKey(), hexObjectMap);
//            }


}
