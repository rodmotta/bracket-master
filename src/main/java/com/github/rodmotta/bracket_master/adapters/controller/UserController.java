package com.github.rodmotta.bracket_master.adapters.controller;

import com.github.rodmotta.bracket_master.adapters.controller.dto.AuthCodeRequest;
import com.github.rodmotta.bracket_master.adapters.controller.dto.TokenResponse;
import com.github.rodmotta.bracket_master.core.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("auth/discord")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@RequestBody AuthCodeRequest request) {
        String accessToken = userService.authenticate(request.code());
        return new TokenResponse(accessToken);
    }
}
