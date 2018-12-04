package days.one.doubleDetector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DoubleDetector {
    Integer duplicatedVal;
    public boolean isInSet(Set<Integer> entireSet, Integer newOne, boolean addToSet) {
        if(entireSet.contains(newOne)) {
            this.duplicatedVal = newOne;
            return true;
        } else {
            if(addToSet) {
                entireSet.add(newOne);
            }
            return false;
        }
    }

    public Integer getDoubled() {
        return duplicatedVal;
    }
}
