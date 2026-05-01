package com.trackinvest.account.user.infrastructure.adapter.in.controller;

import com.trackinvest.account.user.application.ports.in.dto.user.GetUserResponseDTO;
import com.trackinvest.account.user.application.ports.in.service.user.GetMePort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Endpoints for profile management and user information")
public class UserController {

    private final GetMePort getMePort;

    @GetMapping("/me")
    public ResponseEntity<GetUserResponseDTO> getMe(
            @RequestAttribute("USER_ID") UUID userId
    ) {
        GetUserResponseDTO userDTO = getMePort.execute(userId);
        return ResponseEntity.ok(userDTO);
    }
}
