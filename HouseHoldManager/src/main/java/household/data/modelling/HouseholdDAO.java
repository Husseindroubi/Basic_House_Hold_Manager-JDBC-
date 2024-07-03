package household.data.modelling;

import java.util.List;

public interface HouseholdDAO {
    Household createHousehold(Household household);
    void getHousehold(int id);
    void updateHousehold(String newCity, int id);
    void deleteHousehold(int householdId);
    List<Household> getAllHouseholds();
}
