package com.mario.converter;


import com.mario.rule.HexRule;
import com.mario.rule.Rules;
import com.mario.rule.SplitRule;
import com.mario.rule.StringRule;
import com.mario.service.IKeyContentMapping;
import com.mario.service.TestIKeyContentMapping;
import com.mario.utils.ByteUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class KeyMapConverter<IR extends Rules> extends MapConverter<IR, byte[]> {

    private final IKeyContentMapping<IR> keyRulesMapping;

    public KeyMapConverter(IKeyContentMapping<IR> keyRulesMapping) {
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


    public static void main(String[] args) {
        //cc55cc55010000010000480035014400584f543c744246f6a99ed27f761c9161000000000000000000000000000000000000000041564d5000000000000000000000000000000000000000000000000000000000
        String message2 = "CC 55 CC 55 01 00 00 01 0200 11 00 81 00 0D 00 05 00 00 00 02 00 00 00 02 00 00 00 01".replace(" ","");
        KeyMapConverter<HexRule> keyMapConverter = new KeyMapConverter<>(new TestIKeyContentMapping());
        Map<String, Object> convert = keyMapConverter.convert(ByteUtil.Hex2Bytes(message2));
        System.out.println(convert);
    }

}
