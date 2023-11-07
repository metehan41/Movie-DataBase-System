import java.util.*;

public class User extends Person {

    private LinkedHashMap<Film, Integer> rateList = new LinkedHashMap<>();

    public static List<User> userList = new ArrayList<>();

    public User(String name, String surname, String country, String id) {
        super(name, surname, country, id);
        User.userList.add(this);
    }

    public LinkedHashMap<Film, Integer> getRateList() {
        return rateList;
    }

    public void setRateList(Film film, int ratePoint) {
        this.rateList.put(film, ratePoint);
        film.setRatingScore(ratePoint,1);
    }

    public void setNewRateList(Film film, int ratePoint) {
        Set<Film> filmSet = this.getRateList().keySet();
        for (Film film1: filmSet) {
            if (film1.getFilmId().equals(film.getFilmId())) {
                film.edit(this.rateList.get(film), ratePoint);
                this.rateList.put(film, ratePoint);
            }
        }
    }
}