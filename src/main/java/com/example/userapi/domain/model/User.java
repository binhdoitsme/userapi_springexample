package com.example.userapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
public class User {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    private @NonNull String username;
    private @NonNull String password;
    private @NonNull String displayName;
    private @NonNull String email;

    private @NonNull String generatedSalt;
}
