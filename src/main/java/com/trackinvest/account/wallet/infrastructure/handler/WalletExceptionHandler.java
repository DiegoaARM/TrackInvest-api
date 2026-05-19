package com.trackinvest.account.wallet.infrastructure.handler;

import com.trackinvest.account.common.application.dto.ApiResponse;
import com.trackinvest.account.wallet.domain.exception.business.InsufficientBalanceException;
import com.trackinvest.account.wallet.domain.exception.business.InvalidBalanceException;
import com.trackinvest.account.wallet.domain.exception.business.WalletNotFoundException;
import com.trackinvest.account.wallet.domain.exception.format.WalletNameInvalidException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.trackinvest.account.wallet")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WalletExceptionHandler {

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleWalletNotFound(WalletNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage(), null));
    }

    @ExceptionHandler(WalletNameInvalidException.class)
    public ResponseEntity<ApiResponse<Void>> handleWalletNameInvalid(WalletNameInvalidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage(), null));
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ApiResponse<Void>> handleInsufficientBalance(InsufficientBalanceException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage(), null));
    }

    @ExceptionHandler(InvalidBalanceException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidBalance(InvalidBalanceException ex) {
        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage(), null));
    }
}
