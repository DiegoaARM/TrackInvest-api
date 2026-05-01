package com.trackinvest.account.user.application.ports.in.dto.auth;

public record TokenDTO(
        String access_token,
        String refresh_token,
        String id_token,
        Long expires_in) {
}
