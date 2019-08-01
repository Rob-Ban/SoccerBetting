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
            return this.name.equals(((Team) object).getName());
        }
        return false;
    }

    public boolean equals(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
