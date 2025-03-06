package com.github.rodmotta.bracket_master.adapters.security;

import com.github.rodmotta.bracket_master.adapters.client.DiscordClient;
import com.github.rodmotta.bracket_master.core.model.User;
import com.github.rodmotta.bracket_master.core.ports.IdentityProviderPort;
import org.springframework.stereotype.Component;

@Component
public class DiscordIdentityProvider implements IdentityProviderPort {

    private final DiscordClient discordClient;

    public DiscordIdentityProvider(DiscordClient discordClient) {
        this.discordClient = discordClient;
    }

    @Override
    public String getAccessToken(String authCode) {
        return discordClient.exchangeCode(authCode).accessToken();
    }

    @Override
    public User getUserInfo(String accessToken) {
        var userInfo = discordClient.userInfo(accessToken);
        return new User(userInfo.username(), userInfo.email());
    }
}
