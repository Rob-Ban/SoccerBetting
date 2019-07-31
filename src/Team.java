package com.robban.soccerBetting;

public class Team {

    final private String name;

    public Team (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int hashCode(){
        return (int) this.name.hashCode();
    }

    public boolean equals(Team team) {
        return equals(team.getName());
    }

    public boolean equals(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    public String toString() {
        return name;
    }
}
