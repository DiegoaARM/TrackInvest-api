package com.trackinvest.account.wallet.application.ports.in.service;

import java.util.UUID;

public interface DeleteWalletPort {
    void execute(UUID userId, UUID walletId);
}
