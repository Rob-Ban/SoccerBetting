public class Team {

    private String name;

    Team (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Team team) {
        return this.name.equalsIgnoreCase(team.getName());
    }

    public boolean equals(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    public String toString() {
        return name;
    }

}
