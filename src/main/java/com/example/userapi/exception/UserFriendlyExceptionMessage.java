package com.example.userapi.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter @Setter
public class UserFriendlyExceptionMessage {

    private @NonNull String message;

    public static UserFriendlyExceptionMessage from(Object o) {
        Throwable t = (Throwable)o;
        return new UserFriendlyExceptionMessage(t.getMessage());
    }
}