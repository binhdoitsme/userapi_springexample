package com.example.userapi.base;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Composite<T1 extends Object, T2 extends Object> {
    private @NonNull T1 one;
    private @NonNull T2 two;
}