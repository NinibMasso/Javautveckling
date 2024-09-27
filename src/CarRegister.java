import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CarRegister {
    private String brand;
    private double value;
    private int year;

    private Owner owner;            //Referens till ägare (en annan klass)

    // Metod för att kontrollera om menyval är giltig (1-7)
    void checkMenuChoice(int menuChoice) {
        if (menuChoice < 1 || menuChoice > 7) {
            System.out.println("Felaktig inmatning, välj mellan 1-7");
        }
    }

    // Metod för att sätta ägaren av bilen vid koppling.
    public void setOwner(Owner owner){
        this.owner = owner;
    }
    // Skapar konstruktor för en bil med dessa egenskaper.
    public CarRegister(String brand, double value, int year) {
        this.brand = brand;
        this.value = value;
        this.year = year;
    }
    // Skapar denna för att kunna skapa en instans i main.
    public CarRegister() {

    }

    // Metod för att kunna skapa en ny bil som tar in värden från användaren och skickar över informationen
    // till konstruktorn som returnerar en ny bil. Felhantering av exempelvis om man skriver in en bosktav
    // istället för siffra då man ska ange värde/år.
    public CarRegister createCar() {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.print("Vilket märke är bilen? ");
            String s = scan.nextLine();
            if (s.isEmpty()){
                System.out.println("Felaktig inmatning, du har inte skrivit vilket märke det är på bilen");
                return null; ////If-sats som kollar att man har matat in något.
            }
            System.out.print("Hur mycket är bilen värd? ");
            double carValue = scan.nextDouble();
            System.out.print("Vilken årsmodell är bilen? ");
            int y = scan.nextInt();
            System.out.println("Bilen tillagd");
            return new CarRegister(s, carValue, y); //returnerar den nya bilen.

        } catch (InputMismatchException e) {
            System.out.println("Felaktig inmatning prova igen");
            return null;
        }
    }

    // Metod för att skriva ut information om alla bilar i arraylist, och skriver även ut ägare till bilen OM och
    // endast om det finns en ägare till en bil.
    public void printCars(ArrayList<CarRegister> carList) {
        if (carList.isEmpty()) {
            System.out.println("Finns inga bilar att skriva ut");
            return; // Skriver ut att det inte finns bilar att skriva ut om listan är tom.
        }
        int carNumber = 1;
        for (CarRegister carRegister : carList) {
            System.out.println("Bil " + carNumber + ":" + "\tMärke: " + carRegister.brand + "\t\tVärde: " +
                    String.format("%.0f", carRegister.value) + "\t\tÅrsmodell: " + carRegister.year);
            if (carRegister.owner != null){
                System.out.println("\tÄgare: " + carRegister.owner.getForename() + " " + carRegister.owner.getLastname()
                + "\tStad: " + carRegister.owner.getCity() + "\tÅlder: " + carRegister.owner.getAge());
            }
            carNumber++;
        }
    }

    //Metod för att radera en bil från arraylist av bilar. Måste säga att det var mycket lättare att
    //skapa denna metod i Java med simpla carList.remove än vad det är i Cprogram, då man var tvungen
    // att "putta" ut det indexet man ville radera så att det hamna längst bak i listan sen göra (hela listan)-1.
    //Det kanske är det .remove funktionen gör, jag förstod inte riktigt när ja försökte kika in i metoden.
    public void deleteCar(ArrayList<CarRegister> carList) {
        Scanner scan = new Scanner(System.in);

        if (carList.isEmpty()) {
            System.out.println("Finns inga bilar att radera");
            return;
        }
        printCars(carList);
        System.out.print("Vilken bil vill du radera (1-" + carList.size() + ")? ");
        int carListNumber;
        try {
            carListNumber = Integer.parseInt(scan.next());
        } catch (NumberFormatException e) {
            System.out.println("Felaktig inmatning. Ange ett heltal"); // Felhantering om man skriver in något annat
            return;                                                     // än en siffra
        }
        if (carListNumber <= 0 || carListNumber > carList.size()) {
            System.out.println("Felaktig inmatning bilen finns inte med i registret, välj mellan 1-" + carList.size());
            return;
        } //Felhantering om man råkar ange en siffra som är utanför listan av bilar.

        carList.remove(carListNumber - 1);
        System.out.println("Bil " + carListNumber + " raderad");
    }


}
