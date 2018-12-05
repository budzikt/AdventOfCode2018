package days.two.label;

import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;

public class Label {
    public String label;
    public HashMap<Character, Integer> countMap;

    public Label(String label) {
        this.label = label;
        countMap = new HashMap<>();
        processCount();
    }

    public void processCount(){
        label.chars().forEach(charIn -> {
            if(countMap.containsKey(fromInt(charIn))){
                countMap.put( fromInt(charIn), countMap.get(fromInt(charIn)) + 1);
            } else {
                countMap.put(fromInt(charIn), 1);
            }
        });
    }

    private Character fromInt(int intIn) {
        return new Character((char)intIn);
    }
}
