package com.mario.converter;

import com.mario.rule.Rules;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public abstract class MapConverter<IR/*规则类型*/ extends Rules, PT/*数据类型*/> extends Converter<Map<String, Object>, PT> {


    private List<IR> rules;

    @Override
    public Map<String, Object> convert(PT params) {
        PT pt = beforeConverter(params);
        Map<String, Object> resultMap = doMapConverter(pt);
        resultMap = afterConverter(resultMap);
        return resultMap;
    }


    protected abstract Map<String, Object> doMapConverter(PT params);

    @Override
    public PT beforeConverter(PT params) {
        return params;
    }


    @Override
    public Map<String, Object> afterConverter(Map<String, Object> result) {
        return result;
    }

    public MapConverter<IR, PT> addRule(IR rule) {
        if (rules == null || rules.isEmpty()) {
            rules = new ArrayList<>();
        }
        rules.add(rule);
        return this;
    }


    public MapConverter<IR, PT> addRules(List<IR> ruleList) {
        if (rules == null || rules.isEmpty()) {
            rules = new ArrayList<>();
        }
        rules.addAll(ruleList);
        return this;
    }

}
