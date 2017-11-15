package com.meizu.upspushsdklib;

public enum CommandType {
    ERROR(0),
    REGISTER(1),
    UNREGISTER(2),
    SUBALIAS(3),
    UNSUBALIAS(4);

    int code;

    CommandType(int code){
        this.code = code;
    }

}
