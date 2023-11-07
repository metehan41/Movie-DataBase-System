/* Metehan Sarikaya
 * 21993049 BBM104
 * Assignment 2 simple Movie Database System
 * 16.04.2021
 */


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Main {

    public static String peopleFile;
    public static String filmFile;
    public static String commandFile;
    public static String outputFile;

    public static void main(String[] args) throws IOException {

        peopleFile = args[0];
        filmFile = args[1];
        commandFile = args[2];
        outputFile = args[3];

        readPeople();
        readFilm();
        readCommand();
    }

    public static String numberFormat(double number) {
        if (number - (int) number != 0) {
            return Double.toString(number).substring(0,3);
        }else {
            return Integer.toString((int) number);
        }
    }

    public static void printToFile(String message, boolean append) {
        File file = new File(outputFile);
        try (FileWriter writer = new FileWriter(file, append)) {
            writer.write(message);
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    public static void printToFile(String message) {
        File file = new File(outputFile);
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    public static void readPeople() {
        try {
            Scanner scanner = new Scanner(new File(peopleFile));
            while (scanner.hasNextLine()) {
                String person = scanner.next();
                String id = scanner.next();
                String name = scanner.next();
                String surname = scanner.next();
                String country = scanner.next();
                switch (person) {
                    case "Actor:":
                        String height = scanner.next();
                        new Actor(name, surname, country, id, height);
                        break;

                    case "Writer:":
                        String type = scanner.next();
                        new Writer(name, surname, country, id, type);
                        break;

                    case "Director:":
                        String agent = scanner.next();
                        new Director(name, surname, country, id, agent);
                        break;

                    case "ChildActor:":
                        String age = scanner.next();
                        new ChildActor(name, surname, country, id, age);
                        break;

                    case "StuntPerformer:":
                        String height2 = scanner.next();
                        String realId = scanner.next();
                        new StuntPerformer(name, surname, country, id, height2, realId);
                        break;

                    case "User:":
                        new User(name, surname, country, id);
                        break;
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public static void readFilm() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filmFile));
        while (scanner.hasNext()) {
            String film = scanner.next();
            String id = scanner.next();
            String title = scanner.next();
            String language = scanner.next();
            String director = scanner.next();
            String runtime = scanner.next();
            String country = scanner.next();
            String cast = scanner.next();
            switch (film) {
                case "FeatureFilm:":
                    String genre = scanner.next();
                    String releaseDate = scanner.next();
                    String writer = scanner.next();
                    String budget = scanner.next();
                    new FeatureFilm(id, title, language, director, runtime, country, cast, releaseDate, budget, writer, genre);
                    break;

                case "ShortFilm:":
                    String genre2 = scanner.next();
                    String releaseDate2 = scanner.next();
                    String writer2 = scanner.next();
                    if (Integer.parseInt(runtime) <= 40) {
                        new ShortFilm(id, title, language, director, runtime, country, cast, releaseDate2, writer2, genre2);
                    }
                    break;

                case "Documentary:":
                    String releaseDate3 = scanner.next();
                    new Documentary(id, title, language, director, runtime, country, cast, releaseDate3);
                    break;

                case "TVSeries:":
                    String genre3 = scanner.next();
                    String writer3 = scanner.next();
                    String startDate = scanner.next();
                    String endDate = scanner.next();
                    String season = scanner.next();
                    String episode = scanner.next();
                    new TvSeries(id, title, language, director, runtime, country, cast, startDate, endDate, season, episode, genre3, writer3);
                    break;
            }
        }
    }

    public static void readCommand() {
        printToFile("", false);
        String line = "-----------------------------------------------------------------------------------------------------";
        try {
            Scanner scanner = new Scanner(new File(commandFile));
            while (scanner.hasNext()) {
                String commandType = scanner.next();
                switch (commandType) {
                    case "RATE":
                        String userId = scanner.next();
                        String filmId = scanner.next();
                        Integer ratingPoint = scanner.nextInt();
                        rater(userId, filmId, ratingPoint);
                        printToFile(line);
                        break;

                    case "ADD":
                        scanner.next();
                        String id = scanner.next();
                        String title = scanner.next();
                        String language = scanner.next();
                        String directory = scanner.next();
                        String runTime = scanner.next();
                        String country = scanner.next();
                        String performer = scanner.next();
                        String genre = scanner.next();
                        String releaseDate = scanner.next();
                        String writer = scanner.next();
                        String budget = scanner.next();
                        boolean isIn = isIn(id);
                        boolean isDirectorIn = isDirectorIn(directory.split(","));
                        boolean isPerformerIn = isPerformerIn(performer.split(","));
                        boolean isWriterIn = isWriter(writer.split(","));
                        printToFile("ADD\tFEATUREFILM\t" + id + "\t" + title + "\t" + language + "\t" + directory
                                + "\t" + runTime + "\t" + country + "\t" + performer + "\t" + genre + "\t" + releaseDate
                                + "\t" + writer + "\t" + budget + "\n");

                        if (!isIn && isDirectorIn && isPerformerIn && isWriterIn) {
                            new FeatureFilm(id, title, language, directory, runTime, country, performer, releaseDate, budget, writer, genre);
                            printToFile("FeatureFilm added successfully\n" + "Film ID :" + id + "\n" + "Film title: " + title + "\n");
                            printToFile(line);
                        } else {
                            printToFile("Command Failed\n" + "Film ID: " + id + "\n" + "Film title: " + title + "\n");
                            printToFile(line);
                        }
                        break;

                    case "VIEWFILM":
                        String filmId2 = scanner.next();
                        viewer(filmId2);
                        printToFile(line);
                        break;

                    case "LIST":
                        String listWhat = scanner.next();

                        switch (listWhat) {
                            case "USER":
                                String userId4 = scanner.next();
                                scanner.next();
                                listRatesOfUser(userId4);
                                printToFile(line);
                                break;

                            case "FILM":
                                scanner.next();
                                listTvSeries();
                                printToFile(line);
                                break;

                            case "FILMS":
                                scanner.next();
                                String listFilmHow = scanner.next();

                                switch (listFilmHow) {
                                    case "COUNTRY":
                                        String country2 = scanner.next();
                                        listFilmByCountry(country2);
                                        printToFile(line);
                                        break;

                                    case "RATE":
                                        scanner.next();
                                        listFilmByRate();
                                        printToFile(line);
                                        break;
                                }
                                break;

                            case "FEATUREFILMS":
                                String when = scanner.next();
                                switch (when) {
                                    case "BEFORE":
                                        String year = scanner.next();
                                        listFilmByBefore(year);
                                        printToFile(line);
                                        break;

                                    case "AFTER":
                                        String year2 = scanner.next();
                                        listFilmByAfter(year2);
                                        printToFile(line);
                                        break;
                                }
                                break;

                            case "ARTISTS":
                                scanner.next();
                                String country2 = scanner.next();
                                listArtist(country2);
                                printToFile(line);
                                break;
                        }
                        break;

                    case "EDIT":
                        scanner.next();
                        String userId2 = scanner.next();
                        String filmId3 = scanner.next();
                        int newRatingPoint = scanner.nextInt();
                        edit(userId2, filmId3, newRatingPoint);
                        printToFile(line);
                        break;

                    case "REMOVE":
                        scanner.next();
                        String userId3 = scanner.next();
                        String filmId4 = scanner.next();
                        remove(userId3, filmId4);
                        printToFile(line);
                        break;
                }
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void rater(String userId, String filmId, Integer ratingPoint) {
        for (User user : User.userList) {
            if (user.getId().equals(userId)) {
                for (Film film : Film.filmList) {
                    if (film.getFilmId().equals(filmId)) {
                        if (ratingPoint >= 1 && ratingPoint <= 10) {
                            if (!user.getRateList().containsKey(film)) {
                                printToFile("RATE" + "\t" + userId + "\t" + filmId + "\t" + ratingPoint + "\n");
                                user.setRateList(film, ratingPoint);
                                printToFile("Film rated successfully\n" +
                                        "Film type:" + " " + film.getClass().getName() +
                                        "\nFilm title:" + " " + film.getFilmTitle() + "\n");
                                return;
                            } else {
                                printToFile("RATE" + "\t" + userId + "\t" + filmId + "\t" + ratingPoint + "\n\n" +
                                        "This film was earlier rated\n");
                                return;
                            }
                        }
                    }
                }
            }
        }
        printToFile("RATE" + "\t" + userId + "\t" + filmId + "\t" + ratingPoint + "\n" +
                "\n" + "Command Failed" + "\n" + "User ID:" + "\t" + userId + "\n" +
                "Film ID:" + "\t" + filmId + "\n");
    }

    public static void viewer(String filmId) {

        for (Film film : Film.filmList) {
            if (film.getFilmId().equals(filmId)) {
                if (film instanceof FeatureFilm) {
                    for (FeatureFilm featureFilm : FeatureFilm.featureFilmList) {
                        if (featureFilm.getFilmId().equals(filmId)) {
                            printToFile(featureFilm.viewer());
                            printToFile("");
                            return;
                        }
                    }
                } else if (film instanceof ShortFilm) {
                    for (ShortFilm shortFilm : ShortFilm.shortFilmList) {
                        if (shortFilm.getFilmId().equals(filmId)) {
                            printToFile(shortFilm.viewer());
                            printToFile("");
                            return;
                        }
                    }
                } else if (film instanceof Documentary) {
                    for (Documentary documentary : Documentary.documentaryList) {
                        if (documentary.getFilmId().equals(filmId)) {
                            printToFile(documentary.viewer());
                            printToFile("");
                            return;
                        }
                    }
                } else if (film instanceof TvSeries) {
                    for (TvSeries tvSeries : TvSeries.tvSeriesList) {
                        if (tvSeries.getFilmId().equals(filmId)) {
                            printToFile(tvSeries.viewer());
                            printToFile("");
                            return;
                        }
                    }
                }
            }
        }
        printToFile("VIEWFILM\t" + filmId + "\n");
        printToFile("Command Failed\n" +
                "Film ID: " + filmId + "\n");
    }

    public static void edit(String userId, String filmId, int newRate) {
        printToFile("EDIT\tRATE\t" + userId + "\t" + filmId + "\t" + newRate + "\n");
        for (User user : User.userList) {
            if (user.getId().equals(userId)) {
                for (Film film : Film.filmList) {
                    if (film.getFilmId().equals(filmId)) {
                        if (!user.getRateList().isEmpty()) {
                            if (user.getRateList().get(film) != null) {
                                user.setNewRateList(film, newRate);
                                printToFile("New ratings done successfully" + "\n" +
                                        "Film title:" + " " + film.getFilmTitle() + "\n" +
                                        "Your rating:" + " " + newRate + "\n");
                                return;
                            }
                        } else {
                            printToFile("Command Failed" + "\n" +
                                    "User ID:" + " " + userId + "\n" +
                                    "Film ID" + " " + filmId + "\n");
                            return;
                        }
                    }
                }
            }
        }
        printToFile("Command Failed" + "\n" +
                "User ID:" + " " + userId + "\n" +
                "Film ID" + " " + filmId + "\n");
    }

    public static void remove(String userId, String filmId) {
        printToFile("REMOVE\tRATE\t" + userId + "\t" + filmId + "\n");
        for (User user : User.userList) {
            if (user.getId().equals(userId)) {
                for (Film film : Film.filmList) {
                    if (film.getFilmId().equals(filmId)) {
                        HashMap<Film, Integer> rateList = user.getRateList();
                        if (rateList.get(film) != null) {
                            film.remove(user.getRateList().get(film));
                            user.getRateList().remove(film);
                            printToFile("Your film rating was removed successfully\n" +
                                    "Film title:" + " " + film.getFilmTitle() + "\n");
                            return;
                        } else {
                            printToFile("Command Failed" + "\n" +
                                    "User ID:" + " " + userId + "\n" +
                                    "Film ID" + " " + filmId + "\n");
                            return;
                        }
                    }
                }
            }
        }
        printToFile("Command Failed" + "\n" +
                "User ID:" + " " + userId + "\n" +
                "Film ID" + " " + filmId + "\n");
    }

    public static void listRatesOfUser(String userId) {
        boolean flag = true;
        printToFile("LIST\tUSER" + "\t" + userId + "\t" + "RATES\n");
        for (User user : User.userList) {
            if (user.getId().equals(userId)) {
                try {
                    Set<Film> films = user.getRateList().keySet();
                    List<Film> sortedList = new ArrayList<>(films);
                    sortedList.sort(Collections.reverseOrder(new sortByRate()));
                    if (films.size() != 0) {
                        for (Film film : films) {
                            flag = false;
                            printToFile(film.getFilmTitle() + ": " + user.getRateList().get(film));
                        }
                    } else {
                        printToFile("There is not any ratings so far");
                    }
                    return;
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        if (flag) {
            printToFile("Command Failed\n" + "User Id:" + " " + userId);
        }
        printToFile("");
    }

    public static void listTvSeries() {
        printToFile("LIST\tFILM\tSERIES\n");
        boolean flag = true;
        for (TvSeries tvSeries : TvSeries.tvSeriesList) {
            flag = false;
            printToFile(tvSeries.getFilmTitle() + " " + "(" + tvSeries.getStartDate().substring(6) + "-" + tvSeries.getEndDate().substring(6) + ")");
            printToFile(tvSeries.getNumberOfSeasons() + " " + "seasons and" + " " + tvSeries.getNumberOfEpisodes() + " " + "episodes" + "\n");
        }
        if (flag) {
            printToFile("No result" + "\n");
        }
    }

    public static void listFilmByCountry(String country) {
        printToFile("LIST\tFILMS\tBY\tCOUNTRY\t" + country + "\n");
        boolean flag = true;
        for (Film film : Film.filmList) {
            if (film.getCountry().equals(country)) {
                flag = false;
                printToFile("Film title:" + " " + film.getFilmTitle() + "\n" +
                        film.getRuntime() + " " + "min" + "\n" +
                        "Language:" + " " + film.getLanguage() + "\n");
            }
        }
        if (flag) {
            printToFile("No result\n");
        }
    }

    public static void listFilmByRate() {
        printToFile("LIST\tFILMS\tBY\tRATE\tDEGREE\n");

        List<FeatureFilm> featureFilmList = FeatureFilm.featureFilmList;
        List<ShortFilm> shortFilmList = ShortFilm.shortFilmList;
        List<Documentary> documentaryList = Documentary.documentaryList;
        List<TvSeries> tvSeriesList = TvSeries.tvSeriesList;

        featureFilmList.sort(Collections.reverseOrder(new sortByRate()));
        shortFilmList.sort(Collections.reverseOrder(new sortByRate()));
        documentaryList.sort(Collections.reverseOrder(new sortByRate()));
        tvSeriesList.sort(Collections.reverseOrder(new sortByRate()));

        printToFile("Feature Film:");
        if (featureFilmList.size() != 0) {
            for (FeatureFilm featureFilm : featureFilmList) {
                String ratingScore = numberFormat(featureFilm.getRatingScore());
                printToFile(featureFilm.getFilmTitle() + " " + "(" + featureFilm.getReleaseDate().substring(6) + ") " + "Ratings: " + ratingScore.replaceAll("\\.",",") + "/10 from " + featureFilm.getRatedUser() + " user");
            }
        } else {
            printToFile("No result");
        }

        printToFile("\nShort Film:");
        if (shortFilmList.size() != 0) {
            for (ShortFilm shortFilm : shortFilmList) {
                String ratingScore = numberFormat(shortFilm.getRatingScore());
                printToFile(shortFilm.getFilmTitle() + " " + "(" + shortFilm.getReleaseDate().substring(6) + ") " + "Ratings: " + ratingScore.replaceAll("\\.",",") + "/10 from " + shortFilm.getRatedUser() + " user");
            }
        } else {
            printToFile("No result");
        }

        printToFile("\nDocumentary");
        if (documentaryList.size() != 0) {
            for (Documentary documentary : documentaryList) {
                String ratingScore = numberFormat(documentary.getRatingScore());
                printToFile(documentary.getFilmTitle() + " " + "(" + documentary.getReleaseDate().substring(6) + ") " + "Ratings: " + ratingScore.replaceAll("\\.",",") + "/10 from " + documentary.getRatedUser() + " user");
            }
        } else {
            printToFile("No result");
        }

        printToFile("\nTV Series:");
        if (shortFilmList.size() != 0) {
            for (TvSeries tvSeries : tvSeriesList) {
                String ratingScore = numberFormat(tvSeries.getRatingScore());
                printToFile(tvSeries.getFilmTitle() + " " + "(" + tvSeries.getStartDate().substring(6) + "-" + tvSeries.getEndDate().substring(6) + ") " + "Ratings: " + ratingScore.replaceAll("\\.",",") + "/10 from " + tvSeries.getRatedUser() + " user");
            }
        } else {
            printToFile("No result");
        }
        printToFile("");
    }

    public static void listFilmByBefore(String year) throws ParseException {
        printToFile("LIST\tFEATUREFILMS\tBEFORE" + "\t" + year + "\n");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date d1 = simpleDateFormat.parse("01.01." + year);
        boolean flag = true;
        for (FeatureFilm featureFilm : FeatureFilm.featureFilmList) {
            Date d2 = simpleDateFormat.parse(featureFilm.getReleaseDate());
            if (d1.after(d2)) {
                flag = false;
                printToFile("Film title: " + featureFilm.getFilmTitle() + " " + "(" + featureFilm.getReleaseDate().substring(6) + ")");
                printToFile(featureFilm.getRuntime() + " " + "min");
                printToFile("Language:" + " " + featureFilm.getLanguage() + "\n");
            }
        }
        if (flag) {
            printToFile("No result\n");
        }
    }

    public static void listFilmByAfter(String year) throws ParseException {

        printToFile("LIST\tFEATUREFILMS\tAFTER" + "\t" + year + "\n");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date d1 = simpleDateFormat.parse("01.01." + year);
        boolean flag = true;
        for (FeatureFilm featureFilm : FeatureFilm.featureFilmList) {
            Date d2 = simpleDateFormat.parse(featureFilm.getReleaseDate());
            if (d1.before(d2)) {
                flag = false;
                printToFile("Film title: " + featureFilm.getFilmTitle() + " " + "(" + featureFilm.getReleaseDate().substring(6) + ")");
                printToFile(featureFilm.getRuntime() + " " + "min");
                printToFile("Language:" + " " + featureFilm.getLanguage() + "\n");
            }
        }
        if (flag) {
            printToFile("No result\n");
        }
    }

    public static void listArtist(String country) {
        printToFile("LIST ARTIST FROM" + "\t" + country + "\n");
        boolean flagDirector = true;
        boolean flagWriter = true;
        boolean flagActor = true;
        boolean flagChildActor = true;
        boolean flagStuntPerformer = true;

        printToFile("Directors:");
        for (Director director : Director.directorList) {
            if (director.getCountry().equals(country)) {
                flagDirector = false;
                printToFile(director.getName() + " " + director.getSurname() + " " + director.getAgent());
            }
        }
        if (flagDirector) {
            printToFile("No result" + "\n");
        }

        printToFile("\nWriters:");
        for (Writer writer : Writer.writerList) {
            if (writer.getCountry().equals(country)) {
                flagWriter = false;
                printToFile(writer.getName() + " " + writer.getSurname() + " " + writer.getWritingStyle());
            }
        }
        if (flagWriter) {
            printToFile("No result");
        }


        printToFile("\nActors:");
        for (Actor actor : Actor.actorList) {
            if (actor.getCountry().equals(country)) {
                flagActor = false;
                printToFile(actor.getName() + " " + actor.getSurname() + " " + actor.getHeight() + "cm");
            }
        }
        if (flagActor) {
            printToFile("No result");
        }

        printToFile("\nChildActors:");
        for (ChildActor childActor : ChildActor.childActorList) {
            if (childActor.getCountry().equals(country)) {
                flagChildActor = false;
                printToFile(childActor.getName() + " " + childActor.getSurname() + " " + childActor.getAge());
            }
        }
        if (flagChildActor) {
            printToFile("No result");
        }

        printToFile("\nStuntPerformers:");
        for (StuntPerformer stuntPerformer : StuntPerformer.stuntPerformerList) {
            if (stuntPerformer.getCountry().equals(country)) {
                flagStuntPerformer = false;
                printToFile(stuntPerformer.getName() + " " + stuntPerformer.getSurname() + " " + stuntPerformer.getHeight() + "cm");
            }
        }
        if (flagStuntPerformer) {
            printToFile("No result");
        }
        printToFile("");
    }

    public static boolean isIn(String id) {

        for (FeatureFilm featureFilm2 : FeatureFilm.featureFilmList) {
            if (featureFilm2.getFilmId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDirectorIn(String[] directors) {
        int flag = 0;
        for (String director : directors) {
            for (Director director3 : Director.directorList) {
                if (director.equals(director3.getId())) {
                    flag++;
                }
            }
        }
        return directors.length == flag;
    }

    public static boolean isWriter(String[] writers) {
        int flag = 0;
        for (String writer : writers) {
            for (Writer writer2 : Writer.writerList) {
                if (writer.equals(writer2.getId())) {
                    flag++;
                }
            }
        }
        return flag == writers.length;
    }

    public static boolean isPerformerIn(String[] performers) {
        int flag = 0;

        for (String performer : performers) {
            for (Performer performer2 : Performer.performerList) {
                if (performer.equals(performer2.getId())) {
                    flag++;
                }
            }
        }
        return flag == performers.length;
    }
}
class sortByRate implements Comparator<Film> {

    @Override
    public int compare(Film o1, Film o2) {
        return Double.compare(o1.getRatingScore(), o2.getRatingScore());
    }
}
