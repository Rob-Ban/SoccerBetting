import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://www.dfb.de/vereinsmitarbeiter/abteilungsleiterin-fussball/artikel/turnierplaene-als-download-85/


public class Group {

    private class TeamRank extends Team implements Comparable<TeamRank> {
        private int defaultRank;                        // to ensure that teams are always returned in the same order
                                                        // even when they have not played yet

        TeamRank(String name, int defaultRank) {
            super(name);
            this.defaultRank = defaultRank;
        }

        @Override
        public int compareTo(TeamRank otherTeam) {
            int delta = calculatePoints(otherTeam) - calculatePoints(this);
            if(delta != 0) { return delta; }

            delta = getDiffGoals(otherTeam) -  getDiffGoals(this);
            if(delta != 0) { return delta; }

            delta = getCountGoals(otherTeam) - getCountGoals(this);
            if(delta != 0) { return delta; }

            Match match = getMatch(otherTeam, this);
            delta = match.getGoalsTeamA() - match.getGoalsTeamB();
            if(delta != 0) { return delta; }

            return this.defaultRank - otherTeam.defaultRank;
        }
    }

    private String name;
    private List<Match> matches = new ArrayList<>();
    public List<TeamRank> teams = new ArrayList<>();

    Group(String name, ArrayList<Match> matches) {
        this.name = name;

        for(Match match : matches) {
            this.matches.add(match);
            if(!teams.stream().anyMatch(team -> team.equals(match.getTeamA()))) {
                teams.add(new TeamRank(match.getTeamA().getName(), teams.size() ));
            };
            if(!teams.stream().anyMatch(team -> team.equals(match.getTeamB()))) {
                teams.add(new TeamRank(match.getTeamB().getName(), teams.size() ));
            };
        }
    }


    public String toString() {
        String out = "";
        Collections.sort(teams);
        for(Team team : teams) {
            out += ", " + team.getName() ;
        }
        return out.substring(2);
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

    public String getGroupOverview() {
        String out = String.format("%25sM   W   D   L   G   C   +/- P%n","");
        Collections.sort(teams);
        for(TeamRank teamRank : teams) {
            out += String.format("%20s\t%s%n", teamRank.getName(), getAchievement(teamRank));
        }
        return out;
    }

    public Match getMatch(int matchNumber) {
        if(matchNumber >= this.matches.size() ) {
            throw new IndexOutOfBoundsException("No match no. " + matchNumber +
                                                ". There are only " + this.matches.size() + " in group " + this.name + ".");
        }
        return this.matches.get(matchNumber);
    }

    public Match getMatch(Team teamA, Team teamB) {
        for(Match match : this.matches) {
            if(match.isPlaying(teamA) && match.isPlaying(teamB) ) {
                return match;
            }
        }
        return null;
    }
    public Match getMatch(String teamA, String teamB) {
        return getMatch(new Team(teamA), new Team(teamB));
    }

    public void playMatch(int index, Team team, int goalTeamA, int goalTeamB) {
        Match match = matches.get(index);
        if (match.isPlaying(team)) {
            match.play(team, goalTeamA, goalTeamB);
        }
    }
    public void playMatch(int index, String teamName, int goalTeamA, int goalTeamB) {
        Match match = matches.get(index);
        if (match.isPlaying(teamName)) {
            match.play(teamName, goalTeamA, goalTeamB);
        }
    }

    public void playMatch(Team teamA, Team teamB, int goalTeamA, int goalTeamB) throws Exception {
//        getMatch(teamA, teamB).play(teamA, goalTeamA, goalTeamB);
        List<Match> matches = this.matches.stream()
                                    .filter(match -> match.isPlaying(teamA) && match.isPlaying(teamB))
                                    .collect(Collectors.toList());
        if(matches.size() > 1) {
            throw new Exception("Internal Error: Duplicate matches in group " + this.name);
        }
        else if(matches.size() == 0) {
            throw new NoSuchElementException("Illegal argument: These teams (" + teamA.getName() + " : " + teamB.getName() +
                    ") do not play in group " + this.name);
        }
        else { // m.size = 1    so we got one match ... let's see whether it was played already
            if (matches.get(0).hasOccured()) {
                throw new IllegalArgumentException("Illegal argument: These teams (" + teamA.getName() + " : " + teamB.getName() +
                        ")have already played in the group " + this.name);
            }
            matches.get(0).play(teamA, goalTeamA, goalTeamB);
        }
    }
    public void playMatch(String teamNameA, String teamNameB, int goalTeamA, int goalTeamB) {
        getMatch(teamNameA, teamNameB).play(teamNameA, goalTeamA, goalTeamB);
    }

    public int getCountMatchesPlayed() {
        return (int) this.matches.stream()
                .filter(Match::hasOccured)
                .count();
    }

    public int calculatePoints(Team team) {
        return 3 * getCountMatchesWon(team) + 1 * getCountMatchesDraw(team);
    }

    public int getCountMatchesPlayed(Team team) {
        return (int) this.matches.stream()
                                .filter(Match::hasOccured)
                                .filter(match -> match.isPlaying(team))
                                .count();
    }
    public int getCountMatchesPlayed(String teamName) {
        return getCountMatchesPlayed(new Team(teamName));
    }

    public int getCountMatchesWon(Team team) {
        return (int) this.matches.stream()
                                .filter(Match::hasOccured)
                                .filter(match -> !match.isDraw())
                                .filter(match -> team.equals(match.getWinner()))
                                .count();
    }
    public int getCountMatchesWon(String teamName) {
        return getCountMatchesWon(new Team(teamName));
    }

    public int getCountMatchesLost(Team team) {
        return (int) this.matches.stream()
                                .filter(Match::hasOccured)
                                .filter(match -> !match.isDraw())
                                .filter(match -> team.equals(match.getLooser()))
                                .count();
    }
    public int getCountMatchesLost(String teamName) {
        return getCountMatchesLost(new Team(teamName));
    }

    public int getCountMatchesDraw(Team team) {
        return (int) this.matches.stream()
                                .filter(Match::hasOccured)
                                .filter(Match::isDraw)
                                .filter(match -> match.isPlaying(team))
                                .count();
    }
    public int getCountMatchesDraw(String teamName) {
        return getCountMatchesDraw(new Team(teamName));
    }

    public int getCountGoals(Team team) {
        return (int) this.matches.stream()
                .filter(Match::hasOccured)
                .filter(match -> match.isPlaying(team))
                .mapToInt(match -> match.getGoals(team))
                .sum();
    }

    public int getCountCounterGoals(Team team) {
        return (int) this.matches.stream()
                .filter(Match::hasOccured)
                .filter(match -> match.isPlaying(team))
                .mapToInt(match -> match.getCounterGoals(team))
                .sum();
    }

    public int getDiffGoals(Team team) {
        return getCountGoals(team) - getCountCounterGoals(team);
    }

}
