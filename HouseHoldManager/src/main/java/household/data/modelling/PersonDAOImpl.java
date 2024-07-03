package household.data.modelling;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonDAOImpl implements PersonDAO {
    private final Connection connection;
    List<Person> persons;
    List<Person> personList;

    public PersonDAOImpl() throws SQLException {
        connection = MySQLConnection.getInstance();
        persons = new ArrayList<>();
        personList = new ArrayList<>();

    }



    @Override
    public Person createPerson(Person person) {
        String query = "INSERT INTO Person (name, householdId) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getHouseholdId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                person.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public void getPerson(int personId) {
        String query = "SELECT * FROM Person WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, personId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "  " + resultSet.getString("name") + "  " +
                        resultSet.getInt("householdId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePerson(String newName, int id) {
        String sql = "UPDATE Person SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deletePerson(int personId) {
        String sql = "DELETE FROM Person WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, personId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Person> getAllPersons() {
        String query = "SELECT * FROM person";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Id Number: "+ resultSet.getInt("id") + ", " + resultSet.getString
                        ("name") +", Household No:  "+ resultSet.getInt("householdId"));
            }
            // Populate pets for each person
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
