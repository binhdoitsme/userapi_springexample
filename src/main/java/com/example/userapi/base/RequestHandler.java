package com.example.userapi.base;

public interface RequestHandler<TRequest, TResponse> {
    TResponse handle(TRequest request);
}
