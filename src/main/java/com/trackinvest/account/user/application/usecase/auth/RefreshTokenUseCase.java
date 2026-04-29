package com.trackinvest.account.user.application.usecase.auth;

import com.trackinvest.account.user.application.ports.in.dto.auth.TokenDTO;
import com.trackinvest.account.user.application.ports.in.service.auth.RefreshTokenPort;
import com.trackinvest.account.user.application.ports.out.IdentityProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenUseCase implements RefreshTokenPort {

    private final IdentityProviderPort identityProvider;

    @Override
    public TokenDTO execute(String refreshToken) {
        // 1. Llamamos al adaptador para obtener nuevos tokens
        TokenDTO response = identityProvider.refreshTokens(refreshToken);

        return response;
    }
}
