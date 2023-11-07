import java.util.ArrayList;
import java.util.List;

public class Performer extends Artist {

    public static List<Performer> performerList = new ArrayList<>();

    public Performer(String name, String surname, String country, String id) {
        super(name, surname, country, id);
        performerList.add(this);
    }
}
