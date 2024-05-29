package com.mario.converter;


import com.mario.enums.DataType;
import com.mario.rule.SplitRule;

import java.util.*;


/**
 * 将字符串按分隔符拆分成Map，支持多层分割节点
 */
public class SplitMapConverter extends MapConverter<SplitRule, String> {

    //主分割符
    private final String firstSplit;


    public SplitMapConverter(String firstSplit) {
        this.firstSplit = firstSplit;
    }


    //缓存主分割符结果
    private String[] dataCache;

    @Override
    public String beforeConverter(String params) {
        return super.beforeConverter(params);
    }

    @Override
    protected Map<String, Object> doMapConverter(String str) {
        List<SplitRule> rules = getRules();
        if (str == null || rules == null || rules.isEmpty()) {
            return Collections.emptyMap();
        }
        dataCache = str.split(firstSplit);
        HashMap<String, Object> data = new HashMap<>();
        rules.forEach(rule -> {
            if (rule.getSplit() == null) {
                data.put(rule.getKey(), DataFormater.format(dataCache[rule.getIndex()], rule.getType()));
                return;
            }
            //子分割符
            if (rule.getChild() == null || rule.getIndex() == null || dataCache[rule.getIndex()] == null) {
                throw new RuntimeException("sub rules error,key=" + rule.getKey());
            }
            Map<String, Object> convert = new SplitMapConverter(rule.getSplit()).addRules(rule.getChild()).doMapConverter(dataCache[rule.getIndex()]);
            data.put(rule.getKey(), convert);
        });
        return data;
    }


    @Override
    public Map<String, Object> afterConverter(Map<String, Object> result) {
        return super.afterConverter(result);
    }

    /**
     * 以下是对三层结果进行分割测试
     */
    public static void main(String[] args) {
        String message = "zhouxiaoze,zm,18,19.00,test#fuyong%2024";
        Map<String, Object> stringObjectMap1 = new SplitMapConverter(",")
                .addRule(SplitRule.builder().index(0).key("name").type(DataType.STRING).build())
                .addRule(SplitRule.builder().index(1).key("company").type(DataType.STRING).build())
                .addRule(SplitRule.builder().index(2).key("age").type(DataType.INTEGER).build())
                .addRule(SplitRule.builder().index(3).key("money").type(DataType.DOUBLE).build())
                .addRule(SplitRule.builder().index(4).key("info").split("#")
                        .child(
                                Arrays.asList(
                                        SplitRule.builder().index(0).key("infoName").type(DataType.STRING).build(),
                                        SplitRule.builder().index(1).key("infoMsg").split("%")
                                                .child(
                                                        Arrays.asList(
                                                                SplitRule.builder().index(0).key("m1").type(DataType.STRING).build(),
                                                                SplitRule.builder().index(1).key("m2").type(DataType.INTEGER).build()
                                                        )
                                                ).build())).build())
                .convert(message);
        System.out.println(stringObjectMap1.toString());
    }
}
