package com.github.rodmotta.bracket_master.adapters.controller;

import com.github.rodmotta.bracket_master.adapters.controller.dto.TournamentRequest;
import com.github.rodmotta.bracket_master.adapters.controller.dto.TournamentResponse;
import com.github.rodmotta.bracket_master.core.model.Tournament;
import com.github.rodmotta.bracket_master.core.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TournamentResponse> findAll() {
        return tournamentService.findAll().stream()
                .map(TournamentResponse::new)
                .toList();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TournamentResponse findDetails(@PathVariable UUID id) {
        Tournament tournament = tournamentService.findById(id);
        return new TournamentResponse(tournament);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TournamentRequest request) {
        Tournament tournament = request.toDomain();
        tournamentService.create(tournament);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable UUID id, @RequestBody TournamentRequest request) {
        Tournament tournament = request.toDomain();
        tournamentService.updateById(id, tournament);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        tournamentService.deleteById(id);
    }
}
