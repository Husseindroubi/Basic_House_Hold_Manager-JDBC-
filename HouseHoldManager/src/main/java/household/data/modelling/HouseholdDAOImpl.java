package household.data.modelling;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseholdDAOImpl implements HouseholdDAO {
    private final Connection connection;

    public HouseholdDAOImpl() throws SQLException {
        connection = MySQLConnection.getInstance();
    }

    // findPersonByHousehold?

    @Override
    public Household createHousehold(Household household) {
        String query = "INSERT INTO Household (name, city) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, household.getName());
            statement.setString(2, household.getCity());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                household.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return household;
    }

    @Override
    public void getHousehold(int id) {
        String query = "SELECT * FROM Household WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "  " + resultSet.getString("name") + "  " +
                        resultSet.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateHousehold(String newCity, int id) {
        String sql = "UPDATE Household SET city = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newCity);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteHousehold(int householdId) {
        String sql = "DELETE FROM Household WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, householdId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Household> getAllHouseholds() {
        List<Household> households = new ArrayList<>();
        String query = "SELECT * FROM Household";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Household household = new Household(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        null);
                households.add(household);
            }
            // Populate persons for each household
            for (Household household : households) {
                household.setPersons(getPersonsForHousehold(household.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return households;
    }

    private List<Person> getPersonsForHousehold(int householdId) {
        List<Person> persons = new ArrayList<>();
        String query = "SELECT * FROM Person WHERE householdId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, householdId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("householdId"),
                        null);
                persons.add(person);
            }
            // Populate pets for each Person
            for (Person person : persons) {
                person.setPets(getPetsForPerson(person.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    private List<Pet> getPetsForPerson(int personId) {
        List<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM Pet WHERE personId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, personId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pet pet = new Pet(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("species"),
                        resultSet.getInt("personId"));
                pets.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }
}