package com.github.rodmotta.bracket_master.core.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Tournament {
    private Long id;
    private String title;
    private LocalDateTime datetime;
    private User organizer;
    private int maximumParticipants;
    private List<Participant> participants;
    private List<Round> rounds;

    public Tournament(String title, LocalDateTime datetime, User organizer, int maximumParticipants) {
        this.title = title;
        this.datetime = datetime;
        this.organizer = organizer;
        this.maximumParticipants = validNumberOfParticipants(maximumParticipants);
        this.participants = new ArrayList<>();
    }

    //fixme: value object
    private int validNumberOfParticipants(int maximumParticipants) {
        Set<Integer> validNumberOfParticipants = Set.of(4, 8, 16, 32);
        if (validNumberOfParticipants.contains(maximumParticipants)) {
            return maximumParticipants;
        }
        throw new IllegalArgumentException(); //fixme
    }

    public void addParticipant(Participant participant) {
        if (participants.size() >= maximumParticipants) {
            throw new IllegalArgumentException(); //fixme
        }
        participants.add(participant);
    }

    public List<Round> generateBrackets() {

        int totalRounds = (int) (Math.log(maximumParticipants) / Math.log(2));
        var rounds = new ArrayList<Round>();

        int matchesInRound = maximumParticipants / 2;

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
}
