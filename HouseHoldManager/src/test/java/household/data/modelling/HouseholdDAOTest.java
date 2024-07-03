package household.data.modelling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class HouseholdDAOTest {
    private Connection connection;
    private HouseholdDAO householdDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Establish database connection
        connection = MySQLConnection.getInstance();

        // Initialize HouseholdDAO
        householdDAO = new HouseholdDAOImpl();
    }

    @Test
    public void testCreateHousehold() {
        // Create a new household
        Household household = new Household("Household 1", "City 1");
        household = householdDAO.createHousehold(household);
        assertNotNull(household);
    }

    @Test
    public void testGetHousehold() {
        // Create a new household
        Household household = new Household("Household 2", "City 2");
        household = householdDAO.createHousehold(household);
        assertNotNull(household);

        // Get the created household
        householdDAO.getHousehold(household.getId());
    }

    @Test
    public void testUpdateHousehold() {
        // Create a new household
        Household household = new Household( "Household 3", "City 3");
        household = householdDAO.createHousehold(household);
        assertNotNull(household.getName(), household.getCity());

        // Update the household
        household.setCity("Updated City 3");
        householdDAO.updateHousehold(household.getCity(), household.getId());
    }

    @Test
    public void testDeleteHousehold() {
        // Create a new household
        Household household = new Household("Household 4", "City 4");
        household = householdDAO.createHousehold(household);
        assertNotNull(household);

        // Delete the household
        householdDAO.deleteHousehold(household.getId());
        if(!householdDAO.getAllHouseholds().contains(household)) {
            System.out.println("Successfully deleted ");
        }
    }

}