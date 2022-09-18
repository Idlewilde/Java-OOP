package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;
import CounterStriker.repositories.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository <Gun> guns;
    private Repository<Player> players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name, bulletsCount);
                break;
            case "Rifle":
                gun = new Rifle(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);
        }
        this.guns.add(gun);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Player player;
        Gun gun = this.guns.findByName(gunName);
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }
        switch (type) {
            case "Terrorist":
                player = new Terrorist(username, health, armor, gun);
                break;
            case "CounterTerrorist":
                player = new CounterTerrorist(username, health, armor, gun);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        this.players.add(player);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        Collection <Player> start = this.players.getModels();
        Collection <Player> playersToPlay = players.getModels()
                .stream()
                .filter(Player::isAlive)
                .collect(Collectors.toList());
        return field.start(playersToPlay);
    }

    @Override
    public String report() {
        List<Player> counterTerrorists =
                players.getModels().stream()
                        .filter(e -> e.getClass().getSimpleName().equals("CounterTerrorist"))
                        .sorted(Comparator.comparingInt(Player::getHealth)
                                .reversed()
                                .thenComparing(Player::getUsername)).collect(Collectors.toList());

        List<Player> terrorists =
                players.getModels().stream()
                        .filter(e -> e.getClass().getSimpleName().equals("Terrorist"))
                        .sorted(Comparator.comparingInt(Player::getHealth)
                                .reversed()
                                .thenComparing(Player::getUsername)).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (Player counterTerrorist : counterTerrorists) {
            sb.append(counterTerrorist.toString()).append(System.lineSeparator());
        }
        for (Player terrorist : terrorists) {
            sb.append(terrorist.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
