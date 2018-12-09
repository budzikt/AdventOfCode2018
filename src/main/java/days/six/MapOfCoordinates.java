package days.six;

import java.util.*;
import java.util.stream.Stream;

public class MapOfCoordinates {

    ArrayList<ArrayList<MapPoint>> coordinatesMap;
    HashMap<Integer, Integer[]> mainCoordsMap;
    ArrayList<ArrayList<Integer>> safeSetMap;

    private Integer MAX_X;
    private Integer MAX_Y;

    private final int X_REF = 0;
    private final int Y_REF = 1;

    private List<Coordinates> coordList;

    public MapOfCoordinates(Integer maxX, Integer maxY){
        this.MAX_X = maxX;
        this.MAX_Y = maxY;

        this.mainCoordsMap = new HashMap<>();


        this.coordinatesMap = new ArrayList<>(MAX_X);
        for(int i =0; i< MAX_X; i++){
            ArrayList<MapPoint> row = new ArrayList<>();
            for(int j = 0; j < MAX_Y; j++){
                row.add(null);
            }
            this.coordinatesMap.add(row);
        }
    }

    public void processCoordinates(List<Coordinates> coordList){
        this.coordList = new ArrayList<>(coordList);
        this.coordList.forEach(coord -> {
            this.addCoordToMap(coord);
        });
        System.out.println("");
    }

    public void processCoordinates(){
        this.coordList.forEach(coord -> {
            this.addCoordToMap(coord);
        });
    }

    private void addCoordToMap(Coordinates c){
        Integer[] ar = {c.xArray, c.yArray};
        mainCoordsMap.put(c.id, ar);
        coordinatesMap.get(c.xArray).set(c.yArray, MapPoint.getPointAsMainCoordinate());
    }

    public void calculateDistances() {
        this.mainCoordsMap.keySet().forEach(mainCoordinateKey -> {
            Integer[] currentMainCoordinate = mainCoordsMap.get(mainCoordinateKey);

            for(int row = 0; row < MAX_X; row++){
                for(int col = 0; col < MAX_Y; col++){
                    MapPoint mp = this.coordinatesMap.get(row).get(col);
                    if(mp == null){
                        //ArrayList<MapPoint> rowRef = (ArrayList<MapPoint>) ;
                        this.coordinatesMap.get(row).set(col, MapPoint.getPointAsEmptyArea());
                        mp = this.coordinatesMap.get(row).get(col);
                    }
                    if(!mp.isMainCoordinate){
                        Integer[] currentCoordinate = {row, col};
                        mp.distanceFromPoints.put(mainCoordinateKey, calcDistancesForPoint(currentMainCoordinate, currentCoordinate));
                    } else {
                        Integer[] currentCoordinate = {row, col};
                        mp.distanceFromPoints.put(mainCoordinateKey, calcDistancesForPoint(currentMainCoordinate, currentCoordinate));
                    }
                }
            }
        });
    }

    public Integer calcDistancesForPoint(Integer[] cord1, Integer[] cord2){
        return Math.abs(cord1[X_REF] - cord2[X_REF]) + Math.abs(cord1[Y_REF] - cord2[Y_REF]);
    }

    public void getMinimalDistances() {
        for(int row = 0; row < MAX_X; row++){
            for(int col = 0; col < MAX_Y; col++){
                MapPoint mp = this.coordinatesMap.get(row).get(col);
                Optional<Integer> MinDIst = mp.distanceFromPoints.entrySet().stream()
                        .map(el -> el.getValue())
                        .min((o1,o2) -> o1 - o2 );
                mp.distanceFromPoints.entrySet().removeIf(entry -> entry.getValue() > MinDIst.get());
                if(mp.distanceFromPoints.entrySet().size() > 1){
                    mp.equalToMultiple = true;
                }
            }
        }
    }

    public HashMap<Integer, Integer> calculateAreas() {
        HashMap<Integer, Integer> aeras = new HashMap<>();

        this.mainCoordsMap.keySet().forEach(mainCoordinateKey -> {
            for(int row = 0; row < MAX_X; row++){
                for(int col = 0; col < MAX_Y; col++){
                    if(coordinatesMap.get(row).get(col).equalToMultiple == false &&
                            coordinatesMap.get(row).get(col).distanceFromPoints.keySet().contains(mainCoordinateKey)) {
                        if(aeras.containsKey(mainCoordinateKey)){
                            aeras.compute(mainCoordinateKey, (v, drop) -> drop+1);
                        }else{
                            aeras.put(mainCoordinateKey, 1);
                        }
                    }
                }
            }
        });
        return aeras;
    }

    public void addMapFrame() {
        for(int i =0; i< MAX_X; i++){
            //ArrayList<MapPoint> rowRef = this.coordinatesMap.get(i);
            this.coordinatesMap.get(i).add(0, null);
            this.coordinatesMap.get(i).add(null);
        }
        int newRowLength = this.coordinatesMap.get(0).size();
        this.coordinatesMap.add(0, new ArrayList<>(Collections.nCopies(newRowLength, null)));
        this.coordinatesMap.add( new ArrayList(Collections.nCopies(newRowLength, null)));
        this.MAX_Y = newRowLength;
        this.MAX_X = this.coordinatesMap.size();
    }

    public void shiftMainCoordinates() {
        this.mainCoordsMap.forEach((k, v) -> {
            v[X_REF]++;
            v[Y_REF]++;
        });
    }

    public void clearMap() {
        this.coordinatesMap.forEach(row -> {
            row.replaceAll((v) -> null);
        });
    }

    public void calculateSafeAeras() {
        safeSetMap = new ArrayList<>();

        for(int i =0; i< MAX_X; i++){
            ArrayList<Integer> row = new ArrayList<>();
            for(int j = 0; j < MAX_Y; j++){
                row.add(null);
            }
            this.safeSetMap.add(row);
        }

        for (int row = 0; row < MAX_X; row++) {
            for (int col = 0; col < MAX_Y; col++) {
                int sumOfDistances = coordinatesMap.get(row).get(col).distanceFromPoints.entrySet().stream().mapToInt(v -> v.getValue()).sum();
                safeSetMap.get(row).set(col, Integer.valueOf(sumOfDistances));
            }
        }

        int sumOfSafety = 0;
        for (int row = 0; row < MAX_X; row++) {
            for (int col = 0; col < MAX_Y; col++) {
                if(safeSetMap.get(row).get(col) < 10000){
                    sumOfSafety++;
                }
            }
        }

        System.out.println(sumOfSafety);

    }
}
