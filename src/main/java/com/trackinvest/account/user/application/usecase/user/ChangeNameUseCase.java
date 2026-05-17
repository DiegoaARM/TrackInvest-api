package com.trackinvest.account.user.application.usecase.user;

import com.trackinvest.account.user.application.ports.in.service.user.ChangeNamePort;
import com.trackinvest.account.user.application.ports.out.UserRepositoryPort;
import com.trackinvest.account.user.domain.exception.business.UserNotFoundException;
import com.trackinvest.account.user.domain.models.UserDomain;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeNameUseCase implements ChangeNamePort {

    private final UserRepositoryPort userRepository;

    @Override
    @Transactional
    public void changeName(UUID id, String newFullName) {
        UserDomain user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.changeFullname(newFullName);
        userRepository.save(user);
    }
}
