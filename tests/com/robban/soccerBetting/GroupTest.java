package com.robban.soccerBetting;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class GroupTest {

//    Team[] teams4 = { new Team("Russland"),
//                     new Team("Saudi Arabien"),
//                     new Team("Ägypten"),
//                     new Team("Uruguay") };
    String allTeams ="Russland,Saudi Arabien,Ägypten,Uruguay,teams.getTeam(" +
            "Portugal,Spanien,Marokko,Iran,teams.getTeam(" +
            "Frankreich,Australien,Peru,Dänemark,teams.getTeam(" +
            "Argentinien,Island,Kroatien,Nigeria,teams.getTeam(" +
            "Brasilien,Schweiz,Costa Rica,Serbien,teams.getTeam(" +
            "Deutschland,MexikoSchweden,Südkorea,teams.getTeam(" +
            "Belgien,PanamaTunesien,England,teams.getTeam(" +
            "Polen,Senegal,Kolumbien,Japan";

    Teams teams = new Teams(allTeams);

    ArrayList<Match> matchesT4 = new ArrayList<Match>(
                            Arrays.asList(new Match(teams.getTeam("Russland")     , teams.getTeam("Saudi Arabien")),
                                          new Match(teams.getTeam("Ägypten")      , teams.getTeam("Uruguay")),
                                          new Match(teams.getTeam("Russland")     , teams.getTeam("Ägypten")),
                                          new Match(teams.getTeam("Uruguay")      , teams.getTeam("Saudi Arabien")),
                                          new Match(teams.getTeam("Uruguay")      , teams.getTeam("Russland")),
                                          new Match(teams.getTeam("Saudi Arabien"), teams.getTeam("Ägypten")))       );

    
    
    Group groupT4 = new Group("Gruppe T4", matchesT4);


    Team[] teamsSemiFinal = {teams.getTeam("Russland"),
            teams.getTeam("Ägypten") };
    ArrayList<Match> matchesT2 = new ArrayList<Match>(
            Arrays.asList(new Match(teamsSemiFinal[0], teamsSemiFinal[1])) );

    Group groupSemiFinal = new Group("Semifinal", matchesT2);




    @Test
    void matchesPlayedTotal() {
        Assertions.assertEquals(0, groupT4.getCountMatchesPlayed() );

        groupT4.getMatch(0).play(teams.getTeam("Russland"),1,1);
        Assertions.assertEquals(1, groupT4.getCountMatchesPlayed() );

        groupT4.getMatch(1).play(teams.getTeam("Ägypten"),1,2);
        Assertions.assertEquals(2, groupT4.getCountMatchesPlayed() );

        groupT4.getMatch(2).play(teams.getTeam("Russland"),1,2);
        Assertions.assertEquals(3, groupT4.getCountMatchesPlayed() );

        groupT4.getMatch(3).play(teams.getTeam("Ägypten"),1,2);
        Assertions.assertEquals(4, groupT4.getCountMatchesPlayed() );

        groupT4.getMatch(4).play(teams.getTeam("Ägypten"),2,2);
        Assertions.assertEquals(5, groupT4.getCountMatchesPlayed() );

        groupT4.getMatch(5).play(teams.getTeam("Saudi Arabien"),2,2);
        Assertions.assertEquals(6, groupT4.getCountMatchesPlayed() );

    }


    @Test
    void matchesPlayedTeamByIndex() {
        Assertions.assertEquals(0, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.getMatch(0).play(teams.getTeam("Russland"),1,1);
        Assertions.assertEquals(0, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.getMatch(1).play(teams.getTeam("Ägypten"),1,2);
        Assertions.assertEquals(1, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.getMatch(2).play(teams.getTeam("Russland"),1,2);
        Assertions.assertEquals(1, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.getMatch(3).play(teams.getTeam("Uruguay"),1,2);
        Assertions.assertEquals(2, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.getMatch(4).play(teams.getTeam("Uruguay"),2,2);
        Assertions.assertEquals(3, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.getMatch(5).play(teams.getTeam("Saudi Arabien"),2,2);
        Assertions.assertEquals(3, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );
    }

    @Test
    void matchesPlayedByPlayMatchByIndex() {
        Assertions.assertEquals(0, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.playMatch(0,teams.getTeam("Russland"),1,1);
        Assertions.assertEquals(0, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.playMatch(1,teams.getTeam("Ägypten"),1,2);
        Assertions.assertEquals(1, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.playMatch(2,teams.getTeam("Russland"),1,2);
        Assertions.assertEquals(1, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.playMatch(3,teams.getTeam("Uruguay"),1,2);
        Assertions.assertEquals(2, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.playMatch(4,teams.getTeam("Uruguay"),2,2);
        Assertions.assertEquals(3, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );

        groupT4.playMatch(5,teams.getTeam("Saudi Arabien"),2,2);
        Assertions.assertEquals(3, groupT4.getCountMatchesPlayed(teams.getTeam("Uruguay")) );
    }

    @Test
    void matchesWon() {
        Assertions.assertEquals(0, groupT4.getCountMatchesWon(teams.getTeam("Uruguay")) );

        groupT4.playMatch(teams.getTeam("Russland") ,teams.getTeam("Saudi Arabien"),1,1);
        Assertions.assertEquals(0, groupT4.getCountMatchesWon(teams.getTeam("Uruguay")) );

        groupT4.playMatch(teams.getTeam("Ägypten")   ,teams.getTeam("Uruguay"),1,2);
        Assertions.assertEquals(1, groupT4.getCountMatchesWon(teams.getTeam("Uruguay")) );

        groupT4.playMatch(teams.getTeam("Russland") ,teams.getTeam("Ägypten"),1,2);
        Assertions.assertEquals(1, groupT4.getCountMatchesWon(teams.getTeam("Uruguay")) );

        groupT4.playMatch(teams.getTeam("Uruguay"),teams.getTeam("Saudi Arabien"),1,2);
        Assertions.assertEquals(1, groupT4.getCountMatchesWon(teams.getTeam("Uruguay")) );

        groupT4.playMatch(teams.getTeam("Uruguay"),teams.getTeam("Russland"),2,2);
        Assertions.assertEquals(1, groupT4.getCountMatchesWon(teams.getTeam("Uruguay")) );

        groupT4.playMatch(teams.getTeam("Saudi Arabien")    ,teams.getTeam("Ägypten"),2,2);
        Assertions.assertEquals(1, groupT4.getCountMatchesWon(teams.getTeam("Uruguay")) );
    }

    @Test
    void matchesLostSemiFinals() {
        groupSemiFinal.playMatch(0,teamsSemiFinal[0],1 ,0 );
        Assertions.assertEquals(0, groupSemiFinal.getCountMatchesLost(teamsSemiFinal[0]));
        Assertions.assertEquals(1, groupSemiFinal.getCountMatchesLost(teamsSemiFinal[1]));
    }

    @Test
    void matchesWonSemiFinals() {
        groupSemiFinal.playMatch(0,teamsSemiFinal[0],1 ,0 );
        Assertions.assertEquals(1, groupSemiFinal.getCountMatchesWon(teamsSemiFinal[0]));
        Assertions.assertEquals(0, groupSemiFinal.getCountMatchesWon(teamsSemiFinal[1]));
    }



    @Test
    void matchesDraw() {
    }

    @Test
    void getGoals() {
    }

    @Test
    void getCounterGoals() {
    }

    @Test
    void getDiffGoals() {
    }
}