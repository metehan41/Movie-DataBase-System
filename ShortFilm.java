import java.util.ArrayList;
import java.util.List;

public class ShortFilm extends Film {

    private String releaseDate;
    private String writer;
    private String genre;

    public static List<ShortFilm> shortFilmList = new ArrayList<>();

    public ShortFilm(String filmId, String filmTitle, String language, String directors, String runtime,
                     String country, String cast, String releaseDate, String writer, String genre) {

        super(filmId, filmTitle, language, directors, runtime, country, cast);

        this.setReleaseDate(releaseDate);
        this.setGenre(genre);
        this.setWriter(writer);
        ShortFilm.shortFilmList.add(this);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
                this.getFilmTitle() + " " + "(" + this.releaseDate.substring(6) + ")" + "\n" + "\n"  +
                this.getGenre() + "\n" +
                "Writers: " + this.getWriter() + "\n" +
                "Director: " + this.getDirectorAsString() + "\n" +
                "Stars: " + this.getArtistAsString() + "\n" +
                message;
    }
}
