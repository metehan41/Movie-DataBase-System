import java.util.ArrayList;
import java.util.List;

public class Artist extends Person {

    public static List<Artist> artistList = new ArrayList<>();

    public Artist(String name, String surname, String country, String id) {
        super(name, surname, country, id);
        artistList.add(this);
    }
}