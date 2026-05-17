package com.trackinvest.account.common.domain.rules;

public interface DomainRule<T>{
    T validate(T data);
}
