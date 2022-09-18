package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.common.ExceptionMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Player mainPlayer;
    private Map<String, Player> civilPlayers;
    private ArrayDeque<Gun> guns;
    private Neighbourhood gangNeighbourhood;


    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.civilPlayers = new LinkedHashMap<>();
        this.guns = new ArrayDeque<>();
        this.gangNeighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player civilPlayer = new CivilPlayer(name);
        this.civilPlayers.put(name, civilPlayer);
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        if (!type.equals("Pistol") && !type.equals("Rifle")) {
            return ConstantMessages.GUN_TYPE_INVALID;
        } else {
            if (type.equals("Pistol")) {
                Gun pistol = new Pistol(name);
                guns.offer(pistol);
            }
            if (type.equals("Rifle")) {
                Gun rifle = new Rifle(name);
                guns.offer(rifle);
            }
            return String.format(ConstantMessages.GUN_ADDED, name, type);
        }

    }

    @Override
    public String addGunToPlayer(String name) {
        if (guns.isEmpty()) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }
        else if (!civilPlayers.containsKey(name)&&!name.equals("Vercetti")) {
            return String.format(ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST);
        }
        else{
            Gun toAdd = guns.poll();
            if (name.equals("Vercetti")) {
                mainPlayer.getGunRepository().add(toAdd);
                return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, toAdd.getName(),mainPlayer.getName());
            } else {
                civilPlayers.get(name).getGunRepository().add(toAdd);
                return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, toAdd.getName(), name);
            }
        }
    }

    @Override
    public String fight() {
        this.gangNeighbourhood.action(this.mainPlayer, this.civilPlayers.values());
        if (this.mainPlayer.getLifePoints() == 100 && this.civilPlayers
                .values()
                .stream()
                .allMatch(player -> player.getLifePoints() == 50)) {
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        } else {

            List<Player> deadCivils = civilPlayers.values().stream().filter(e->!e.isAlive()).collect(Collectors.toList());
            for (Player deadCivil : deadCivils) {
                this.civilPlayers.remove(deadCivil.getName());
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ConstantMessages.FIGHT_HAPPENED)
                    .append(System.lineSeparator());
            stringBuilder.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()))
                    .append(System.lineSeparator());
            stringBuilder.append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadCivils.size()))
                    .append(System.lineSeparator());
            stringBuilder.append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayers.size()));

            return stringBuilder.toString().trim();

        }
    }
}
