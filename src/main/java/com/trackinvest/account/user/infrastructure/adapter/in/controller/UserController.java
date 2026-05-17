package com.trackinvest.account.user.infrastructure.adapter.in.controller;

import com.trackinvest.account.common.application.dto.ApiResponse;
import com.trackinvest.account.user.application.ports.in.dto.user.GetUserResponseDTO;
import com.trackinvest.account.user.application.ports.in.service.user.ChangeNamePort;
import com.trackinvest.account.user.application.ports.in.service.user.GetMePort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Endpoints for profile management and user information")
public class UserController {

    private final GetMePort getMePort;
    private final ChangeNamePort changeNamePort;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<GetUserResponseDTO>> getMe(
            @RequestAttribute("USER_ID") UUID userId
    ) {
        GetUserResponseDTO userDTO = getMePort.execute(userId);
        return ResponseEntity.ok(ApiResponse.success(userDTO, "User information retrieved successfully"));
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<Void>> changeName(
            @RequestAttribute("USER_ID") UUID userId,
            @RequestParam String newFullName
    ) {
        changeNamePort.changeName(userId, newFullName);
        return ResponseEntity.ok(ApiResponse.success(null, "User name updated successfully"));
    }
}
