 package com.trackinvest.account.wallet.application.usecase;

import com.trackinvest.account.user.application.ports.out.UserRepositoryPort;
import com.trackinvest.account.user.domain.exception.business.UserNotFoundException;
import com.trackinvest.account.user.domain.models.UserDomain;
import com.trackinvest.account.wallet.application.ports.in.dto.GetWalletResponseDTO;
import com.trackinvest.account.wallet.application.ports.in.service.GetAllWalletsPort;
import com.trackinvest.account.wallet.application.ports.out.WalletRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllWalletsUseCase implements GetAllWalletsPort {

    private final UserRepositoryPort userRepository;
    private final WalletRepositoryPort walletRepository;

    @Override
    public List<GetWalletResponseDTO> execute(UUID userId) {
        return walletRepository.findByUserId(userId)
                .stream()
                .map(GetWalletResponseDTO::fromDomain)
                .collect(Collectors.toList());
    }
}
