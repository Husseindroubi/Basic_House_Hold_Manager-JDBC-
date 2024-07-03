package household.data.modelling;

import java.util.List;

public class Person {
    private int id;
    private String name;
    private int householdId;
    private List<Pet> pets;

    public Person(String name, int householdId) {
        this.name = name;
        this.householdId = householdId;
    }

    public Person(int id, String name, int householdId, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.householdId = householdId;
        this.pets = pets;
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

    public int getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(int householdId) {
        this.householdId = householdId;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return id+". Name: "+  name +", Household No: "+  householdId +", \nPets: "+  pets.toString()+"\n";
    }
}
