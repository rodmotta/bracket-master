package com.github.rodmotta.bracket_master.adapters.controller.dto;

import com.github.rodmotta.bracket_master.core.model.Tournament;

import java.util.UUID;

public record TournamentResponse(
        UUID id,
        String title,
        int participantLimit
) {
    public TournamentResponse(Tournament tournament) {
        this(tournament.getId(), tournament.getTitle(), tournament.getParticipantLimit());
    }
}
