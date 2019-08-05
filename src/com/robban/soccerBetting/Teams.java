package com.robban.soccerBetting;

import java.util.HashMap;
import java.util.Map;

public class Teams {

    private final Map<String, Team> teams;

    public Teams(String teams) {
        this.teams = new HashMap<>();
        for (String team: teams.split(",")) {
            this.teams.put(team, new Team(team));
        }
    }

    public Team getTeam(String teamName) {
        if (teams.containsKey(teamName)) {
            return teams.get(teamName);
        }
        else {
            throw new IllegalArgumentException("This team is not participating in this tournament");
        }
    }

}
