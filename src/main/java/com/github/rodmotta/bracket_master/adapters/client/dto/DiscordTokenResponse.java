package com.github.rodmotta.bracket_master.adapters.client.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DiscordTokenResponse(
        String tokenType,
        String accessToken,
        int expiresIn,
        String refreshToken,
        String scope
) {
}
