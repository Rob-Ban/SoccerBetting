package com.robban.soccerBetting;


import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

// https://www.dfb.de/vereinsmitarbeiter/abteilungsleiterin-fussball/artikel/turnierplaene-als-download-85/

/**
 * Group ranks 2 or more teams into an order by playing matches against each other.
 */

public class Group {

    final private String name;
    final private List<Match> matches = new ArrayList<>();
    final private List<Team> teamsDisplayOrder = new ArrayList<>();

    public Group(String name, ArrayList<Match> matches) {
        this.name = name;

        for(Match match : matches) {
            this.matches.add(match);

            Team team = match.getTeamA();
            if(!teamsDisplayOrder.contains(team)) { teamsDisplayOrder.add(team); }
            team = match.getTeamB();
            if(!teamsDisplayOrder.contains(team)) { teamsDisplayOrder.add(team); }
        }
    }

    @Override
    public String toString() {
        return teamsDisplayOrder.stream()
                                .map( team -> team.toString() )
                                .collect( Collectors.joining( ", " ) );
    }

    public List<Team> getTeamsRanked() {
        return new ArrayList<Team>(teamsDisplayOrder.stream()
                                .sorted(TeamInGroupComparator)
                                .collect(Collectors.toList()));
    }

    public Comparator<Team> TeamInGroupComparator = new Comparator<Team>() {
        public int compare(Team teamA, Team teamB) {
            int delta = calculatePoints(teamB) - calculatePoints(teamA);
            if(delta != 0) { return delta; }

            delta = getDiffGoals(teamB) -  getDiffGoals(teamA);
            if(delta != 0) { return delta; }

            delta = getCountGoals(teamB) - getCountGoals(teamA);
            if(delta != 0) { return delta; }

            Match match = getMatch(teamB, teamA);
            delta = match.getGoalsTeamA() - match.getGoalsTeamB();
            if(delta != 0) { return delta; }

            return teamsDisplayOrder.indexOf(teamB) - teamsDisplayOrder.indexOf(teamA);
        }
    };


    public String getGroupOverview() {
        return getOverview(teamsDisplayOrder);
    }
    public String getGroupOverviewRanked() {
        return getOverview(getTeamsRanked());
    }
    private String getOverview(List<Team> teams) {
        String out = String.format("%25sM   W   D   L   G   C   +/- P%n","");
        for(Team team : teams) {
            out += String.format("%20s\t%s%n", team.getName(), getAchievement(team));
        }
        return out;
    }
    public String getAchievement(Team team) {
        //                      M    W    D    L    G    C  +/-    P
        return String.format("%2d  %2d  %2d  %2d  %2d  %2d  %2d  %2d",
                getCountMatchesPlayed(team),
                getCountMatchesWon(team),
                getCountMatchesDraw(team),
                getCountMatchesLost(team),
                getCountGoals(team),
                getCountCounterGoals(team),
                getDiffGoals(team),
                calculatePoints(team));
    }

    Match getMatch(int matchNumber) {
        if(matchNumber >= this.matches.size() ) {
            throw new IndexOutOfBoundsException("No match no. " + matchNumber +
                                                ". There are only " + this.matches.size() + " in group " + this.name + ".");
        }
        return this.matches.get(matchNumber);
    }

    public Match getMatch(Team teamA, Team teamB) {
        return this.matches.stream()
                           .filter(match -> match.isPlaying(teamA) && match.isPlaying(teamB))
                           .findFirst()
                           .orElse(null);
    }


    public void playMatch(int index, Team team, int goalTeamA, int goalTeamB) {
        Match match = matches.get(index);
        if (match.isPlaying(team)) {
            match.play(team, goalTeamA, goalTeamB);
        }
    }

    public void playMatch(Team teamA, Team teamB, int goalTeamA, int goalTeamB)  {
        this.matches.stream()
                    .filter(match -> !match.hasOccured())
                    .filter(match -> match.isPlaying(teamA) && match.isPlaying(teamB))
                    .collect(Collectors.toList())
                    .get(0).play(teamA, goalTeamA, goalTeamB);
    }


    int getCountMatchesPlayed() {
        return (int) this.matches.stream()
                .filter(Match::hasOccured)
                .count();
    }

    int calculatePoints(Team team) {
        return 3 * getCountMatchesWon(team) + 1 * getCountMatchesDraw(team);
    }

    int getCountMatchesPlayed(Team team) {
        return (int) this.matches.stream()
                                .filter(Match::hasOccured)
                                .filter(match -> match.isPlaying(team))
                                .count();
    }


    int getCountMatchesWon(Team team) {
        return (int) this.matches.stream()
                                .filter(Match::hasOccured)
                                .filter(match -> !match.isDraw())
                                .filter(match -> team.equals(match.getWinner()))
                                .count();
    }


    int getCountMatchesLost(Team team) {
        return (int) this.matches.stream()
                                .filter(Match::hasOccured)
                                .filter(match -> !match.isDraw())
                                .filter(match -> team.equals(match.getLooser()))
                                .count();
    }

    int getCountMatchesDraw(Team team) {
        return (int) this.matches.stream()
                                .filter(Match::hasOccured)
                                .filter(Match::isDraw)
                                .filter(match -> match.isPlaying(team))
                                .count();
    }

    int getCountGoals(Team team) {
        return (int) this.matches.stream()
                .filter(Match::hasOccured)
                .filter(match -> match.isPlaying(team))
                .mapToInt(match -> match.getGoals(team))
                .sum();
    }

    int getCountCounterGoals(Team team) {
        return (int) this.matches.stream()
                .filter(Match::hasOccured)
                .filter(match -> match.isPlaying(team))
                .mapToInt(match -> match.getCounterGoals(team))
                .sum();
    }

    int getDiffGoals(Team team) {
        return getCountGoals(team) - getCountCounterGoals(team);
    }

}
