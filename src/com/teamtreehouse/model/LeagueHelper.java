package com.teamtreehouse.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class LeagueHelper {
    public ArrayList<Player> players;
    private Map<String, String> menu;
    private BufferedReader reader;

    public LeagueHelper(ArrayList<Player> players) {
        this.players = players;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.menu = new HashMap<String, String>();
        this.menu.put("create", "Create Team");
        this.menu.put("add", "Add Player to Team");
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
                        System.out.println("Create a Team...");
                        break;
                    case "add":
                        Player player = promptNewSong();
                        addPlayer(player);
                        System.out.printf("%s added! %n%n", player.getFirstName());
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

    private Player promptNewSong() throws IOException{
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


}
