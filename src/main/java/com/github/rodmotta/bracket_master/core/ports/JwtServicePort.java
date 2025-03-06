package com.github.rodmotta.bracket_master.core.ports;

public interface JwtServicePort {
    String generate(String subject);
    boolean validate(String token);
    String getSubject(String token);
}
