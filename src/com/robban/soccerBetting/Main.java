package com.robban.soccerBetting;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        String allTeams ="Russland,Saudi Arabien,Ägypten,Uruguay," +
                "Portugal,Spanien,Marokko,Iran," +
                "Frankreich,Australien,Peru,Dänemark," +
                "Argentinien,Island,Kroatien,Nigeria," +
                "Brasilien,Schweiz,Costa Rica,Serbien," +
                "Deutschland,MexikoSchweden,Südkorea," +
                "Belgien,PanamaTunesien,England," +
                "Polen,Senegal,Kolumbien,Japan";

        Teams teams = new Teams(allTeams);

        ArrayList<Match> matches = new ArrayList<Match>(
                Arrays.asList(new Match(teams.getTeam("Südafrika") ,teams.getTeam("Mexico")),
                              new Match(teams.getTeam("Uruguay")   ,teams.getTeam("Südafrika")),
                              new Match(teams.getTeam("Uruguay")   ,teams.getTeam("Mexico"))));

        Group groupSmall = new Group("F", matches);
        System.out.println(groupSmall);
        System.out.println(groupSmall.getGroupOverview());

        groupSmall.getMatch(0).play(teams.getTeam("Südafrika"),2,4);

        System.out.println(groupSmall);
        System.out.println(groupSmall.getGroupOverview());

        groupSmall.getMatch(1).play(teams.getTeam("Südafrika"),1,3);

        System.out.println(groupSmall);
        System.out.println(groupSmall.getGroupOverview());

        groupSmall.getMatch(2).play(teams.getTeam("Uruguay"),0,1);

        System.out.println(groupSmall);
        System.out.println(groupSmall.getGroupOverview());


        matches = new ArrayList<Match>(
                Arrays.asList(new Match(teams.getTeam("Südafrika") ,teams.getTeam("Mexico")),
                        new Match(teams.getTeam("Uruguay")   ,teams.getTeam("Frankreich")),
                        new Match(teams.getTeam("Südafrika") ,teams.getTeam("Uruguay")),
                        new Match(teams.getTeam("Frankreich"),teams.getTeam("Mexico")),
                        new Match(teams.getTeam("Frankreich"),teams.getTeam("Südafrika")),
                        new Match(teams.getTeam("Mexico")    ,teams.getTeam("Uruguay")))       );


        Group group = new Group("Gruppe A", matches);
        System.out.println(group);
        System.out.println(group.getGroupOverview());

        group.getMatch(0).play(teams.getTeam("Südafrika"),2,0);
        System.out.println(group.getMatch(0));
        System.out.println(group.getGroupOverview());

        group.getMatch(1).play(teams.getTeam("Uruguay"),1,3);
        System.out.println(group.getMatch(1));
        System.out.println(group.getGroupOverview());

        group.getMatch(2).play(teams.getTeam("Südafrika"),1,2);
        System.out.println(group.getMatch(2));
        System.out.println(group.getGroupOverview());

        group.getMatch(3).play(teams.getTeam("Frankreich"),4,1);
        System.out.println(group.getMatch(3));
        System.out.println(group.getGroupOverview());

        group.getMatch(4).play(teams.getTeam("Frankreich"),2,2);
        System.out.println(group.getMatch(4));
        System.out.println(group.getGroupOverview());

        group.getMatch(5).play(teams.getTeam("Mexico"),2,2);
        System.out.println(group.getMatch(5));
        System.out.println(group.getGroupOverview());

        System.out.println("\n\n" + group.getGroupOverviewRanked());

    }
}
