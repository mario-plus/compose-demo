package com.mario.common;

import javax.crypto.spec.IvParameterSpec;

public interface SimpleCallback<T> extends Callback {

    SimpleCallback<Void> empty = new SimpleCallback<Void>() {

        @Override
        public void onSuccess(Void msg) {

        }

        @Override
        public void onError(Throwable e) {

        }
    };

    void onSuccess(T msg);

    void onError(Throwable e);


}
