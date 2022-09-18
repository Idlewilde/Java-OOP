package CounterStriker.models.players;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.guns.Gun;

public abstract class PlayerImpl implements Player {
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    public PlayerImpl(String username, int health, int armor, Gun gun) {
        setUsername(username);
        setHealth(health);
        setArmor(armor);
        setGun(gun);
        isAlive=true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    protected void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    @Override
    public boolean isAlive() {
        return isAlive;

    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public Gun getGun() {
        return gun;
    }

    public void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public void takeDamage(int points) {
        if (this.armor >= points) {
            this.armor = this.armor - points;
        } else {
            int pointsLeft=points-this.armor;
            this.armor=0;
            this.health = this.health - pointsLeft;
            if (this.health <= 0) {
                this.health=0;
                setAlive(false);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s: %s",getClass().getSimpleName(),this.username)).append(System.lineSeparator())
          .append(String.format("--Health: %d",this.health)).append(System.lineSeparator())
          .append(String.format("--Armor: %d",this.armor)).append(System.lineSeparator())
          .append(String.format("--Gun: %s",this.gun.getName())).append(System.lineSeparator());
        return sb.toString().trim();
    }
}


