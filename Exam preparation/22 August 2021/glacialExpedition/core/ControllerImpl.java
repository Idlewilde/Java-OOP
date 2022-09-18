package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    ExplorerRepository explorerRepository;
    StateRepository stateRepository;
    Mission mission;
    int exploredStates;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
        this.mission=new MissionImpl();
        exploredStates=0;
    }

    @Override
    public String addExplorer(String type, String explorerName) {

        switch (type) {
            case "AnimalExplorer":
                Explorer animalExplorer = new AnimalExplorer(explorerName);
                explorerRepository.add(animalExplorer);
                return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
            case "GlacierExplorer":
                Explorer glacierExplorer = new GlacierExplorer(explorerName);
                explorerRepository.add(glacierExplorer);
                return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
            case "NaturalExplorer":
                Explorer naturalExplorer = new NaturalExplorer(explorerName);
                explorerRepository.add(naturalExplorer);
                return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        for (String s : exhibits) {
            state.getExhibits().add(s);
        }
        stateRepository.add(state);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer toRemove = explorerRepository.byName(explorerName);
        if (toRemove == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        explorerRepository.remove(toRemove);
        return String.format(ConstantMessages.EXPLORER_RETIRED,explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        State state = stateRepository.byName(stateName);
        Collection <Explorer> toExplore = explorerRepository.getCollection().stream().filter(e->e.getEnergy()>50).collect(Collectors.toList());
        if(toExplore.isEmpty()){throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);}
        else{mission.explore(state,toExplore);
        }
        long retired = toExplore.stream().filter(e -> e.getEnergy() == 0).count();
        exploredStates++;
        return String.format(ConstantMessages.STATE_EXPLORER,stateName,retired);
    }

    @Override
    public String finalResult() {
        StringBuilder result = new StringBuilder();
        result.append(String.format(ConstantMessages.FINAL_STATE_EXPLORED,exploredStates))
                .append(System.lineSeparator())
                .append(ConstantMessages.FINAL_EXPLORER_INFO)
                .append(System.lineSeparator());
        for (Explorer explorer : explorerRepository.getCollection()) {
            result.append(String.format(ConstantMessages.FINAL_EXPLORER_NAME,explorer.getName()))
                    .append(System.lineSeparator())
                    .append(String.format(ConstantMessages.FINAL_EXPLORER_ENERGY,explorer.getEnergy()))
                    .append(System.lineSeparator());
                    if(explorer.getSuitcase().getExhibits().isEmpty()){result.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS,"None"));}
                    else{result.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS,
                            String.join(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, explorer.getSuitcase().getExhibits())));}

        result.append(System.lineSeparator());}
        return result.toString().trim();
    }
}
