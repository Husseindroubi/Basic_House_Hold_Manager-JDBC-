package household.data.modelling;

import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;


public class PersonDAOTest {
    private Connection connection;
    private PersonDAO personDAO;

    @BeforeEach
    public void setUp() throws Exception {
        // Establish database connection
        connection = MySQLConnection.getInstance();

        // Initialize PersonDAO
        personDAO = new PersonDAOImpl();
    }


    @Test
    public void testCreatePerson() {
        // Create a new person
        Person person = new Person( "Person 1", 1);
        person = personDAO.createPerson(person);
        assertNotNull(person);
    }

    @Test
    public void testGetPerson() {
        // Create a new person
        Person person = new Person( "Person 2", 1);
        person = personDAO.createPerson(person);
        assertNotNull(person);

        // Get the created person
        personDAO.getPerson(person.getId());

        assertEquals("Person 2", person.getName());
        assertEquals(1, person.getHouseholdId());
    }

    @Test
    public void testUpdatePerson() {
        // Create a new person
        Person person = new Person( "Person 3", 2);
        person = personDAO.createPerson(person);
        assertNotNull(person);

        // Update the person
        person.setName("Updated Person 3");
        personDAO.updatePerson(person.getName(), person.getId());

        // Get the updated person
        personDAO.getPerson(person.getId());
        assertEquals("Updated Person 3", person.getName());
    }

    @Test
    public void testDeletePerson() {
        // Create a new person
        Person person = new Person("Person 4", 2);
        person = personDAO.createPerson(person);
        assertNotNull(person);

        // Delete the person
        personDAO.deletePerson(person.getId());

        // Check if person is deleted
        personDAO.getPerson(person.getId());
        if(!personDAO.getAllPersons().contains(person)) {
            System.out.println("Successfully deleted ");
        }
    }
}