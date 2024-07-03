package household.data.modelling;

import java.util.List;

public interface PersonDAO {
    Person createPerson(Person person);
    void getPerson(int personId);
    void updatePerson(String newName, int id);
    void deletePerson(int personId);
    List<Person> getAllPersons();
}

