package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldImpl implements Field {
    @Override
    public String start(Collection<Player> players) {
        List<Player> terrorists =
                players
                        .stream()
                        .filter(e -> e.getClass().getSimpleName().equals("Terrorist"))
                        .collect(Collectors.toList());
        List<Player> counterTerrorists =
                players
                        .stream()
                        .filter(e -> e.getClass().getSimpleName().equals("CounterTerrorist"))
                        .collect(Collectors.toList());

        while (terrorists.stream().mapToInt(Player::getHealth).sum()>0
                && counterTerrorists.stream().mapToInt(Player::getHealth).sum()>0) {
            List<Player> aliveTerrorists =
                    terrorists.stream()
                            .filter(Player::isAlive)
                            .collect(Collectors.toList());
            for (Player terrorist : aliveTerrorists) {
                List<Player> aliveCounterTerroristsUnderAttack =
                        counterTerrorists.stream()
                                .filter(Player::isAlive)
                                .collect(Collectors.toList());
                for (Player aliveCounterTerrorist : aliveCounterTerroristsUnderAttack) {
                    aliveCounterTerrorist.takeDamage(terrorist.getGun().fire());
                }
            }

            List<Player> aliveCounterTerrorists =
                    counterTerrorists.stream()
                            .filter(Player::isAlive)
                            .collect(Collectors.toList());
            for (Player counterTerrorist : aliveCounterTerrorists) {
                List<Player> aliveTerroristsUnderAttack =
                        terrorists.stream()
                                .filter(Player::isAlive)
                                .collect(Collectors.toList());
                for (Player aliveTerrorist : aliveTerroristsUnderAttack) {
                    aliveTerrorist.takeDamage(counterTerrorist.getGun().fire());
                }
            }


        }
        if (terrorists.stream().noneMatch(Player::isAlive)) {
            return OutputMessages.COUNTER_TERRORIST_WINS;
        } else {
            return OutputMessages.TERRORIST_WINS;
        }
    }

}

