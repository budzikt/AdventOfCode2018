package days.six;

import java.util.HashMap;

public class MapPoint {
    HashMap<Integer, Integer> distanceFromPoints;
    boolean isMainCoordinate;
    boolean equalToMultiple;

    public static MapPoint getPointAsMainCoordinate() {
        MapPoint mp = new MapPoint();
        mp.distanceFromPoints = new HashMap<>();
        mp.isMainCoordinate = true;
        mp.equalToMultiple = false;
        return  mp;
    }

    public static MapPoint getPointAsEmptyArea(){
        MapPoint mp = new MapPoint();
        mp.distanceFromPoints = new HashMap<>();
        mp.isMainCoordinate = false;
        mp.equalToMultiple = false;
        return  mp;
    }
}
