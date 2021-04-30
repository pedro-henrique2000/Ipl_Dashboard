package io.projects.demo.controller;

import io.projects.demo.model.Match;
import io.projects.demo.model.Team;
import io.projects.demo.repository.MatchRepository;
import io.projects.demo.repository.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamRepository.findByTeamName(teamName);
        List<Match> matchList = this.matchRepository.findLatestMatchesByTeam(teamName, 4);
        team.setMatches(matchList);

        return team;
    }

}
