package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{
    public Biologist(String name) {
        super(name, 70);
    }

    @Override
    public void breath() {
        if(getOxygen()>5){setOxygen(getOxygen()-5);}
        else{setOxygen(0);}
    }
}
