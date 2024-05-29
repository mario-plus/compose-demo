package com.mario.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定义key生成规则
 * 定义content获取规则
 * 定义key对应的规则集合
 */
public abstract class IKeyRulesMapping<IR> implements IKeyRulesService<IR>, IKeyHandler, IContentHandler {

    Map<String, List<IR>> keyMapping;

    @Override
    public void initRules() {
        if (keyMapping == null || keyMapping.isEmpty()) {
            keyMapping = new HashMap<>();
            doInitRules();
        }
    }

    @Override
    public List<IR> getRules(String key) {
        return keyMapping.get(key);
    }

    public void addKeyMapping(Object key, List<IR> rules) {
        keyMapping.put(String.valueOf(key), rules);
    }


    /**
     * 初始化规则
     * */
    abstract void doInitRules();


}
