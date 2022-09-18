package fairyShop.models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class ShopImpl implements Shop{
    @Override
    public void craft(Present present, Helper helper) {

        List <Instrument> instruments = new ArrayList<>(helper.getInstruments());
        boolean toBreak=false;

        while(!present.isDone()||!helper.canWork()){
            for (Instrument instrument : instruments) {
                if(!instrument.isBroken()){
                    while(!instrument.isBroken()){
                    present.getCrafted();instrument.use();helper.work();
                if(present.isDone()||!helper.canWork()){toBreak=true;break;}}}
                if(toBreak){break;}
            }
                if(toBreak){break;}
                if(instruments.stream().mapToInt(e->e.getPower()).sum()==0){break;}

    }
}}