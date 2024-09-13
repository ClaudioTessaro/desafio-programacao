package com.br.magalu.desafio.api.exceptions;

import lombok.NonNull;

import java.time.OffsetDateTime;

public record ResponseError(
        @NonNull String error, @NonNull OffsetDateTime timestamp, int statusCode
) {
}
