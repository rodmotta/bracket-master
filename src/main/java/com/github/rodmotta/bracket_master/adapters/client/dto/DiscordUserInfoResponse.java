package com.github.rodmotta.bracket_master.adapters.client.dto;

public record DiscordUserInfoResponse(
        String username,
        String email,
        String avatar
) {
}
