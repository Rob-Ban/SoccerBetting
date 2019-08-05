package com.robban.soccerBetting;

public class Team {

    final private String name;

    public Team (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode(){
        return (int) this.name.toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Team) {
            return this.name.equalsIgnoreCase(((Team) object).getName());
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
