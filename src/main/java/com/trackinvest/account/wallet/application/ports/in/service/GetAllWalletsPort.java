package com.trackinvest.account.wallet.application.ports.in.service;

import com.trackinvest.account.wallet.application.ports.in.dto.GetWalletResponseDTO;

import java.util.List;

public interface GetAllWalletsPort {
    List<GetWalletResponseDTO> execute(String cognitoId);
}

