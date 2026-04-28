package com.trackinvest.account.wallet.application.ports.in.service;

import com.trackinvest.account.wallet.application.ports.in.dto.CreateWalletRequestDTO;
import com.trackinvest.account.wallet.application.ports.in.dto.GetWalletResponseDTO;

import java.util.UUID;

public interface CreateWalletPort {
    GetWalletResponseDTO execute(UUID userId, CreateWalletRequestDTO wallet);
}
