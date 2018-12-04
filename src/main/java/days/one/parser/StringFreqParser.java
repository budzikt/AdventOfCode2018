package days.one.parser;

import days.one.parser.IFreqParser;

import java.util.ArrayList;
import java.util.List;

public class StringFreqParser implements IFreqParser {

    @Override
    public ArrayList<Integer> parseInput(String input) {
        String[] splitted = input.split("\n");
        ArrayList<Integer> arrList = new ArrayList<Integer>();

        for(String s: splitted) {
            arrList.add(Integer.parseInt(s.replaceAll("\\s", "")));
        }
        return arrList;
    }
}
