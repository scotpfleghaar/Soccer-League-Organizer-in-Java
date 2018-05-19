package com.teamtreehouse.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LeagueHelper {
    public ArrayList<Player> players;
    public ArrayList<Team> teams;
    private Map<String, String> menu;
    private BufferedReader reader;

    public LeagueHelper(ArrayList<Player> players) {
        this.players = players;
        this.teams = new ArrayList<Team>();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.menu = new HashMap<String, String>();
        this.menu.put("create", "Create Team");
        this.menu.put("add", "Add Player to the Queue");
        this.menu.put("build", "Add Players to a Team");
        this.menu.put("remove", "Remove Player from Team");
        this.menu.put("reports", "View Reports");
        this.menu.put("leagueBalance", "League Balance Report");
        this.menu.put("quit", "Quit the LeagueBuilder");
    }

    private String promptAction() throws IOException {
        System.out.printf("There are currently %d registered players.%n", players.size());
        for (Map.Entry<String, String> option : this.menu.entrySet()){
            System.out.printf("%s - %s %n", option.getKey(), option.getValue());
        }
        System.out.print("What do you want to do: ");
        String choice = this.reader.readLine();
        return choice.trim().toLowerCase();
    }

    public void run(){
        String choice = "";
        do {
            try {
                choice = promptAction();
                switch(choice){
                    case "create":
                        Team team = promptNewTeam();
                        addTeam(team);
                        System.out.printf("Team Created! %s is coaching the %s%n", team.getCoach(), team.getTeamName());
                        System.out.printf("There are %d teams%n%n", teams.size());
                        break;
                    case "add":
                        Player player = promptNewPlayer();
                        addPlayer(player);
                        System.out.printf("%s added! %n%n", player.getFirstName());
                        break;
                    case "build":
                        int teamIndex =  promptTeam();
                        addPlayersToTeam(teams.get(teamIndex));
                        System.out.printf("You have choosen %s%n", teams.get(teamIndex).teamName);
                        break;
                    case "remove":
                        System.out.println("Removeing Player...");
                        break;
                    case "reports":
                        System.out.println("reports...");
                        break;
                    case "leagueBalance":
                        System.out.println("leagueBalance....");
                        break;
                    case "quit":
                        System.out.println("Thanks!");
                        break;
                    case "choose":
//                        String artist = promptArtist();
//                        Song artistSong = promptSongForArtist(artist);
//                        mSongQueue.add(artistSong);
//                        System.out.printf("You choose: %s %n", artistSong);
                        break;
                    case "play":
                        //playNext();
                        break;
                    default:
                        System.out.printf("Uknown Choice: '%s'%n%n", choice);
                }
            } catch(IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }

        } while(!choice.equals("quit"));
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void addTeam(Team team) {
        this.teams.add(team);
    }

    private Player promptNewPlayer() throws IOException{
        System.out.print("Players First Name:  ");
        String firstName = this.reader.readLine();
        System.out.print("Players Last Name:  ");
        String lastName = this.reader.readLine();
        System.out.print("Players Height(inches):  ");
        int height = Integer.parseInt(this.reader.readLine());
        System.out.print("Has the player played before? (Y/N):  ");
        boolean experiance;
        if(this.reader.readLine() == "Y"){
            experiance = true;
        } else {
            experiance = false;
        }
        return new Player(firstName, lastName, height, experiance);
    }

    private Team promptNewTeam() throws IOException{
        System.out.print("Team Name:  ");
        String teamName = this.reader.readLine();
        System.out.print("Coach Name:  ");
        String coachName = this.reader.readLine();
        return new Team(teamName, coachName);
    }

    private int promptForIndex(List<String> options) throws IOException{
        int counter = 1;
        for (String option : options){
            System.out.printf("%d.)  %s %n", counter, option);
            counter++;
        }
        System.out.print("Your choice:   ");
        String optionAsString = this.reader.readLine();
        int choice = Integer.parseInt(optionAsString.trim());
        return (choice - 1);
    }


    private int promptTeam() throws IOException{
        System.out.printf("%n%nAvailable Teams:%n");
        List<String> localTeams = new ArrayList<>();
        if (this.teams.size() > 0){
            for(Team team : this.teams){
                localTeams.add(team.teamName);
            }
            int index = promptForIndex(localTeams);
            return index;
        } else {
            System.out.printf("There are no Teams%n");
            return -1;
        }
    }

    private int promptPlayers() throws IOException{
        System.out.printf("%n%nAvailable Players:%n");
        List<String> localPlayer = new ArrayList<>();
        if (this.players.size() > 0){
            for(Player player : this.players){
                localPlayer.add(player.getFirstName());
            }
            int index = promptForIndex(localPlayer);
            return index;
        } else {
            System.out.printf("There are no Players%n");
            return -1;
        }
    }

    public void addPlayersToTeam(Team team) throws IOException{
        if(team == null) {
            System.out.println("Sorry there are teams. Use create from the menu to add some");
        } else {
            ArrayList<Player> players;
            if (team.getPlayers() == null){
                players = new ArrayList<>();
            } else {
                players = team.getPlayers();
            }
            while (players.size() < 12) {
                Player player = this.players.get(promptPlayers());
                this.players.add(player);
                System.out.printf("Added %s to %s's Team", player.getFirstName(), team.teamName);
            }
            System.out.printf("%s's Team is full with %d players", team.coach, this.players.size());

        }
    }




}
