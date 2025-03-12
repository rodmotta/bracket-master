package com.github.rodmotta.bracket_master.core.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Tournament {
    private UUID id;
    private String title;
    private LocalDateTime datetime;
    private User organizer;
    private int participantLimit;
    private List<Participant> participants;
    private List<Round> rounds;

    public Tournament(UUID id, String title, LocalDateTime datetime, User organizer, int participantLimit) {
        this.id = id;
        this.title = title;
        this.datetime = datetime;
        this.organizer = organizer;
        this.participantLimit = validLimitOfParticipants(participantLimit);
        this.participants = new ArrayList<>();
    }

    //fixme: value object
    private int validLimitOfParticipants(int maximumParticipants) {
        Set<Integer> validNumberOfParticipants = Set.of(4, 8, 16, 32);
        if (validNumberOfParticipants.contains(maximumParticipants)) {
            return maximumParticipants;
        }
        throw new IllegalArgumentException(); //fixme
    }

    public void addParticipant(Participant participant) {
        if (participants.size() >= participantLimit) {
            throw new IllegalArgumentException(); //fixme
        }
        participants.add(participant);
    }

    public List<Round> generateBrackets() {

        int totalRounds = (int) (Math.log(participantLimit) / Math.log(2));
        var rounds = new ArrayList<Round>();

        int matchesInRound = participantLimit / 2;

        for (int i = 0; i < totalRounds; i++) {
            Round round = new Round(i);
            rounds.add(round);

            for (int o = 0; o < matchesInRound; o++) {

                Match match = new Match(null, null);
                round.addMatch(match);
            }

            matchesInRound /= 2;
        }

        return rounds;
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

    public int getParticipantLimit() {
        return participantLimit;
    }
}
