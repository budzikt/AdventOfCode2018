package days.six;

public class Coordinates {
    Integer x;
    Integer y;
    Integer xArray;
    Integer yArray;
    Integer id;

    public Coordinates(Integer x, Integer y, Integer id){
        this.id = id;
        this.x = x;
        this.y = y;
        this.xArray = this.x - 1;
        this.yArray = this.y - 1;
    }

}
