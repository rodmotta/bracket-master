package com.github.rodmotta.bracket_master.core.model;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private int round;
    private List<Match> matches;

    public Round(int round) {
        this.round = round;
        this.matches = new ArrayList<>();
    }

    public void addMatch(Match match) {
        matches.add(match);
    }
}
