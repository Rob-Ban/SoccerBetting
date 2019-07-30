import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class GroupTest {

    Team[] teams = { new Team("Südafrika"),
                     new Team("Mexico"),
                     new Team("Uruguay"),
                     new Team("Frankreich") };
    ArrayList<Match> matches = new ArrayList<Match>(
                            Arrays.asList(new Match("Südafrika" ,"Mexico"),
                                          new Match("Uruguay"   ,"Frankreich"),
                                          new Match("Südafrika" ,"Uruguay"),
                                          new Match("Frankreich","Mexico"),
                                          new Match("Frankreich","Südafrika"),
                                          new Match("Mexico"    ,"Uruguay"))       );

    Group group = new Group("Gruppe A", matches);

    @Test
    void matchesPlayedTotal() {
        Assertions.assertEquals(0, group.getCountMatchesPlayed() );

        group.getMatch(0).play("Südafrika",1,1);
        Assertions.assertEquals(1, group.getCountMatchesPlayed() );

        group.getMatch(1).play("Uruguay",1,2);
        Assertions.assertEquals(2, group.getCountMatchesPlayed() );

        group.getMatch(2).play("Südafrika",1,2);
        Assertions.assertEquals(3, group.getCountMatchesPlayed() );

        group.getMatch(3).play("Frankreich",1,2);
        Assertions.assertEquals(4, group.getCountMatchesPlayed() );

        group.getMatch(4).play("Frankreich",2,2);
        Assertions.assertEquals(5, group.getCountMatchesPlayed() );

        group.getMatch(5).play("Mexico",2,2);
        Assertions.assertEquals(6, group.getCountMatchesPlayed() );

    }


    @Test
    void matchesPlayedTeamByIndex() {
        Assertions.assertEquals(0, group.getCountMatchesPlayed("Frankreich") );

        group.getMatch(0).play("Südafrika",1,1);
        Assertions.assertEquals(0, group.getCountMatchesPlayed("Frankreich") );

        group.getMatch(1).play("Uruguay",1,2);
        Assertions.assertEquals(1, group.getCountMatchesPlayed("Frankreich") );

        group.getMatch(2).play("Südafrika",1,2);
        Assertions.assertEquals(1, group.getCountMatchesPlayed("Frankreich") );

        group.getMatch(3).play("Frankreich",1,2);
        Assertions.assertEquals(2, group.getCountMatchesPlayed("Frankreich") );

        group.getMatch(4).play("Frankreich",2,2);
        Assertions.assertEquals(3, group.getCountMatchesPlayed("Frankreich") );

        group.getMatch(5).play("Mexico",2,2);
        Assertions.assertEquals(3, group.getCountMatchesPlayed("Frankreich") );
    }

    @Test
    void matchesPlayedByPlayMatchByIndex() {
        Assertions.assertEquals(0, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch(0,"Südafrika",1,1);
        Assertions.assertEquals(0, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch(1,"Uruguay",1,2);
        Assertions.assertEquals(1, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch(2,"Südafrika",1,2);
        Assertions.assertEquals(1, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch(3,"Frankreich",1,2);
        Assertions.assertEquals(2, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch(4,"Frankreich",2,2);
        Assertions.assertEquals(3, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch(5,"Mexico",2,2);
        Assertions.assertEquals(3, group.getCountMatchesPlayed("Frankreich") );
    }

    @Test
    void matchesPlayedByPlayMatchByTeamNames() {
        Assertions.assertEquals(0, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch("Südafrika" ,"Mexico",1,1);
        Assertions.assertEquals(0, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch("Uruguay"   ,"Frankreich",1,2);
        Assertions.assertEquals(1, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch("Südafrika" ,"Uruguay",1,2);
        Assertions.assertEquals(1, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch("Frankreich","Mexico",1,2);
        Assertions.assertEquals(2, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch("Frankreich","Südafrika",2,2);
        Assertions.assertEquals(3, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch("Mexico"    ,"Uruguay",2,2);
        Assertions.assertEquals(3, group.getCountMatchesPlayed("Frankreich") );
    }

    @Test
    void matchesWon() {
        Assertions.assertEquals(0, group.getCountMatchesWon("Frankreich") );

        group.playMatch("Südafrika" ,"Mexico",1,1);
        Assertions.assertEquals(0, group.getCountMatchesPlayed("Frankreich") );

        group.playMatch("Uruguay"   ,"Frankreich",1,2);
        Assertions.assertEquals(1, group.getCountMatchesWon("Frankreich") );

        group.playMatch("Südafrika" ,"Uruguay",1,2);
        Assertions.assertEquals(1, group.getCountMatchesWon("Frankreich") );

        group.playMatch("Frankreich","Mexico",1,2);
        Assertions.assertEquals(1, group.getCountMatchesWon("Frankreich") );

        group.playMatch("Frankreich","Südafrika",2,2);
        Assertions.assertEquals(1, group.getCountMatchesWon("Frankreich") );

        group.playMatch("Mexico"    ,"Uruguay",2,2);
        Assertions.assertEquals(1, group.getCountMatchesWon("Frankreich") );
    }

    @Test
    void matchesLost() {
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