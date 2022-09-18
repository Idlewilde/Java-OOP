package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood{
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        ArrayDeque <Gun> tommyGuns = new ArrayDeque<> (mainPlayer.getGunRepository().getModels());
        ArrayDeque <Player> civils = new ArrayDeque<>(civilPlayers);
        Gun currentGun = tommyGuns.poll();
        Player currentCivil=civils.poll();

        while (currentCivil != null && currentGun != null) {
            while (currentGun.canFire() && currentCivil.isAlive()) {

                int impact = currentGun.fire();
                currentCivil.takeLifePoints(impact);
            }

            if (currentGun.canFire()) {
                currentCivil = civils.poll();

            } else if (currentCivil.isAlive()) {
                currentGun = tommyGuns.poll();
            }
        }
        for (Player civil : civilPlayers) {
            if(civil.isAlive()){
            ArrayDeque <Gun> civilGuns = new ArrayDeque<> (civil.getGunRepository().getModels());
            Gun currentCivilGun=civilGuns.poll();
            while(currentCivilGun!=null){
                while(currentCivilGun.canFire()&& mainPlayer.isAlive()){
                    int impact = currentCivilGun.fire();
            mainPlayer.takeLifePoints(impact);}
                if(!mainPlayer.isAlive()){break;}
                currentCivilGun=civilGuns.poll();}

        }
    }
}}
