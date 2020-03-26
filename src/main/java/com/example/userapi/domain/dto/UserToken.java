package com.example.userapi.domain.dto;

import com.example.userapi.domain.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
@JsonInclude(JsonInclude.Include.ALWAYS)
public class UserToken {
    private @NonNull String username;
    private @NonNull String displayName;
    private @NonNull String email;
    private @NonNull @Setter String authToken;

    public UserToken(User u, String token) {
        this(u.getUsername(), u.getDisplayName(), u.getEmail(), token);
    }
}
