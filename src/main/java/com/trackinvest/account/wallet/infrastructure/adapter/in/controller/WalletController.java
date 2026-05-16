package com.trackinvest.account.wallet.infrastructure.adapter.in.controller;

import com.trackinvest.account.common.application.dto.ApiResponse;
import com.trackinvest.account.wallet.application.ports.in.dto.CreateWalletRequestDTO;
import com.trackinvest.account.wallet.application.ports.in.dto.GetWalletResponseDTO;
import com.trackinvest.account.wallet.application.ports.in.dto.UpdateWalletRequestDTO;
import com.trackinvest.account.wallet.application.ports.in.dto.UpdateWalletBalanceRequestDTO;
import com.trackinvest.account.wallet.application.ports.in.service.CreateWalletPort;
import com.trackinvest.account.wallet.application.ports.in.service.UpdateWalletPort;
import com.trackinvest.account.wallet.application.ports.in.service.UpdateWalletBalancePort;
import com.trackinvest.account.wallet.application.ports.in.service.DeleteWalletPort;
import com.trackinvest.account.wallet.application.ports.in.service.GetAllWalletsPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
@Tag(name = "Wallet", description = "Endpoints for wallets management")
public class WalletController {

    private final CreateWalletPort createWalletPort;
    private final UpdateWalletPort updateWalletPort;
    private final UpdateWalletBalancePort updateWalletBalancePort;
    private final DeleteWalletPort deleteWalletPort;
    private final GetAllWalletsPort getAllWalletsPort;

    @PostMapping
    public ResponseEntity<ApiResponse<GetWalletResponseDTO>> createWallet(
            @RequestAttribute("USER_ID") UUID userId,
            @Valid @RequestBody CreateWalletRequestDTO request
    ) {
        GetWalletResponseDTO newWallet = createWalletPort.execute(userId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(newWallet, "Wallet created successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetWalletResponseDTO>>> getAllWallets(
            @RequestAttribute("USER_ID") UUID userId
    ) {
        List<GetWalletResponseDTO> wallets = getAllWalletsPort.execute(userId);
        return ResponseEntity.ok(ApiResponse.success(wallets, "Wallets retrieved successfully"));
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<ApiResponse<GetWalletResponseDTO>> updateWallet(
            @RequestAttribute("USER_ID") UUID userId,
            @PathVariable UUID walletId,
            @Valid @RequestBody UpdateWalletRequestDTO request
    ) {
        GetWalletResponseDTO updatedWallet = updateWalletPort.execute(userId, walletId, request);
        return ResponseEntity.ok(ApiResponse.success(updatedWallet, "Wallet updated successfully"));
    }

    @PutMapping("/{walletId}/balance")
    public ResponseEntity<ApiResponse<GetWalletResponseDTO>> updateWalletBalance(
            @RequestAttribute("USER_ID") UUID userId,
            @PathVariable UUID walletId,
            @Valid @RequestBody UpdateWalletBalanceRequestDTO request
    ) {
        GetWalletResponseDTO updatedWallet = updateWalletBalancePort.execute(userId, walletId, request);
        return ResponseEntity.ok(ApiResponse.success(updatedWallet, "Wallet updated successfully"));
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<ApiResponse<Void>> deleteWallet(
            @RequestAttribute("USER_ID") UUID userId,
            @PathVariable UUID walletId
    ) {
        deleteWalletPort.execute(userId, walletId);
        return ResponseEntity.ok(ApiResponse.success(null, "Wallet deleted successfully"));
    }
}
