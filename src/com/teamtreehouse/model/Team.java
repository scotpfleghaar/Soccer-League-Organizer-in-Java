package com.teamtreehouse.model;

import java.util.ArrayList;

public class Team {
    public ArrayList<Player> players;
    public String teamName;
    public String coach;

    public Team(String teamName, String coach) {
        this.teamName = teamName;
        this.coach = coach;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCoach() {
        return coach;
    }

    public void addPlayer(Player player){
        if (players.size() < 12) {
            this.players.add(player);
            System.out.printf("Added %s to %s's Team", player.getFirstName(), this.coach);
        } else {
            System.out.printf("%s's Team is full with %d players", this.coach, this.players.size());
        }
    }

    public void removePlayer(Player player){
        if (players.size() < 12) {
            // Display a list of players to remove
            this.players.remove(player);
            System.out.printf("removed %s from %s's Team", player.getFirstName(), this.coach);
        } else {
            System.out.printf("%s's Team is full with %d players", this.coach, this.players.size());
        }
    }


}
