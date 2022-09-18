package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.players.Player;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerRepository implements Repository <Player> {
    private Map<String, Player> playerRepository;

    public PlayerRepository() {
        this.playerRepository = new LinkedHashMap<>();
    }

    @Override
    public Collection<Player> getModels() {
        return playerRepository.values();
    }

    @Override
    public void add(Player model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_REPOSITORY);
        }
        playerRepository.put(model.getUsername(),model);
    }

    @Override
    public boolean remove(Player model) {
        Player toBeRemoved = playerRepository.remove(model.getUsername());
        return toBeRemoved != null;
    }

    @Override
    public Player findByName(String name) {
        return playerRepository.get(name);
    }
}
