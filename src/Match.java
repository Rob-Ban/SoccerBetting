package com.robban.soccerBetting;

import java.time.*;

public class Match {

    private Team teamA     , teamB;
    private int  goalsTeamA, goalsTeamB;
    private boolean isKnockOut = false;         // is it a knock-out match

    private boolean hasOccured = false;         // did the match take place?

    private LocalDateTime dateTime;             // When should this game take place?
    private String place;                       // Where should this game take place?

    private boolean hadTimeExtension = false;   // has the match got extra time?
    private boolean hadPenalty = false;         // was the match decided by penalty?

    public Match(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.isKnockOut = false;
    }

    public Match(Team teamA, Team teamB, boolean isKnockOut) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.isKnockOut = isKnockOut;
    }

    public Match(Team teamA, Team teamB, boolean isKnockOut, String place, LocalDateTime dateTime ) {
        this.place = place;
        this.dateTime = dateTime;
    }

    public void play(Team team, int goalTeamA, int goalTeamB) {
        if(this.hasOccured) {
            throw new IllegalArgumentException("IllegalArgument: This match took place already.");
        }
        if(this.isKnockOut && goalTeamA == goalTeamB) {
            throw new IllegalArgumentException("IllegalArgument: This match must not end in a draw.");
        }

        if(team.equals(this.teamA)) {
            this.goalsTeamA = goalTeamA;
            this.goalsTeamB = goalTeamB;
        }
        else {
            this.goalsTeamA = goalTeamB;
            this.goalsTeamB = goalTeamA;
        }
        this.hasOccured = true;
    }

    public void play(Team team, int goalsTeamA, int goalsTeamB, boolean hadTimeExtension, boolean hadPenalty) {
        this.play(team, goalsTeamA, goalsTeamB);
        this.hadTimeExtension = hadTimeExtension;
        this.hadPenalty = hadPenalty;
    }

    /** returns whether or not the game has a winner and looser provided it took place
     */
    public boolean isDraw() {
        if (!this.hasOccured ) {
            throw new IllegalArgumentException("IllegalArgument: This match must not end in a draw.");
        }
        return this.goalsTeamA == this.goalsTeamB;
    }

    public boolean isPlaying(Team team) {
        return team.equals(this.teamA) || team.equals(this.teamB);
    }

    public Team getWinner() {
        if (!this.hasOccured ) {
            throw new IllegalArgumentException("IllegalArgument: Match has not yet occured.");
        }
        if ( this.goalsTeamA == this.goalsTeamB ) { return null; }
        return this.goalsTeamA > this.goalsTeamB ? this.getTeamA() : this.getTeamB();
    }
    public Team getLooser() {
        if (!this.hasOccured ) {
            throw new IllegalArgumentException("IllegalArgument: Match has not yet occured.");
        }
        if ( this.goalsTeamA == this.goalsTeamB ) { return null; }
        return this.goalsTeamA < this.goalsTeamB ? this.getTeamA() : this.getTeamB();
    }


    @Override
    public String toString() {
        return "com.robban.soccerBetting.Match{" + teamA.getName() + " - " + teamB.getName() + "   " + goalsTeamA + " : " + goalsTeamB + '}';
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Team getTeamA() {
        return this.teamA;
    }

    public Team getTeamB() {
        return this.teamB;
    }

    public int getGoals(Team team) {
        if (!this.hasOccured ) { return 0; }
        if(team.equals(teamA)) return goalsTeamA;
        if(team.equals(teamB)) return goalsTeamB;
        throw new IllegalArgumentException("IllegalArgument: This team was not playing in this match");
    }

    public int getCounterGoals(Team team) {
        if (!this.hasOccured ) { return 0; }
        if(team.equals(teamA)) return goalsTeamB;
        if(team.equals(teamB)) return goalsTeamA;
        throw new IllegalArgumentException("IllegalArgument: This team was not playing in this match");
    }


    public int getGoalsTeamA() {
        return goalsTeamA;
    }

    public int getGoalsTeamB() {
        return goalsTeamB;
    }

    public boolean hasOccured() {
        return this.hasOccured;
    }

    public boolean isKnockOut() {
        return this.isKnockOut;
    }

    public boolean hadTimeExtension() {
        return this.hadTimeExtension;
    }

}
