package com.trackinvest.account.user.infrastructure.adapter.out.persistence.mapper;

import com.trackinvest.account.user.domain.models.UserDomain;
import com.trackinvest.account.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.trackinvest.account.wallet.domain.models.WalletDomain;
import com.trackinvest.account.wallet.infrastructure.adapter.out.persistence.entity.WalletEntity;
import com.trackinvest.account.wallet.infrastructure.adapter.out.persistence.mapper.WalletEntityMapper;
import org.hibernate.Hibernate;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", uses = {WalletEntityMapper.class})
public interface UserEntityMapper {

    UserEntity toEntity(UserDomain domain);

    @AfterMapping
    default void linkWallets(UserDomain domain, @MappingTarget UserEntity.UserEntityBuilder userEntityBuilder) {
        UserEntity userEntity = userEntityBuilder.build();

        if (userEntity.getWalletsList() != null) {
            userEntity.getWalletsList().forEach(wallet -> wallet.setUser(userEntity));
        }
    }

    default UserDomain toDomain(UserEntity entity) {
        if (entity == null) return null;

        List<WalletDomain> wallets = Hibernate.isInitialized(entity.getWalletsList())
                ? mapWallets(entity.getWalletsList())
                : Collections.emptyList();

        return UserDomain.from(
                entity.getId(),
                entity.getCognitoId(),
                entity.getFullname(),
                entity.getEmail(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                mapWallets(entity.getWalletsList())
        );
    }

    List<WalletDomain> mapWallets(List<WalletEntity> entities);
}
