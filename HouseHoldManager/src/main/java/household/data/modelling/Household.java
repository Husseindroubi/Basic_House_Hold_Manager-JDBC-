package household.data.modelling;

import java.awt.font.TextHitInfo;
import java.util.List;

public class Household {
    private int id;
    private String name;
    private String city;
    private List<Person> persons;

    public Household(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Household(int id, String name, String city, List<Person> persons) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.persons  =persons;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return id+". Name: "+  name +", City: "+  city +", \nPersons: "+  persons.toString();
    }
}
