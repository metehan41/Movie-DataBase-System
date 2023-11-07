import java.util.ArrayList;
import java.util.List;

public class TvSeries extends Film {

    private String startDate;
    private String endDate;
    private String numberOfSeasons;
    private String numberOfEpisodes;
    private String genre;
    public ArrayList<Writer> writers = new ArrayList<>();

    public static List<TvSeries> tvSeriesList = new ArrayList<>();


    public TvSeries(String filmId, String filmTitle, String language, String directors, String runtime, String country,
                    String cast, String startDate, String endDate, String numberOfSeasons, String numberOfEpisodes,
                    String genre, String writer) {

        super(filmId, filmTitle, language, directors, runtime, country, cast);

        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfSeasons = numberOfSeasons;
        this.numberOfEpisodes = numberOfEpisodes;
        this.genre = genre;
        this.setWriters(writer);

        TvSeries.tvSeriesList.add(this);
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(String numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(String numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
            return writer.substring(0, writer.length() - 2);
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

    public String viewer() {
        String message;
        String ratingScore = Double.toString(this.getRatingScore()).replaceAll("\\.",",").substring(0,3);
        if (this.getRatedUser() == 0) {
            message = "Awaiting for votes\n";
        } else if (this.getRatingScore() - (int) this.getRatingScore() != 0) {
            message = "Rating: " + " " + ratingScore + "/10 from " + this.getRatedUser() + " users\n";
        } else {
            message = "Rating: " + " " + (int) this.getRatingScore() + "/10 from " + this.getRatedUser() + " users\n";
        }
        return  "VIEWFILM" + "\t" + this.getFilmId() + "\n\n" +
                this.getFilmTitle() + " " + "(" + this.getStartDate().substring(6) + "-" + this.getEndDate().substring(6) + ")" + "\n\n" +
                this.getNumberOfSeasons() + " seasons, " + this.getNumberOfEpisodes() + " episodes" + "\n" +
                this.getGenre() + "\n" +
                "Writers: " + this.getWriterAsString() + "\n" +
                "Director: " + this.getDirectorAsString() + "\n" +
                "Stars: " + this.getArtistAsString() + "\n" +
                message;
    }
}