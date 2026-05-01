package com.trackinvest.account.wallet.application.ports.in.service;

import com.trackinvest.account.wallet.application.ports.in.dto.GetWalletResponseDTO;
import com.trackinvest.account.wallet.application.ports.in.dto.UpdateWalletBalanceRequestDTO;

import java.util.UUID;

public interface UpdateWalletBalancePort {
    GetWalletResponseDTO execute(UUID userId, UUID walletId, UpdateWalletBalanceRequestDTO request);
}
