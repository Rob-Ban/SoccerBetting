package com.robban.soccerBetting;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MatchTest {
    Teams teams = new Teams("Russland,Saudi Arabien,Ägypten,Uruguay," +
            "Portugal,Spanien,Marokko,Iran," +
            "Frankreich,Australien,Peru,Dänemark," +
            "Argentinien,Island,Kroatien,Nigeria," +
            "Brasilien,Schweiz,Costa Rica,Serbien," +
            "Deutschland,MexikoSchweden,Südkorea," +
            "Belgien,PanamaTunesien,England," +
            "Polen,Senegal,Kolumbien,Japan");
    Team team = teams.getTeam("Russland");
    Match match = new Match(team, teams.getTeam("Saudi Arabien"));
    Match matchKO = new Match(team, teams.getTeam("Saudi Arabien"), true);

    @Test
    void hasOccuredNo() {
        Assertions.assertFalse(match.hasOccured());
    }

    @Test
    void hasOccuredYes() {
        match.play(team, 1,1);
        Assertions.assertTrue(match.hasOccured());
    }

    @Test
    void isDrawYes() {
        match.play(team, 1,1);
        Assertions.assertTrue(match.isDraw());
    }

    @Test
    void isDrawYes2() {
        match.play(teams.getTeam("Russland"), 1,1);
        Assertions.assertTrue(match.isDraw());
    }

    @Test
    void isDrawNo() {
        match.play(team, 2,1);
        Assertions.assertFalse(match.isDraw());
    }

    @Test
    void isDrawNo2() {
        match.play(teams.getTeam("Russland"), 2,1);
        Assertions.assertFalse(match.isDraw());
    }

    @Test
    void isDrawNotPlayed() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> match.isDraw());
    }

    @Test
    void isPlayingYes() {
        Assertions.assertTrue(match.isPlaying(team));
    }

    @Test
    void isPlayingYes2() {
        Assertions.assertTrue(match.isPlaying(teams.getTeam("Russland")));
    }

    @Test
    void isPlayingNo() {
        Assertions.assertFalse(match.isPlaying(new Team("England")));
    }

    @Test
    void isPlayingNo2() {
        Assertions.assertFalse(match.isPlaying(teams.getTeam("England")));
    }

    @Test
    void winner() {
        match.play(teams.getTeam("Saudi Arabien"),0,1);
        Assertions.assertEquals(team, match.getWinner());
    }

    @Test
    void looser() {
        match.play(team, 1,2);
        Assertions.assertEquals(team.getName(), match.getLooser().getName());
    }

    @Test
    void winnerNotPlayed() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> match.getWinner());
    }

    @Test
    void getGoalsTeamA() {
        match.play(team, 2,1);
        Assertions.assertEquals(2, match.getGoalsTeamA());
    }

    @Test
    void getGoalsTeamB() {
        match.play(team, 2,1);
        Assertions.assertEquals(1, match.getGoalsTeamB());
    }
}