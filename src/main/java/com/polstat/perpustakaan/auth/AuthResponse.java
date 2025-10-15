package com.polstat.perpustakaan.auth;
import lombok.*;
@Data @AllArgsConstructor
public class AuthResponse {
    private String email;
    private String accessToken;
}