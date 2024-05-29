package com.mario.converter;

import com.mario.rule.StringRule;
import lombok.Setter;
import com.mario.enums.DataType;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Setter
public class StringMapConverter extends MapConverter<StringRule, String> {

    private int startIndex;

    @Override
    protected Map<String, Object> doMapConverter(String str) {
        List<StringRule> rules = getRules();
        if (str == null || rules == null || rules.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap<String, Object> data = new HashMap<>();

        rules.parallelStream().forEach(e -> {
            if (e.getStartIndex() > str.length() || e.getStartIndex() + e.getLength() > str.length()) {
                throw new RuntimeException("rule error,key is " + e.getKey());
            }
            String substring = str.substring(e.getStartIndex(), e.getStartIndex() + e.getLength());
            data.put(e.getKey(), DataFormater.format(substring, e.getType()));
        });
        return data;
    }


    public static void main(String[] args) {
        String message = "zhouxiaoze,zm,18,19.00,test#fuyong";
        Map<String, Object> stringObjectMap = new StringMapConverter()
                .addRule(StringRule.builder().startIndex(0).length(10).key("name").type(DataType.STRING).build())
                .addRule(StringRule.builder().startIndex(11).length(2).key("company").type(DataType.STRING).build())
                .addRule(StringRule.builder().startIndex(14).length(2).key("age").type(DataType.INTEGER).build())
                .addRule(StringRule.builder().startIndex(17).length(5).key("money").type(DataType.DOUBLE).build())
                .convert(message);
        System.out.println(stringObjectMap.toString());
    }

}
