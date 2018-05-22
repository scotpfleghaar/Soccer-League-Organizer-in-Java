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
    private Set<String> coaches;
    private Set<String> teamNames;

    public LeagueHelper(ArrayList<Player> players) {
        this.players = players;
        this.teams = new ArrayList<Team>();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.menu = new HashMap<String, String>();
        this.coaches = new HashSet();
        this.teamNames = new HashSet();
        this.menu.put("create", "Create Team");
        this.menu.put("add", "Add Player to the Queue");
        this.menu.put("build", "Add Players to a Team");
        this.menu.put("remove", "Remove Player from Team");
        this.menu.put("reports", "View Reports");
        this.menu.put("league", "League Balance Report");
        this.menu.put("print", "Print Team Roster");
        this.menu.put("quit", "Quit the LeagueBuilder");
    }

    private String promptAction() throws IOException {
        System.out.printf("%n%nThere are currently %d registered players not assigned to a team%n", players.size());
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
                        if(teams.size() > players.size()) {
                            System.out.printf("Cannot Create more teams than there are players.%n");
                        } else {
                            Team team = promptNewTeam();
                            addTeam(team);
                            System.out.printf("Team Created! %s is coaching the %s%n", team.getCoach(), team.getTeamName());
                            System.out.printf("There are %d teams%n%n", teams.size());
                        }

                        break;
                    case "add":
                        Player player = promptNewPlayer();
                        addPlayer(player);
                        System.out.printf("%s added! %n%n", player.getFirstName());
                        break;
                    case "build":
                        if(teams.size() == 0){
                            System.out.printf("There are not teams, Please create a team%n");
                        } else {
                            int teamIndex =  promptTeam();
                            addPlayersToTeam(teams.get(teamIndex));
                        }

                        break;
                    case "remove":
                        if(teams.size() == 0){
                            System.out.printf("There are not teams, Please create a team%n");
                        } else {
                            int teamIndex = promptTeam();
                            if(teams.get(teamIndex).players == null){
                                System.out.printf("There are no players in this team, please build the team");
                            } else {
                                removePlayersFromTeam(teams.get(teamIndex));
                            }
                        }
                        break;
                    case "reports":
                        if(teams.size() == 0){
                            System.out.printf("There are not teams, Please create a team%n");
                        } else {
                            int teamIndex = promptTeam();
                            if(teams.get(teamIndex).players == null){
                                System.out.printf("There are no players in this team, please build the team");
                            } else {
                                sortPlayerByHeight(teams.get(teamIndex));
                            }
                        }
                        break;
                    case "league":
                        System.out.println("leagueBalance....");

                        if(teams.size() == 0){
                            System.out.printf("There are not teams, Please create a team%n");
                        } else {
                            leagueBalanceReport(this.teams);
                        }



                        break;
                    case "print":
                        if(teams.size() == 0){
                            System.out.printf("There are not teams, Please create a team%n");
                        } else {
                            int teamIndex = promptTeam();
                            if(teams.get(teamIndex).players == null){
                                System.out.printf("There are no players in this team, please build the team");
                            } else {
                                showPlayers(teams.get(teamIndex));
                            }
                        }
                        break;
                    case "quit":
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
        Set<String> playerFirstName = new HashSet<String>();
        Set<String> playersLastNames = new HashSet<String>();
        for(Player player : players){
            playerFirstName.add(player.getFirstName());
            playersLastNames.add(player.getLastName());
        }
        String firstName;
        String lastName;
        while(true) {
            System.out.print("Players First Name:  ");
            firstName = this.reader.readLine();
            System.out.print("Players Last Name:  ");
            lastName = this.reader.readLine();

            if (playerFirstName.contains(firstName) && playersLastNames.contains(lastName)) {
                System.out.printf("%s %s is already registered. Please create a new player%n", firstName, lastName);
            } else {
                break;
            }
        }


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

        String teamName;
        String coachName;
        while(true) {
            System.out.print("Team Name:  ");
            teamName = this.reader.readLine();
            if (this.teamNames.contains(teamName)) {
                System.out.printf("The %s is already a team. Please use a different name%n", teamName);
            } else {
                this.teamNames.add(teamName);
                break;
            }
        }
        while(true) {
            System.out.print("Coach Name:  ");
            coachName = this.reader.readLine();
            if (this.coaches.contains(coachName)) {
                System.out.printf("%s is already coaching a team. Please use a different coach%n", coachName );
            } else {
                this.coaches.add(coachName);
                break;
            }
        }
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
        sortTeams(this.teams);
        if (this.teams.size() > 0){
            for(Team team : this.teams){
                localTeams.add(team.teamName + " coached by " + team.coach);
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
            sortPlayers(this.players);
            for(Player player : this.players){
                String experience;
                if (player.isPreviousExperience()){
                    experience = "is experienced.";
                } else {
                    experience = "is not experienced.";
                }
                localPlayer.add(player.getFirstName() + " " + player.getLastName() + ", Height: " + player.getHeightInInches() + " and " + experience);
            }
            int index = promptForIndex(localPlayer);
            return index;
        } else {
            System.out.printf("There are no Players%n");
            return -1;
        }
    }

    public void sortPlayers(ArrayList players) {
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                Collections.sort(players, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        Player p1 = (Player) o1;
                        Player p2 = (Player) o2;
                        int res = p1.getLastName().compareToIgnoreCase(p2.getLastName());
                        if (res != 0)
                            return res;
                        return p1.getFirstName().compareToIgnoreCase(p2.getFirstName());
                    }
                });
            }

        }
    }

    public void sortPlayersByHeight(ArrayList players) {
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                Collections.sort(players, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        Player p1 = (Player) o1;
                        Player p2 = (Player) o2;
                        int res = p1.getHeightInInches() > p2.getHeightInInches() ? +1 : p1.getHeightInInches() < p2.getHeightInInches() ? -1 : 0;
                        if (res != 0)
                            return res;
                        return p1.getLastName().compareToIgnoreCase(p2.getLastName());
                    }
                });
            }

        }
    }

    public void sortTeams(ArrayList teams) {
        for (int i = 0; i < teams.size(); i++) {
            for (int j = 0; j < teams.size(); j++) {
                Collections.sort(teams, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        Team p1 = (Team) o1;
                        Team p2 = (Team) o2;
                        int res = p1.teamName.compareToIgnoreCase(p2.teamName);
                        if (res != 0)
                            return res;
                        return p1.coach.compareToIgnoreCase(p2.coach);
                    }
                });
            }

        }
    }

    public void addPlayersToTeam(Team team) throws IOException{
        if(team == null) {
            System.out.println("Sorry there are no teams. Use create from the menu to add some");
        } else {
            ArrayList<Player> players;
            if (team.players == null){
                players = new ArrayList<>();
            } else {
                players = team.players;
            }
            while (players.size() < 11) {
                Player player = this.players.get(promptPlayers());
                players.add(player);
                this.players.remove(player);
                System.out.printf("Added %s to the %s%n", player.getFirstName(), team.teamName);
                System.out.printf("There are now %s players in the %s%n", players.size(), team.teamName);

            }
            team.setPlayers(players);
            System.out.printf("%s's Team is full with %d players%n", team.coach, players.size());
        }
    }

    public int promptTeamPlayers(Team team) throws IOException{
        System.out.printf("%n%nAvailable Players:%n");
        List<String> localPlayer = new ArrayList<>();
        if (team.players.size() > 0){
            sortPlayers(team.players);
            for(Player player : team.players){
                String experience;
                if (player.isPreviousExperience()){
                    experience = "is experienced.";
                } else {
                    experience = "is not experienced.";
                }
                localPlayer.add(player.getFirstName() + " " + player.getLastName() + ", Height: " + player.getHeightInInches() + " and " + experience);
            }
            int index = promptForIndex(localPlayer);
            return index;
        } else {
            System.out.printf("There are no Players%n");
            return -1;
        }
    }

    public void removePlayersFromTeam(Team team) throws IOException{
        if(team == null) {
            System.out.println("Sorry there are no teams. Use create from the menu to add some");
        } else {
            ArrayList<Player> players;
            if (team.players == null){
                System.out.printf("There are not players on this team");
            } else {
                players = team.players;
                boolean keepRemovingPlayers = true;
                do {
                    Player player = players.get(promptTeamPlayers(team));
                    players.remove(player);
                    this.players.add(player);
                    System.out.printf("Removed %s From the %s%n", player.getFirstName(), team.teamName);
                    System.out.printf("There are now %s players in the %s%n", players.size(), team.teamName);
                    System.out.print("Would you like to remove another player? (Y/N)  ");
                    String removePlayer = this.reader.readLine();
                    if(removePlayer == "Y"){
                        keepRemovingPlayers = true;
                    } else {
                        keepRemovingPlayers = false;
                    }
                } while (keepRemovingPlayers);
                team.setPlayers(players);
                System.out.printf("%s's Team is full with %d players%n", team.coach, players.size());
            }

        }
    }

    public void showPlayers(Team team) throws IOException{
        boolean returnToMenu = false;
        do {
            System.out.printf("%nPlayers on %s coached by %s%n", team.teamName, team.coach);
            if (team.players.size() > 0){
                sortPlayers(team.players);
                for(Player player : team.players){
                    String experience;
                    if (player.isPreviousExperience()){
                        experience = "is experienced.";
                    } else {
                        experience = "is not experienced.";
                    }
                    System.out.printf("%s %s, is %d inches tall and %s%n", player.getFirstName(), player.getLastName(), player.getHeightInInches(), experience);
                }
            } else {
                System.out.printf("There are no Players%n");
            }
            System.out.printf("%nThere are %s players in team %s%n", team.players.size(), team.teamName);
            System.out.print("Press Enter to return to Menu.");
            this.reader.readLine();
            returnToMenu = false;
        } while (returnToMenu);
    }

    public void sortPlayerByHeight(Team team) throws IOException{
        boolean returnToMenu = false;
        do {
            System.out.printf("%nPlayer Height report for players on %s coached by %s%n", team.teamName, team.coach);
            if (team.players.size() > 0){
                Map<Integer,List<Player>> map = new HashMap<Integer,List<Player>>();

                for (Player player : team.players) {
                    int cid = player.getHeightInInches();

                    List<Player> list = map.get(cid);

                    if (list == null) {
                        list = new ArrayList<Player>();
                        map.put(cid,list);
                    }

                    list.add(player);
                }

                int maxHeight = Integer.MAX_VALUE;
                int minHeight = 0;
                for (Map.Entry<Integer, List<Player>> entry : map.entrySet()) {
                    if(maxHeight > entry.getKey()){
                        maxHeight = entry.getKey();
                    }
                    if(minHeight < entry.getKey()){
                        minHeight = entry.getKey();
                    }
                }

                System.out.printf("There are %s players with a heights of %d-%d inches.%n", team.players.size(), maxHeight, minHeight);
                for (Player player : players)
                {
                    System.out.printf("%s %s%n", player.getFirstName(), player.getLastName());
                }
            } else {
                System.out.printf("There are no Players%n");
            }
            System.out.printf("%nThere are %s players in team %s%n", team.players.size(), team.teamName);
            System.out.print("Press Enter to return to Menu.");
            this.reader.readLine();
            returnToMenu = false;
        } while (returnToMenu);
    }

    public float teamBalancePercent(Team team){
        double experiencedPlayers = 0;
        double noneExperiencedPlayers = 0;
        double total = team.players.size();
        for(Player player : team.players){
            if (player.isPreviousExperience()){
                experiencedPlayers += 1;
            } else {
                noneExperiencedPlayers += 1;
            }
        }
        float percentExperianced = (float) ((experiencedPlayers / total) * 100);
        return percentExperianced;
    }

    public void leagueBalanceReport(ArrayList<Team> teams) throws IOException{
        System.out.printf("%n%nThis is the League Balance Report%n");
        boolean returnToMenu = false;
        do {
            for(Team team : teams){
                if(team.players == null){
                    System.out.printf("There are no players in the %s%n", team.teamName);
                } else {
                    System.out.printf("Of the %d players in the %s coached by %s, %.2f%% are experienced%n", team.players.size(), team.teamName, team.coach, teamBalancePercent(team));
                }
            }
            System.out.print("Press Enter to return to Menu.");
            this.reader.readLine();
            returnToMenu = false;
        } while (returnToMenu);
    }
}
