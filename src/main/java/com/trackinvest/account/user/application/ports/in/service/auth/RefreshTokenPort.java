package com.trackinvest.account.user.application.ports.in.service.auth;

import com.trackinvest.account.user.application.ports.in.dto.auth.TokenDTO;

public interface RefreshTokenPort {
    TokenDTO execute(String refreshToken);
}
