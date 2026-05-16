package com.trackinvest.account.user.application.ports.in.service.user;

import java.util.UUID;

public interface ChangeNamePort {
    void changeName(UUID id, String newFullName);
}
