package com.trackinvest.account.user.infrastructure.adapter.in.controller;

import com.trackinvest.account.common.application.dto.ApiResponse;
import com.trackinvest.account.user.application.ports.in.dto.auth.RefreshTokenRequestDTO;
import com.trackinvest.account.user.application.ports.in.dto.auth.TokenDTO;
import com.trackinvest.account.user.application.ports.in.dto.auth.UrlDTO;
import com.trackinvest.account.user.application.ports.in.service.auth.AuthWithCodePort;
import com.trackinvest.account.user.application.ports.in.service.auth.GenerateAuthUrlPort;
import com.trackinvest.account.user.application.ports.in.service.auth.RefreshTokenPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Endpoints for authentication")
public class AuthController {

    private final GenerateAuthUrlPort generateAuthUrlPort;
    private final AuthWithCodePort authWithCodePort;
    private final RefreshTokenPort refreshTokenPort;

    @GetMapping("/url")
    public ResponseEntity<ApiResponse<UrlDTO>> url() {
        return ResponseEntity.ok(ApiResponse.success(generateAuthUrlPort.execute(), "Authorization URL generated successfully"));
    }

    @GetMapping("/callback")
    public ResponseEntity<ApiResponse<TokenDTO>> callback(
            @RequestParam("code") String code) {
        return ResponseEntity.ok(ApiResponse.success(authWithCodePort.execute(code), "Authentication successful"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenDTO>> refresh(
            @RequestBody RefreshTokenRequestDTO request) {
        TokenDTO newTokens = refreshTokenPort.execute(request.refreshToken());
        return ResponseEntity.ok(ApiResponse.success(newTokens, "Tokens refreshed successfully"));
    }
}
