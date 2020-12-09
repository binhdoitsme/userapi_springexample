package com.example.userapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class UserDto {
    private @NonNull String username;
    private @NonNull String password;
    private String displayName;
    private String email;
}
