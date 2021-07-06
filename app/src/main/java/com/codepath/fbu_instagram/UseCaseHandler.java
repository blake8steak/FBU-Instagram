package com.codepath.fbu_instagram;

import com.codepath.fbu_instagram.UseCase.*;

public class UseCaseHandler {
    public UseCaseHandler() {}

    public <T, R> void execute(UseCase<T,R> useCase, T reqValues, UseCaseCallback<R> callback) {
        useCase.requestValues = reqValues;
        useCase.callback = new UICallbackWrapper(callback, this);
        useCase.run();
    }

    private <V> void notifyError(UseCaseCallback<V> mCallback, Throwable t) { }

    private <V> void notifyResponse(V resp, UseCaseCallback<V> mCallback) { }

    private class UICallbackWrapper<V> implements UseCaseCallback<V> {
        private UseCaseCallback<V> callback;
        private UseCaseHandler handler;

        public UICallbackWrapper(UseCaseCallback callback, UseCaseHandler handler) {
            this.callback = callback;
            this.handler = handler;
        }
        @Override
        public void onSuccess(V resp) {
            handler.notifyResponse(resp, callback);
        }

        @Override
        public void onFailure(Throwable t) {
            handler.notifyError(callback, t);
        }
    }
}
