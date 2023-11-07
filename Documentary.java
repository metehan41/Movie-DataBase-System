import java.util.ArrayList;
import java.util.List;

public class Documentary extends Film {
    private String releaseDate;

    public static List<Documentary> documentaryList = new ArrayList<>();

    public Documentary(String filmId, String filmTitle, String language, String directors, String runtime,
                       String country, String cast, String releaseDate) {
        super(filmId, filmTitle, language, directors, runtime, country, cast);
        this.setReleaseDate(releaseDate);
        documentaryList.add(this);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String viewer() {
        String message;
        String ratingScore = Double.toString(this.getRatingScore()).replaceAll("\\.",",").substring(0,3);
        if (this.getRatedUser() == 0) {
            message = "Awaiting for votes\n";
        } else if (this.getRatingScore() - (int) this.getRatingScore() != 0){
            message = "Rating: " + ratingScore + "/10 from " + this.getRatedUser() + " users\n";
        } else {
            message = "Rating: " + (int) this.getRatingScore() + "/10 from " + this.getRatedUser() + " users\n";
        }
        return  "VIEWFILM" + "\t" + this.getFilmId() + "\n\n" +
                this.getFilmTitle() + " " + "(" + this.getReleaseDate().substring(6) + ")" + "\n" + "\n" +
                "Director: " + this.getDirectorAsString() + "\n" +
                "Stars: " + this.getArtistAsString() + "\n" +
                message;
    }

}
