package household.data.modelling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PetDAOImplTest {

    private PetDAO petDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        petDAO = new PetDAOImpl();
        connection = MySQLConnection.getInstance();
    }

    @Test
    void createPetTest() {
        // creating new Pets
        Pet pet1 = new Pet("Tutsi", "Turtle", 1);
        Pet pet2 = new Pet("Bobby", "Dog", 2);
        Pet pet3 = new Pet("Lulu Caty", "Cat", 1);
        pet1 = petDAO.createPet(pet1);
        pet2 = petDAO.createPet(pet2);
        pet3 = petDAO.createPet(pet3);

        // checking if pets created
        assertNotNull(pet1);
        assertNotNull(pet2);
        assertNotNull(pet3);
    }

    @Test
    void getPetTest() {
        // create a new Pet
        Pet pet4 = new Pet("Kiki Riki", "Parrot", 3);
        pet4 = petDAO.createPet(pet4);
        assertNotNull(pet4);

        // trying to get this Pet
        petDAO.getPet(pet4.getId());
        assertEquals("Kiki Riki", pet4.getName());
        assertEquals(3, pet4.getPersonId());
    }

    @Test
    void updatePetTest() {
        // create a new Pet
        Pet pet5 = new Pet("Pikachu", "Pokemon", 2);
        pet5 = petDAO.createPet(pet5);
        assertNotNull(pet5);

        // Update pet4
        pet5.setName("No More Pikachu");
        petDAO.updatePet(pet5.getName(), pet5.getId());

        // Get the updated person
        petDAO.getPet(pet5.getId());
        assertEquals("No More Pikachu", pet5.getName());
    }

    @Test
    void deletePetTest() {
        // Create a new Pet
        Pet testPet = new Pet("To Delete", "Not Pet", 1);
        testPet = petDAO.createPet(testPet);
        assertNotNull(testPet);

        // Delete the Pet
        petDAO.deletePet(testPet.getId());
    }
}