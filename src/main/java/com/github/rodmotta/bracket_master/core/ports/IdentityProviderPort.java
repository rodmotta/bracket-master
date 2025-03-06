package com.github.rodmotta.bracket_master.core.ports;

import com.github.rodmotta.bracket_master.core.model.User;

public interface IdentityProviderPort {
    String getAccessToken(String authCode);
    User getUserInfo(String accessToken);
}
