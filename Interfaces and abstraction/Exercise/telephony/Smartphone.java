package telephony;

import java.util.ArrayList;
import java.util.List;

public class Smartphone implements Callable,Browsable{
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = new ArrayList<>();
        this.urls = new ArrayList<>();
        this.numbers=numbers;
        this.urls=urls;
    }

    @Override
    public String browse() {
        StringBuilder browsing = new StringBuilder();
        for (String site : urls) {
            if(!site.contains("0")&&
                    !site.contains("1")&&
                    !site.contains("2")&&
                    !site.contains("3")&&
                    !site.contains("4")&&
                    !site.contains("5")&&
                    !site.contains("6")&&
                    !site.contains("7")&&
                    !site.contains("8")&&
                    !site.contains("9")){
            browsing.append("Browsing: ").append(site).append("!").append(System.lineSeparator());
        }
            else{browsing.append("Invalid URL!").append(System.lineSeparator());}}
        return browsing.toString();
    }

    @Override
    public String call() {
        StringBuilder calling = new StringBuilder();
        for (String number : numbers) {
            if(number.matches("^[0-9]+$")){
            calling.append("Calling... ").append(number).append(System.lineSeparator());}
            else{calling.append("Invalid number!").append(System.lineSeparator());}
        }
        return calling.toString();
    }
}
