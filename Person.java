import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String surname;
    private String country;
    private String id;

    public static List<Person> personList = new ArrayList<>();

    public Person() {}

    public Person(String name, String surname, String country, String id) {
        this.setName(name);
        this.setSurname(surname);
        this.setCountry(country);
        this.setId(id);
        Person.personList.add(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}