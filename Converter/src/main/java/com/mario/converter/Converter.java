package com.mario.converter;


public abstract class Converter<R/*结果类型*/, PT/*参数类型*/> {


    public abstract PT beforeConverter(PT params);

    public abstract R convert(PT params);

    public abstract R afterConverter(R result);


}
