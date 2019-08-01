package com.robban.soccerBetting;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        ArrayList<Match> matches = new ArrayList<Match>(
                Arrays.asList(new Match("Südafrika" ,"Mexico"),
                        new Match("Uruguay"   ,"Südafrika"),
                        new Match("Uruguay"   ,"Mexico")
                )       );

        Group groupSmall = new Group("F", matches);
        System.out.println(groupSmall);
        System.out.println(groupSmall.getGroupOverview());

        groupSmall.getMatch(0).play("Südafrika",2,4);

        System.out.println(groupSmall);
        System.out.println(groupSmall.getGroupOverview());

        groupSmall.getMatch(1).play("Südafrika",1,3);

        System.out.println(groupSmall);
        System.out.println(groupSmall.getGroupOverview());

        groupSmall.getMatch(2).play("Uruguay",0,1);

        System.out.println(groupSmall);
        System.out.println(groupSmall.getGroupOverview());


        matches = new ArrayList<Match>(
                Arrays.asList(new Match("Südafrika" ,"Mexico"),
                        new Match("Uruguay"   ,"Frankreich"),
                        new Match("Südafrika" ,"Uruguay"),
                        new Match("Frankreich","Mexico"),
                        new Match("Frankreich","Südafrika"),
                        new Match("Mexico"    ,"Uruguay"))       );


        Group group = new Group("Gruppe A", matches);
        System.out.println(group);
        System.out.println(group.getGroupOverview());

        group.getMatch(0).play("Südafrika",2,0);
        System.out.println(group.getMatch(0));
        System.out.println(group.getGroupOverview());

        group.getMatch(1).play("Uruguay",1,3);
        System.out.println(group.getMatch(1));
        System.out.println(group.getGroupOverview());

        group.getMatch(2).play("Südafrika",1,2);
        System.out.println(group.getMatch(2));
        System.out.println(group.getGroupOverview());

        group.getMatch(3).play("Frankreich",4,1);
        System.out.println(group.getMatch(3));
        System.out.println(group.getGroupOverview());

        group.getMatch(4).play("Frankreich",2,2);
        System.out.println(group.getMatch(4));
        System.out.println(group.getGroupOverview());

        group.getMatch(5).play("Mexico",2,2);
        System.out.println(group.getMatch(5));
        System.out.println(group.getGroupOverview());

        System.out.println("\n\n" + group.getGroupOverviewRanked());

    }
}
