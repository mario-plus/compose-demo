package com.mario;

import com.mario.converter.KeyMapConverter;
import com.mario.rule.HexRule;
import com.mario.service.TestIKeyRulesMapping;
import com.mario.utils.ByteUtil;

import java.util.Map;

public class T {

    public static void main(String[] args) {







        String message2 = "cc55cc55010000010000480035014400584f543c744246f6a99ed27f761c9161000000000000000000000000000000000000000041564d5000000000000000000000000000000000000000000000000000000000".replace(" ","");
        KeyMapConverter<HexRule> hexRuleKeyMapConverter = new KeyMapConverter<>(new TestIKeyRulesMapping());
        Map<String, Object> convert = hexRuleKeyMapConverter.convert(ByteUtil.Hex2Bytes(message2));
        System.out.println(convert);





    }
}
