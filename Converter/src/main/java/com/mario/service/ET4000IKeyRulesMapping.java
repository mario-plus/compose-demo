package com.mario.service;

import com.mario.DataType;
import com.mario.rule.HexRule;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * */
public class ET4000IKeyRulesMapping extends IKeyRulesMapping<HexRule>  {


    @Override
    void doInitRules() {
        addKeyMapping(129, programRules());
    }

    //节目列表
    private List<HexRule> programRules() {
        List<HexRule> hexRules = new ArrayList<>();
        hexRules.add(HexRule.builder().key("programCount").startIndex(16).length(4).bigEndian(false).type(DataType.INTEGER).build());
        hexRules.add(HexRule.builder().key("programIndex").length(4).bigEndian(false).type(DataType.INTEGER).build());
        hexRules.add(HexRule.builder().key("programId").length(4).bigEndian(false).type(DataType.INTEGER).build());
        hexRules.add(HexRule.builder().key("programName").length(128).bigEndian(false).type(DataType.INTEGER).build());
        return hexRules;
    }

    @Override
    public byte[] getContent(byte[] data) {
        return new byte[0];
    }

    @Override
    public String getMessageKey(byte[] data) {
        return "";
    }
}
