package com.mario.converter;

import com.mario.rule.StringRule;
import lombok.Setter;

import java.util.*;

@Setter
public class StringMapConverter extends MapConverter<StringRule, String> {


    @Override
    protected Map<String, Object> doMapConverter(String str) {
        List<StringRule> rules = getRules();
        if (str == null || rules == null || rules.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap<String, Object> data = new HashMap<>();
        rules.forEach(e -> data.put(e.getKey(), DataFormater.format(str.substring(e.getStartIndex(), e.getStartIndex() + e.getLength()), e.getType())));
        return data;
    }

}
