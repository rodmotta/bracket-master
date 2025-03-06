package com.github.rodmotta.bracket_master.adapters.client;

import com.github.rodmotta.bracket_master.adapters.client.dto.DiscordTokenResponse;
import com.github.rodmotta.bracket_master.adapters.client.dto.DiscordUserInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@Component
public class DiscordClient {
    private final RestClient restClient = RestClient.create("https://discord.com");

    @Value("${discord.client-id}")
    private String clientId;
    @Value("${discord.client-secret}")
    private String clientSecret;

    public DiscordTokenResponse exchangeCode(String code) {
        String REDIRECT_URI = "http://localhost:3553/callback";

        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "authorization_code");
        body.put("code", code);
        body.put("redirect_uri", REDIRECT_URI);

        String strBody = body.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        Consumer<HttpHeaders> headers = httpHeaders -> {
            httpHeaders.setContentType(APPLICATION_FORM_URLENCODED);
            httpHeaders.setBasicAuth(clientId, clientSecret);
        };

        var response = restClient
                .post()
                .uri("/api/v10/oauth2/token")
                .body(strBody)
                .headers(headers)
                .retrieve()
                .body(DiscordTokenResponse.class);

        return response;
    }

    public DiscordUserInfoResponse userInfo(String accessToken) {

        Consumer<HttpHeaders> headers = httpHeaders -> {
            httpHeaders.setBearerAuth(accessToken);
        };

        var response = restClient
                .get()
                .uri("/api/v10/users/@me")
                .headers(headers)
                .retrieve()
                .body(DiscordUserInfoResponse.class);

        return response;
    }
}
