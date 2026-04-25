package com.trackinvest.account.user.infrastructure.adapter.out.persistence.mapper;

import com.trackinvest.account.user.domain.models.UserDomain;
import com.trackinvest.account.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.trackinvest.account.wallet.infrastructure.adapter.out.persistence.mapper.WalletEntityMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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

    @Mapping(target = "walletsList", ignore = true)
    UserDomain toDomain(UserEntity entity);
}
