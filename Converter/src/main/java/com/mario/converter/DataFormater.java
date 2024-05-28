package com.mario.converter;

import com.mario.DataType;
import com.mario.utils.ByteUtil;

import java.util.Arrays;

public class DataFormater {


    /**
     * 基本数据类型转换
     */
    public static Object format(String data, DataType dataType) {

        switch (dataType) {
            case INTEGER:
                return Integer.valueOf(data);
            case LONG:
                return Long.valueOf(data);
            case DOUBLE:
                return Double.valueOf(data);
            case FLOAT:
                return Float.valueOf(data);
            case BOOLEAN:
                return Boolean.valueOf(data);
            case BYTE:
                return Byte.valueOf(data);
            default:
                return data;
        }
    }

    /**
     * 按字节解析
     */
    public static Object formatHex(byte[] data, DataType dataType) {
        if (dataType == null) {
            return ByteUtil.byte2Hex(data);
        }
        switch (dataType) {
            case STRING:
                return new String(data);
            case INTEGER:
                return Integer.parseInt(ByteUtil.byte2Hex(data), 16);
            case LONG:
                return Long.parseLong(ByteUtil.byte2Hex(data), 16);
            case DOUBLE:
            case FLOAT:
                //hex转浮点数
                return ByteUtil.hexToFloat(ByteUtil.byte2Hex(data));
            default:
                return ByteUtil.byte2Hex(data);
        }
    }


}
