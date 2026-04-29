package com.trackinvest.account.user.application.ports.out;

import com.trackinvest.account.user.application.ports.in.dto.auth.TokenDTO;

public interface IdentityProviderPort {
    String generateAuthorizationUrl();
    TokenDTO exchangeCodeForToken(String code);
    TokenDTO refreshTokens(String refreshToken);
}
