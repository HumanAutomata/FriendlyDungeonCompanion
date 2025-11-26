package app.logic;

import java.util.ArrayList;
import java.util.List;

public class POI {
    public String name;
    public String title;
    public String description;
    public String imagePath;

    public int x;
    public int y;

    public List<POI> children = new ArrayList<>();

    public POI() {}

    public POI(String t, String d, String img, int x, int y) {
        this.title = t;
        this.description = d;
        this.imagePath = img;
        this.x = x;
        this.y = y;
    }
}
