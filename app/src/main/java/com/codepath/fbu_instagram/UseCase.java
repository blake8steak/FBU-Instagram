package com.codepath.fbu_instagram;

public abstract class UseCase<Q,P> {
    /*
        This class is extended by all
        UseCases throughout the entire
        application.

        P: UseCase.ResponseValue
        Q: UseCase.RequestValues
        R: UseCase.ResponseValue
     */
    public Q requestValues;
    public UseCaseCallback<P> callback;

    public void run() {
        executeUseCase(requestValues);
    }

    public abstract void executeUseCase(Q reqVals);

    interface RequestValues{};

    interface ResponseValue{};

    interface UseCaseCallback<R> {
        void onSuccess(R resp);
        void onFailure(Throwable t);
    }

}
