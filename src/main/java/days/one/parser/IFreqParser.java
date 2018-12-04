package days.one.parser;

import java.util.ArrayList;
import java.util.List;

public interface IFreqParser<T extends String> {
    ArrayList<Integer> parseInput(T input);
}
