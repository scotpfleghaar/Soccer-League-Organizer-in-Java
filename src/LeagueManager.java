import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.LeagueHelper;

import java.util.Arrays;
import java.util.ArrayList;


public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        System.out.printf("There are currently %d registered players.%n", players.length);
        // Your code here
        LeagueHelper leagueHelper = new LeagueHelper(new ArrayList<>(Arrays.asList(players)));
        leagueHelper.run();


        for (Player player : players) {
            System.out.printf("%s %s is %d inches ", player.getFirstName(), player.getLastName(), player.getHeightInInches());
            if (player.isPreviousExperience()){
                System.out.printf("and has played before%n");
            } else {
                System.out.printf("hasn't played before%n");
            }
        }
        for (Player player : players) {
            player.compareTo(player);
            System.out.printf("%s %s is %d inches ", player.getFirstName(), player.getLastName(), player.getHeightInInches());
            if (player.isPreviousExperience()){
                System.out.printf("and has played before%n");
            } else {
                System.out.printf("hasn't played before%n");
            }
        }
        System.out.println("Hi");



        // While the Organizer has not selected Exit from the Menu
            //Create Team: Present an menu item that allows the organizer to create a new team, so that they can build the season.
            //Add Player to Team: Present an menu item that allows me to add players to a team, so that I can build fair teams.
            //Remove Player from Team: Present an menu item that allows me to remove players from a team, so that I can attempt to produce more fair teams.
            //View Reports: As an organizer planning teams, I should be able to view a Height report of a chosen team grouped by height, so that I can determine if teams are fair.
            //League Balance Report: As an organizer who is planning teams, I should be able to see a League Balance Report for all teams in the league showing a total count of experienced players vs. inexperienced players, so I can determine from a high level if the teams are fairly balanced regarding previous experience. The report should use a map like solution to properly report experienced vs. inexperienced for each team.

        // As an organizer adding or removing a player to a yet to be chosen team,
        // I should be prompted with an alphabetically ordered list of teams to choose from,
        // so that I can quickly locate the team and avoid typos.

        //Function displayTeams (teams) {
        //  Sort teams Alphabetically
        //  Display teams
        //}


        //Function selectTeam () {
        //  displayTeams();
        //  select team
        //}

        //Function displayPlayersByHeight (team) {
        //  Sort players by Height
        //  Display player
        //}

        //Function displayPlayersByName (team) {
        //  Sort players Alphabetically
        //  Display players
        //}

        //Function addPlayer (team, player) {
        //   displayPlayersByName(team)
        //   team.add(player);
        //}

        //Function removePlayer (team, player) {
        //   displayPlayersByName(team)
        //   team.remove(player);
        //}

        //Function printPlayer (team) {
        //  displayPlayersByName(team)
        //  Print players
        //}






    }

}
