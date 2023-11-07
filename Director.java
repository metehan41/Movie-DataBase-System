import java.util.ArrayList;
import java.util.List;

public class Director extends Artist {
    private String agent;

    public static List<Director> directorList = new ArrayList<>();

    public Director(String name, String surname, String country, String id, String agent) {
        super(name, surname, country, id);
        this.setAgent(agent);
        Director.directorList.add(this);
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
