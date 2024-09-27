import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Owner {
    private String forename;
    private String lastname;
    private String city;
    private int age;

    private CarRegister carRegister; // Skapar en instans av CarRegister så att jag kan uttnyttja metoderna jag har
    // i den classen (tex printcars)

    // Skapar en konstruktor för en ägare med dessa egenskaper
    public Owner(String forename, String lastname, String city, int age) {
        this.forename = forename;
        this.lastname = lastname;
        this.age = age;
        this.city = city;
    }

    public Owner() { //Skapar denna så att jag kan skapa en instans i main.
        carRegister = new CarRegister();

    }

    // Metod för att kunna skapa en ny ägare som tar in värden från användaren och skickar över informationen
    // till konstruktorn som returnerar en ny ägare. Felhantering av exempelvis om man skriver in en bosktav
    // istället för siffra då man ska ange ålder.
    public Owner createOwner() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Ange ägarens förnamn: ");
        String forename = scan.nextLine();
        System.out.print("Ange ägarens efternamn: ");
        String lastname = scan.nextLine();
        System.out.print("Ange vilken stad ägaren bor i: ");
        String city = scan.nextLine();
        if (forename.isEmpty() || lastname.isEmpty() || city.isEmpty()){
            System.out.println("Felaktig inmatning, du har inte skrivit in något i någon av kolumnerna: " +
                    "namn/efternamn/stad"); //If-sats som kollar att man har matat in något i namn/efternamn/stad.
            return null;
        }
        try {
            System.out.print("Ange ägarens ålder: ");
            int age = scan.nextInt();
            Owner carOwner = new Owner(forename, lastname, city, age);
            System.out.println("Ägaren tillagd");
            return carOwner; // returnerar den nya ägaren.
        } catch (InputMismatchException e) {
            System.out.println("Felaktig inmatning prova igen");
            return null;
        }
    }
    // Metod för att skriva ut information om alla ägare i arraylist om det finns någon ägare.
    public void printOwners(ArrayList<Owner> owners) {
        if (owners.isEmpty()) {
            System.out.println("Finns inga ägare att skriva ut");
            return;
        }
        int ownerNumber = 1;
        for (Owner owner : owners) {
            System.out.println("Ägare " + ownerNumber + ":" + "\tFörnamn: " + owner.forename + "\tEfternamn: " +
                    owner.lastname + "\tStad:" + owner.city + "\t\tÅrsmodell: " + owner.age);
            ownerNumber++;
        }
    }
        //Metod för att koppla ihop en bil till en ägare.
    public void connect(ArrayList<CarRegister> carList, ArrayList<Owner> owners) {
        if (carList.isEmpty() || owners.isEmpty()) {
            System.out.println("Det finns inga bilar eller ägare att koppla");
            return; //Kollar om det listan av ägare eller bilar är tom, då kan ingen koppling ske.
        }
        carRegister.printCars(carList); //Skriver ut listan av bilar för att se vilka bilar man kan välja mellan
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Välj en bil att koppla (1-" + carList.size() + "): ");
            int carChoice;
            carChoice = scan.nextInt() - 1; // gör -1 så att det passar med arrayens index utifrån valet man gör.
            if (carChoice < 0 || carChoice > carList.size()) {
                System.out.println("Felaktig inmatning bilen finns inte med i registret, välj mellan 1-" + carList.size());
                return; // kollar om man har angett en siffra som ligger utanför arrayen.
            }
            printOwners(owners); //Skriver ut ägare för att visa vilka ägare man kan välja mellan.
            System.out.print("Välj en ägare att koppla (1-" + owners.size() + "): ");
            int ownerChoice;
            ownerChoice = scan.nextInt() - 1; // -1 så att det passar med arryaens index utifrån valet man gör.
            if (ownerChoice < 0 || ownerChoice > owners.size()) {
                System.out.println("Felaktig inmatning ägaren finns inte med i registret, välj mellan 1-" + owners.size());
                return; // kollar om man har angett en siffra som ligger utanför arrayen
            }

            carList.get(carChoice).setOwner(owners.get(ownerChoice)); //Kopplar ihop bil med ägare.
            System.out.println("Ägare " + (ownerChoice + 1) + " kopplad till bil " + (carChoice + 1));
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println("Felaktig inmatning prova igen");
        }
    }
    //Getters för att få access till ägarnas information.
    public String getForename() {
        return forename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }
}
