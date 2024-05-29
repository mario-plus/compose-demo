package com.mario.converter;


import com.mario.rule.HexRule;
import com.mario.rule.Rules;
import com.mario.rule.SplitRule;
import com.mario.rule.StringRule;
import com.mario.service.IKeyRulesMapping;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class KeyMapConverter<IR extends Rules> extends MapConverter<IR, byte[]> {

    private final IKeyRulesMapping<IR> keyRulesMapping;
    public KeyMapConverter(IKeyRulesMapping<IR> keyRulesMapping) {
        this.keyRulesMapping = keyRulesMapping;
    }

    @Override
    protected Map<String, Object> doMapConverter(byte[] params) {
        if (keyRulesMapping == null) {
            throw new RuntimeException("not found IKeyRulesMapping");
        }
        keyRulesMapping.initRules();
        String messageKey = keyRulesMapping.getMessageKey(params);
        if (messageKey == null) {
            throw new RuntimeException("not config messageKey");
        }
        List<IR> rules = keyRulesMapping.getRules(messageKey);
        if (rules == null || rules.isEmpty()) {
            throw new RuntimeException("not found rules");
        }
        byte[] content = keyRulesMapping.getContent(params);

        IR ir = rules.get(0);
        if (ir instanceof HexRule) {
            return new HexMapConverter().addRules(rules.stream().map(e -> (HexRule) e).collect(Collectors.toList())).convert(content);
        } else if (ir instanceof SplitRule) {
            //return new SplitMapConverter().addRules(rules.stream().map(e -> (SplitRule) e).collect(Collectors.toList())).convert(new String(content));
        } else if (ir instanceof StringRule) {
            return new StringMapConverter().addRules(rules.stream().map(e -> (StringRule) e).collect(Collectors.toList())).convert(new String(content));
        }
        return Collections.emptyMap();
    }


}
