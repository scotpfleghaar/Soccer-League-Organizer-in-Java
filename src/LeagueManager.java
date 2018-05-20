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
        System.out.printf("Thank you for using the League Manager!");
    }

}
