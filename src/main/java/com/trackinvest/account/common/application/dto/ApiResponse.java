package com.trackinvest.account.common.application.dto;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        Object errors,
        String timestamp
) {
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data, null, java.time.LocalDateTime.now().toString());
    }

    public static <T> ApiResponse<T> error(String message, Object errors) {
        return new ApiResponse<>(false, message, null, errors, java.time.LocalDateTime.now().toString());
    }
}
