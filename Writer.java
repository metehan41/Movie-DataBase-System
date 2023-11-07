import java.util.ArrayList;
import java.util.List;

public class Writer extends Artist {
    public static List<Writer> writerList = new ArrayList<>();
    private String writingStyle;

    public Writer(String name, String surname, String country, String id, String writingStyle) {
        super(name, surname, country, id);
        this.setWritingStyle(writingStyle);
        Writer.writerList.add(this);
    }

    public String getWritingStyle() {
        return writingStyle;
    }

    public void setWritingStyle(String writingStyle) {
        this.writingStyle = writingStyle;
    }
}
