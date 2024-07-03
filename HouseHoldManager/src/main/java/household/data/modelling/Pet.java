package household.data.modelling;

public class Pet {
    private int id;
    private String name;
    private String species;
    private int personId;

    public Pet(String name, String species, int personId)  {
        this.name = name;
        this.species = species;
        this.personId = personId;
    }

    public Pet(int id ,String name, String species, int personId)  {
        this.id = id;
        this.name = name;
        this.species = species;
        this.personId = personId;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", personId=" + personId +
                '}';
    }
}

