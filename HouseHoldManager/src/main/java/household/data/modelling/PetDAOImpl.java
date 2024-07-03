package household.data.modelling;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {
    private final Connection connection;

    public PetDAOImpl() throws SQLException {
        connection = MySQLConnection.getInstance();
    }

    @Override
    public Pet createPet(Pet pet) {
        String sql = "INSERT INTO Pet (name, species, personId) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getSpecies());
            statement.setInt(3, pet.getPersonId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    pet.setId(resultSet.getInt(1));
                } else {
                    throw new SQLException("Creating pet failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pet;
    }

    @Override
    public void getPet(int petId) {
        String sql = "SELECT * FROM Pet WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, petId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Id: "+ resultSet.getInt("id") + ", Pet Name:  " + resultSet.getString("name")
                        + ", Species:  " + resultSet.getString("species")+", Holder Id:  "+ resultSet.getInt("personId"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updatePet(String newName, int id) {
        String sql = "UPDATE Pet SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deletePet(int petId) {
        String sql = "DELETE FROM Pet WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, petId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM pet";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id")+" "+resultSet.getString("name")+"  "+
                        resultSet.getString("species")+" "+ resultSet.getInt("personId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }
}