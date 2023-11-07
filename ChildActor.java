import java.util.ArrayList;
import java.util.List;

public class ChildActor extends Performer {
    private String age;

    public static List<ChildActor> childActorList = new ArrayList<>();

    public ChildActor(String name, String surname, String country, String id, String age) {
        super(name, surname, country, id);
        this.setAge(age);
        ChildActor.childActorList.add(this);
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
