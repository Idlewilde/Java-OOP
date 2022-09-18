package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import fairyShop.repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{

    private Repository <Helper> helperRepository;
    private Repository <Present> presentRepository;
    private Shop shop;
    private int brokenInstruments;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.shop=new ShopImpl();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        if(type.equals("Happy")){helper=new Happy(helperName);
        }
        else if(type.equals("Sleepy")){helper=new Sleepy(helperName);
        }
        else{throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);}

        helperRepository.add(helper);

        return String.format(ConstantMessages.ADDED_HELPER,type,helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        if(helperRepository.findByName(helperName)==null){throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);}
        Instrument instrument=new InstrumentImpl(power);
        helperRepository.findByName(helperName).addInstrument(instrument);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER,power,helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        this.presentRepository.add(new PresentImpl(presentName,energyRequired));
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT,presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> helpersReady = new ArrayList<>();
        helpersReady=helperRepository.getModels().stream()
                .filter(e->e.getEnergy()>50).collect(Collectors.toList());

        if(helpersReady.isEmpty()){throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);}

        List <Instrument> instrumentList=new ArrayList<>();

        for (Helper helper : helpersReady) {
            instrumentList.addAll(helper.getInstruments().stream()
                    .filter(e->!e.isBroken()).collect(Collectors.toList()));
        }

        int instrumentsWorking = instrumentList.size();

        for (Helper helper : helpersReady) {
            if(!presentRepository.findByName(presentName).isDone()){
           shop.craft(presentRepository.findByName(presentName),helper );}
            else{break;}
        }

        String output ="";
        if(presentRepository.findByName(presentName).isDone()){output = "done";}
        else{output="not done";}

        List <Instrument> instrumentListAfter=new ArrayList<>();
        for (Helper helper : helpersReady) {
            instrumentListAfter.addAll(helper.getInstruments().stream()
                    .filter(e->!e.isBroken()).collect(Collectors.toList()));;
        }

        this.brokenInstruments = this.brokenInstruments+(instrumentsWorking-instrumentListAfter.size());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.PRESENT_DONE,presentName,output));
        sb.append(String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS,this.brokenInstruments));
        
        return sb.toString().trim();
    }

    @Override
    public String report() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d presents are done!",presentRepository.getModels().stream().filter(Present::isDone).count())).append(System.lineSeparator());
        sb.append("Helpers info:").append(System.lineSeparator());
        for (Helper helper : helperRepository.getModels()) {
            sb.append(String.format("Name: %s",helper.getName())).append(System.lineSeparator());
            sb.append(String.format("Energy: %d",helper.getEnergy())).append(System.lineSeparator());
            sb.append(String.format("Instruments: %d not broken left",helper.getInstruments().stream().filter(e->!e.isBroken()).count())).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
