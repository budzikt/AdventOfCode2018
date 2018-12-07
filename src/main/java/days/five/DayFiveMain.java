package days.five;

import java.nio.file.FileSystemNotFoundException;
import java.util.Collections;
import java.util.HashMap;

public class DayFiveMain {
    public static void main(String[] args){
        String polymer  = PolymerSource.polymer;//"dabAcCaCBAcCcaDA";
        StringBuilder p = new StringBuilder(polymer);
        boolean fullyReact = false;
        while(!fullyReact){
            boolean deleted = false;
            for(int i=0; i<p.length()-1; i++){
                char ch1 = p.charAt(i);
                char ch2 = p.charAt(i+1);
                if(Math.abs(ch1 - ch2) == 32){
                    p.deleteCharAt(i);
                    p.deleteCharAt(i);
                    deleted = true;
                    break;
                }
            }
            if(!deleted){
                fullyReact = true;
            }
        }
        System.out.println(p.toString().length());

        //polymer  = PolymerSource.polymer;//"dabAcCaCBAcCcaDA";
        HashMap<Character, Integer> resultMap = new HashMap<>();
        for(char i = 'a'; i < 'z'; i++){
            polymer  = PolymerSource.polymer;
            polymer = polymer.replace(Character.toString(i), "");
            char capital = (char)((char)i - (char)32);
            polymer = polymer.replace("" + capital, "");
            StringBuilder p2 = new StringBuilder(polymer);

            boolean fullyReact2 = false;
            while(!fullyReact2){
                boolean deleted = false;
                for(int idx=0; idx<p2.length()-1; idx++){
                    char ch1 = p2.charAt(idx);
                    char ch2 = p2.charAt(idx+1);
                    if(Math.abs(ch1 - ch2) == 32){
                        p2.deleteCharAt(idx);
                        p2.deleteCharAt(idx);
                        deleted = true;
                        break;
                    }
                }
                if(!deleted){
                    fullyReact2 = true;
                }
            }
            resultMap.put(i, p2.toString().length());
        }

        int minFind = PolymerSource.polymer.length();
        Character markedChar = 'a';
        for(Character ch: resultMap.keySet()) {
            if(resultMap.get(ch) < minFind){
                minFind = resultMap.get(ch);
                markedChar = ch;
            }
        }
        System.out.println(markedChar + " " + minFind + " " + "Ayyyy");

    }
}

