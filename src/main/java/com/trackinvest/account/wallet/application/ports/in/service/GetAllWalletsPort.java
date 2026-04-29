package com.trackinvest.account.wallet.application.ports.in.service;

import com.trackinvest.account.wallet.application.ports.in.dto.GetWalletResponseDTO;

import java.util.List;
import java.util.UUID;

public interface GetAllWalletsPort {
    List<GetWalletResponseDTO> execute(UUID userId);
}

