package com.mario.converter;


import com.mario.rule.SplitRule;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitMapConverter extends MapConverter<SplitRule, String> {


    private String[] dataCache;

    @Override
    public String beforeConverter(String params) {
        return params + ",huangpangzi";
    }

    @Override
    protected Map<String, Object> doMapConverter(String str) {
        List<SplitRule> rules = getRules();
        if (str == null || rules == null || rules.isEmpty()) {
            return Collections.emptyMap();
        }
        dataCache = str.split(rules.get(0).getSplit());
        HashMap<String, Object> data = new HashMap<>();
        rules.forEach(rule -> {
            if (rule.getSplit() == null) {
                data.put(rule.getKey(), DataFormater.format(dataCache[rule.getIndex()], rule.getType()));
                return;
            }
            String[] split = str.split(rule.getSplit());
            String s = split[rule.getIndex()];
            List<SplitRule> child = rule.getChild();
            if (child == null || child.isEmpty()) {
                data.put(rule.getKey(), DataFormater.format(s, rule.getType()));
            } else {
                SplitMapConverter splitMapConverter = new SplitMapConverter();
                splitMapConverter.addRules(child);
                Map<String, Object> stringObjectMap = splitMapConverter.doMapConverter(s);
                data.put(rule.getKey(), stringObjectMap);
            }

        });
        return data;
    }
}
