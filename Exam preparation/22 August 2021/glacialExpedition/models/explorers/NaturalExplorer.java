package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    public NaturalExplorer(String name) {
        super(name, 60);
    }

    @Override
    public void search(){
        if(getEnergy()>=7){setEnergy(getEnergy()-7);}
        else{setEnergy(0);}
    }
    }
