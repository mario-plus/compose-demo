package com.mario.service;


import java.util.List;


public interface IKeyRulesService<IR> {

    void initRules();

    List<IR> getRules(String key);

}
