import java.util.ArrayList;
import java.util.List;

public class StuntPerformer extends Performer {
    private String height;
    private String realActorId;

    public static List<StuntPerformer> stuntPerformerList = new ArrayList<StuntPerformer>();

    public StuntPerformer(String name, String surname, String country, String id, String height, String realActorId) {
        super(name, surname, country, id);
        this.setHeight(height);
        this.setRealActorId(realActorId);
        StuntPerformer.stuntPerformerList.add(this);
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRealActorId() {
        return realActorId;
    }

    public void setRealActorId(String realActorId) {
        this.realActorId = realActorId;
    }
}
