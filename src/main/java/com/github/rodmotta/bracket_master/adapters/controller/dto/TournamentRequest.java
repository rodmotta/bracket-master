package com.github.rodmotta.bracket_master.adapters.controller.dto;

import com.github.rodmotta.bracket_master.core.model.Tournament;

public record TournamentRequest(
        String title,
        int participantLimit
) {

    public Tournament toDomain() {
        return new Tournament(null, title, null, null, participantLimit);
    }
}
