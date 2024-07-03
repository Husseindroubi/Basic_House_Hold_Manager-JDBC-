package household.data.modelling;

import java.util.List;

public interface PetDAO {
    Pet createPet(Pet pet);
    void getPet(int petId);
    void updatePet(String newName, int id);
    void deletePet(int petId);
    List<Pet> getAllPets();
}
