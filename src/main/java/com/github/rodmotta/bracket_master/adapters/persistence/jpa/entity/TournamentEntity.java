package com.github.rodmotta.bracket_master.adapters.persistence.jpa.entity;

import com.github.rodmotta.bracket_master.core.model.Tournament;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tournaments")
public class TournamentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private int participantLimit;

    public TournamentEntity() {
    }

    public TournamentEntity(Tournament tournament) {
        this.id = tournament.getId();
        this.title = tournament.getTitle();
        this.participantLimit = tournament.getParticipantLimit();
    }

    public Tournament toModel() {
        return new Tournament(id, title, null, null, participantLimit);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(int participantLimit) {
        this.participantLimit = participantLimit;
    }
}
