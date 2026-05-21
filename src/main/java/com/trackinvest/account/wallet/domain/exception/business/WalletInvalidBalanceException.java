package com.trackinvest.account.wallet.domain.exception.business;

import com.trackinvest.account.common.domain.exception.DomainException;

public class WalletInvalidBalanceException extends DomainException {
    public WalletInvalidBalanceException() {
        super("Balance amount must be greater than zero");
    }
}
