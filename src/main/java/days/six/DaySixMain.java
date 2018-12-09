package days.six;

import days.three.DayThreeMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaySixMain {
    public static void main(String[] args){
        ClassLoader cl = DayThreeMain.class.getClassLoader();
        URL pathURL = cl.getResource("daySix/real.txt");
        File ff = new File(pathURL.getPath());
        try{
            BufferedReader  br = new BufferedReader(new FileReader(ff));
            String line = "";
            ArrayList<Coordinates> coords = new ArrayList<>();
            Pattern coordPattern = Pattern.compile("^(\\d{1,}), (\\d{1,})$");
            int idx = 0;
            while((line= br.readLine()) != null) {
                Matcher m = coordPattern.matcher(line);
                m.matches();
                MatchResult mr = m.toMatchResult();
                String g1 = mr.group(1);
                String g2 = mr.group(1);
                Coordinates cord = new Coordinates(Integer.parseInt(mr.group(1)), Integer.parseInt(mr.group(2)), idx);
                coords.add(cord);
                idx++;
            }

            Comparator<Integer> comp = (i1, i2) -> {
                return i1 - i2;
            };

            Optional<Integer> maxXCoord = coords.stream()
                    .map(el -> el.x)
                    .max(comp);

            Optional<Integer> maxYCoord = coords.stream()
                    .map(el -> el.y)
                    .max(comp);

            if(maxXCoord.isPresent() && maxYCoord.isPresent()) {
                System.out.println("Initializing map with max Coordinates of X " + maxXCoord.get() + " and Y:" + maxYCoord.get());
                MapOfCoordinates moc = new MapOfCoordinates(maxXCoord.get(), maxYCoord.get());
                moc.processCoordinates(coords);
                moc.calculateDistances();
                moc.getMinimalDistances();
                HashMap<Integer, Integer> aeras1 =  moc.calculateAreas();
                moc.addMapFrame();
                moc.shiftMainCoordinates();
                moc.clearMap();
                //moc.processCoordinates();
                moc.calculateDistances();
                moc.getMinimalDistances();
                HashMap<Integer, Integer> aeras2 =  moc.calculateAreas();
                HashMap <Integer, Integer[]> resultSet = new HashMap<>();
                if(aeras1.size() == aeras2.size()){
                    aeras1.forEach((k,v) -> {
                        Integer afterExpandSize = aeras2.get(k);
                        Integer aeraDiff = Math.abs(afterExpandSize - v);
                        if(aeraDiff < 2){
                            Integer[] aeras = {v, afterExpandSize};
                            resultSet.put(k, aeras);
                        }
                    });


                    OptionalInt res = resultSet.entrySet().stream().map(e -> e.getValue()).map(v -> v[0]).mapToInt(v -> (int)v).max();
                    System.out.println(res.getAsInt());

                }else{
                    throw new Exception("Well fuck");
                }

                moc = new MapOfCoordinates(maxXCoord.get(), maxYCoord.get());
                moc.processCoordinates(coords);
                moc.calculateDistances();
                moc.calculateSafeAeras();

                System.out.println("K");
            } else {
                throw new Exception("No Max Coordinate found");
            }
        } catch (Exception e){
            e.printStackTrace();
        }



    }
}
