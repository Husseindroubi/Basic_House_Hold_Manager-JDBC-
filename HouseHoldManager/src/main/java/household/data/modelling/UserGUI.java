package household.data.modelling;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserGUI {
    //private final Connection connection;
    private HouseholdDAO householdDAO;
    private PersonDAO personDAO;
    private PetDAO petDAO;
    private List<Household> households;
    private List<Person> persons;
    private List<Pet> pets;
    Scanner sc = new Scanner(System.in);

    public UserGUI() throws SQLException {
        householdDAO = new HouseholdDAOImpl();
        personDAO = new PersonDAOImpl();
        petDAO = new PetDAOImpl();
        households = new ArrayList<>();
        persons = new ArrayList<>();
        pets = new ArrayList<>();
        //connection = MySQLConnection.getInstance();
    }

    public void showMainMenu() {
        System.out.println("Please choose an option: ");
        System.out.println("1. Create Household");
        System.out.println("2. Read Household");
        System.out.println("3. Update Household");
        System.out.println("4. Delete Household");
        System.out.println("5. List All Households");

        System.out.println("6. Create Person");
        System.out.println("7. Read Person");
        System.out.println("8. Update Person");
        System.out.println("9. Delete Person");

        System.out.println("10. Create Pet");
        System.out.println("11. Read Pet");
        System.out.println("12. Update Pet");
        System.out.println("13. Delete Pet");

        System.out.println("0. Exit");
    }

    public void createHousehold() {
        listAllHouseholds();
        System.out.println("Enter household name: ");
        String name = sc.nextLine();
        System.out.println("Enter household city: ");
        String city = sc.nextLine();
        if (name.matches(".*\\d.*") || city.matches(".*\\d.*")) {
            System.out.println("Name or City shouldn't contain numbers!");
        } else {
            Household h = new Household(name, city);
            householdDAO.createHousehold(h);
            households.add(h);
        }
    }

    public void getHousehold() {
        listAllHouseholds();
        System.out.println("Enter household ID to look for it:");
        int id = sc.nextInt();
        sc.nextLine();
        householdDAO.getHousehold(id);
    }

    public void updateHousehold() throws Exception {
        listAllHouseholds();
        System.out.println("Enter household ID you want to update:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new City name for Household:");
        String newCity = sc.nextLine();
        if (newCity.matches(".*\\d.*")) {
            throw new InvalidExceptions("This Name couldn't contain numbers!");
        } else {
            householdDAO.updateHousehold(newCity, id);
        }
    }

    public void deleteHousehold() {
        listAllHouseholds();
        System.out.println("Enter household ID:");
        int id = sc.nextInt();
        sc.nextLine();
        householdDAO.deleteHousehold(id);
    }

    public void listAllHouseholds() {
        System.out.println("Here are the list of all Households: ");
        List<Household> hl = householdDAO.getAllHouseholds();
        for (Household h: hl){
            System.out.println(h);
        }
    }

    public void createPerson() throws InvalidExceptions {
        getAllPersons();
        System.out.println("Enter Person name: ");
        String name = sc.nextLine();
        if (name.matches(".*\\d.*")) {
            throw new InvalidExceptions("This Name shouldn't contain numbers!");
        } else {
            System.out.println("Enter the Household number that you want to add person inside it: ");
            int householdId = sc.nextInt();
            sc.nextLine();
            Person p = new Person(name, householdId);
            personDAO.createPerson(p);
            persons.add(p);
        }
    }

    public void getPerson() {
        getAllPersons();
        System.out.println("Enter Person ID to look for it:");
        int personId = sc.nextInt();
        sc.nextLine();
        personDAO.getPerson(personId);
    }

    public void updatePerson() throws InvalidExceptions {
        getAllPersons();
        System.out.println("Enter Person ID you want to update:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new Person name:");
        String newName = sc.nextLine();
        if (newName.matches(".*\\d.*")) {
            throw new InvalidExceptions("This Name shouldn't contain numbers!");
        } else {
            personDAO.updatePerson(newName, id);
        }
    }

    public void deletePerson() {
        getAllPersons();
        System.out.println("Enter Person ID:");
        int personId = sc.nextInt();
        sc.nextLine();
        personDAO.deletePerson(personId);
    }

    public void getAllPersons() {
        System.out.println("Here are the Persons list: ");
        List<Person> per = personDAO.getAllPersons();
        for (Person p: per){
            System.out.println(p);
        }
    }

    public void createPet() throws InvalidExceptions {
        petDAO.getAllPets();
        System.out.println("Enter Pet name: ");
        String name = sc.nextLine();
        if (name.matches(".*\\d.*")) {
            throw new InvalidExceptions("This Name shouldn't contain numbers!");
        }
        System.out.println("Enter Pet species: ");
        String species = sc.nextLine();
        if (species.matches(".*\\d.*")) {
            throw new InvalidExceptions("This Name shouldn't contain numbers!");
        } else {
            System.out.println("Enter Person number who you want to add pet to: ");
            int personId = sc.nextInt();
            sc.nextLine();
            Pet pet = new Pet(name, species, personId);
            petDAO.createPet(pet);
            pets.add(pet);
        }
    }

    public void getPet() {
        petDAO.getAllPets();
        System.out.println("Enter Pet ID to look for it:");
        int id = sc.nextInt();
        sc.nextLine();
        petDAO.getPet(id);
    }

    public void updatePet() throws InvalidExceptions {
        petDAO.getAllPets();
        System.out.println("Enter Pet ID you want to update:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new Pet name:");
        String newName = sc.nextLine();
        if (newName.matches(".*\\d.*")) {
            throw new InvalidExceptions("This Name shouldn't contain numbers!");
        } else {
            petDAO.updatePet(newName, id);
        }
    }

    public void deletePet() {
        petDAO.getAllPets();
        System.out.println("Enter Pet ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        petDAO.deletePet(id);
    }


    public void switchcaseDisplayer() throws Exception {
        int option;
        do {
            do {
                System.out.println("type Enter or anything to continue ..");
                sc.nextLine();
                showMainMenu();
                option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1 -> createHousehold();
                    case 2 -> getHousehold();
                    case 3 -> updateHousehold();
                    case 4 -> deleteHousehold();
                    case 5 -> listAllHouseholds();
                    case 6 -> createPerson();
                    case 7 -> getPerson();
                    case 8 -> updatePerson();
                    case 9 -> deletePerson();
                    case 10 -> createPet();
                    case 11 -> getPet();
                    case 12 -> updatePet();
                    case 13 -> deletePet();
                    case 0 -> System.out.println("See you again.. Good bye!");
                    default -> System.out.println("invalid value, please try again");
                }
            } while (option > 13 || option < 0);
        } while (option != 0);
    }

}

