package com.trackinvest.account.user.application.ports.in.service.user;

import com.trackinvest.account.user.application.ports.in.dto.user.GetUserResponseDTO;

import java.util.UUID;

public interface GetMePort {
    GetUserResponseDTO execute(UUID userId);
}
