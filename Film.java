import java.util.ArrayList;
import java.util.List;


public class Film {

    private double ratingScore;
    private int ratedUser;
    private String filmId;
    private String filmTitle;
    private String language;
    private String runtime;
    private String country;
    private ArrayList<Director> directors = new ArrayList<>();
    private ArrayList<Artist> artists = new ArrayList<>();

    public static List<Film> filmList = new ArrayList<>();

    public Film(String filmId, String filmTitle, String language, String directors, String runtime, String country, String cast) {

        this.setFilmId(filmId);
        this.setFilmTitle(filmTitle);
        this.setLanguage(language);
        this.setDirectors(directors);
        this.setRuntime(runtime);
        this.setCountry(country);
        this.setArtists(cast);
        Film.filmList.add(this);
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(int ratingScore, int user) {
        if (this.ratedUser != 0) {
            this.ratingScore = (ratingScore + this.getRatingScore() * ratedUser) / (ratedUser + 1);
            this.setRatedUser(user);
        } else {
            this.ratingScore = ratingScore;
            this.setRatedUser(user);
        }
    }

    public void edit(int ratingScore, int newRatingScore) {
        this.ratingScore = (this.ratingScore * ratedUser - ratingScore) / (ratedUser - 1);
        if (this.ratedUser != 0) {
            this.ratingScore = (newRatingScore + this.getRatingScore() * (ratedUser - 1)) / ratedUser;
            this.setRatedUser(0);
        } else {
            this.ratingScore = ratingScore;
            this.setRatedUser(0);
        }
    }

    public void remove(int ratingScore) {
        if (ratedUser - 1 != 0) {
            this.ratingScore = (this.ratingScore * this.ratedUser - ratingScore) / (this.ratedUser - 1);
            this.ratedUser--;
        } else {
            this.ratingScore = 0;
            this.ratedUser--;
        }
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<Director> getDirectors() {
        return directors;
    }

    public String getDirectorAsString() {
        StringBuilder director = new StringBuilder();
        for (Director director1: directors) {
            director.append(new StringBuilder(director1.getName() + " " + director1.getSurname()));
            director.append(", ");
        }
        if (director.length() != 0) {
            return director.toString().substring(0, director.length() - 2);
        } else {
            return "";
        }
    }

    public void setDirectors(String directors) {
        String[] directors2 = directors.split(",");
        for (String director: directors2) {
            for (Director director2: Director.directorList) {
                if (director.equals(director2.getId())) {
                    this.directors.add(director2);
                }
            }
        }
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public String getArtistAsString() {
        StringBuilder artist = new StringBuilder();
        for (Artist artist1: artists) {
            artist.append(new StringBuilder(artist1.getName()) + " " + artist1.getSurname());
            artist.append(", ");
        }
        if (artist.length() != 0) {
            return artist.toString().substring(0, artist.length() - 2);
        } else {
            return "";
        }
    }

    public void setArtists(String artists) {
        String[] artists2 = artists.split(",");
        for (String artist: artists2) {
            for (Artist artist2: Artist.artistList) {
                if (artist.equals(artist2.getId())) {
                    this.artists.add(artist2);
                }

            }
        }
    }

    public void setRatedUser(int user) {
        this.ratedUser += user;
    }

    public int getRatedUser() {
        return ratedUser;
    }
}