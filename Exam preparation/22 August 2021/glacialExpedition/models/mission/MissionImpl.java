package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayDeque;
import java.util.Collection;

public class MissionImpl implements Mission{
    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        for (Explorer explorer : explorers) {
            while(!state.getExhibits().isEmpty()&&explorer.canSearch()){
                explorer.search();
                String current =state.getExhibits().stream().iterator().next();
                explorer.getSuitcase().getExhibits().add(current);
                state.getExhibits().remove(current);
            }
            if(state.getExhibits().isEmpty()){break;}
        }

    }
}
