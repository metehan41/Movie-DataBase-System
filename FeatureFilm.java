import java.util.ArrayList;
import java.util.List;

public class FeatureFilm extends Film {
    private String releaseDate;
    private String budget;
    private ArrayList<Writer> writers = new ArrayList<Writer>();
    private String genre;

    public static List<FeatureFilm> featureFilmList = new ArrayList<>();

    public FeatureFilm(String filmId, String filmTitle, String language, String directors, String runtime, String country,
                       String cast, String releaseDate, String budget, String writers, String genre) {

        super(filmId, filmTitle, language, directors, runtime, country, cast);

        this.setReleaseDate(releaseDate);
        this.setBudget(budget);
        this.setWriters(writers);
        this.setGenre(genre);

        FeatureFilm.featureFilmList.add(this);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public ArrayList<Writer> getWriters() {
        return writers;
    }

    public String getWriterAsString() {
        StringBuilder writer = new StringBuilder();
        for (Writer writer2: writers ) {
            writer.append(new StringBuilder(writer2.getName() + " " + writer2.getSurname()));
            writer.append(", ");
        }
        if (writer.length() != 0) {
            return writer.toString().substring(0, writer.length() - 2);
        } else {
            return "";
        }
    }

    public void setWriters(String writers) {
        String[] writers2 = writers.split(",");
        for (String writer: writers2) {
            for (Writer writer2: Writer.writerList) {
                if (writer.equals(writer2.getId())) {
                    this.writers.add(writer2);
                }
            }
        }
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
        return "VIEWFILM" + "\t" + this.getFilmId() + "\n\n" +
                this.getFilmTitle() + " " + "(" + this.getReleaseDate().substring(6) + ")" + "\n" +
                this.getGenre() + "\n" +
                "Writers: " + this.getWriterAsString() + "\n" +
                "Director: " + this.getDirectorAsString() + "\n" +
                "Stars: " + this.getArtistAsString() + "\n" +
                message;
    }
}
