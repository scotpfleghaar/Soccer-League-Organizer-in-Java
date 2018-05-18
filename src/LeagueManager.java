import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;

import java.util.TreeMap;

public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        System.out.printf("There are currently %d registered players.%n", players.length);
        // Your code here!
        System.out.println(players);

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
        public void displayTeams(Players[] members){
            TreeMap sortPlayers = new TreeMap();
        }

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
