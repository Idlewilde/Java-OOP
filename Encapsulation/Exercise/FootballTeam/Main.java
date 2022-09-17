import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String,Team> teams = new LinkedHashMap<>();

        while(!input.equals("END")){
            String[] commandParts = input.split(";");
            String command = commandParts[0];
            String teamName = commandParts[1];

            try{
            if(command.startsWith("Team")){
                Team team = new Team(teamName);
                teams.putIfAbsent(teamName,team);}

            else if(command.startsWith("Add")){
                String playerName = commandParts[2];
                int endurance = Integer.parseInt(commandParts[3]);
                int sprint = Integer.parseInt(commandParts[4]);
                int dribble = Integer.parseInt(commandParts[5]);
                int passing = Integer.parseInt(commandParts[6]);
                int shooting = Integer.parseInt(commandParts[7]);

                if (!teams.containsKey(teamName)) {
                    System.out.printf("Team %s does not exist.%n", teamName);

                } else {
                    Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                    teams.get(teamName).addPlayer(player);
                }}

            else if(command.startsWith("Remove")){
                String playerToRemove = input.split(";")[2];
                teams.get(teamName).removePlayer(playerToRemove);}

            else if(command.startsWith("Rating")){
                if (!teams.containsKey(teamName)) {
                    System.out.printf("Team %s does not exist.%n", teamName);

                } else {
                    System.out.printf("%s - %d%n", teamName, Math.round(teams.get(teamName).getRating()));
                }

            }} catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }


            input = scanner.nextLine();
        }
    }

}


