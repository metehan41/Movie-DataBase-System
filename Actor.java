import java.util.ArrayList;
import java.util.List;

public class Actor extends Performer {
    private String height;

    public static List<Actor> actorList = new ArrayList<>();

    public Actor(String name, String surname, String country, String id, String height) {
        super(name, surname, country, id);
        this.setHeight(height);
        Actor.actorList.add(this);
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}