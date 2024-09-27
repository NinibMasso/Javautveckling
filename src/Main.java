import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int menuChoice = 0;
        ArrayList<CarRegister> carList = new ArrayList<>(); //Skapar en lista för bilar
        ArrayList<Owner> owners = new ArrayList<>();        //Skapar en lista för ägare
        CarRegister carRegister = new CarRegister();        //Skapar en instans av klassen
        Owner owner = new Owner();                          //Skapar en instans av klassen

        // En loop som fortsätter tills användaren väljer att avsluta genom att ange siffran 7.
        while (menuChoice != 7) {
            System.out.print("""
                    (1) Lägg till en ny bil\s
                    (2) Lägg till en ny ägare\s
                    (3) Koppla en bil till ägare
                    (4) Visa bilar\s
                    (5) Visa ägare\s
                    (6) Radera bil\s
                    (7) Avsluta
                    """);
            try {
                menuChoice = scan.nextInt(); // läser in menyval

                carRegister.checkMenuChoice(menuChoice); //kontrollerar att menyvalet är mellan 1-7.

                switch (menuChoice) {
                    case 1 -> { //Lägger till en ny bil och lägger den i bilens lista.
                        CarRegister newCar = carRegister.createCar();
                        if (newCar != null) { // om man skulle råka ange fel värden på bilen så att returnering av bilen
                            carList.add(newCar); //inte går som det ska vill vi inte att den felaktiga bilen ska
                        }                        // läggas in i listan.
                    }
                    case 2 -> { // Lägger till en ny ägare och lägger den i ägarens lista.
                        Owner newCarOwner = owner.createOwner();
                        if (newCarOwner != null) { // om man skulle råka ange fel värden på ägaren så vill vi inte att
                            owners.add(newCarOwner); // så att returnering av ägaren inte går som det ska vill vi inte
                        }                              // att den felaktiga ägaren ska läggas in i listan.
                    }
                    case 3 -> owner.connect(carList, owners); //Kopplar en bil till en ägare
                    case 4 -> carRegister.printCars(carList); //Skriver ut informationen om bilarna som finns i listan
                    case 5 -> owner.printOwners(owners); // Skriver ut information om ägarna som finns i listan
                    case 6 -> carRegister.deleteCar(carList); // Raderar en bil från listan.
                    case 7 -> System.out.println("Avslutar programmet");
                }
            } catch (InputMismatchException | NullPointerException e) { //Felhantering
                scan.next();// Denna har jag med ifall att användaren råkar skriva en bokstav istället för siffa
                // i menyvalet då hamnar jag i en "endless-loop". Denna scannar in de "felaktiga valet"
                // så att nästa inscanning görs korrekt.
                System.out.println("Felaktig inmatning prova igen");
            }
        }

    }

    /*
    Uppgiften var rolig att utföra men samtidigt svår. Jag hade en bild i mitt huvud på vad jag ville göra, men det
    kändes som att jag grävde mig ner i en grop ju längre jag kom in i uppgiften och inte kunde ta mig upp ur gropen.
    Det var utmanande att få allt och fungera samtidigt som det också var lite otydligt hur pass mycket felhantering som
    skulle ingå i uppgiften. Jag har programmerat lite innan i Cprogram men jag lärde mig väldigt mycket med hur syntaxen
    och hur saker o ting fungerar i java för att kunna anropa en metod från en annan fil samtidigt som det ska vara säkra
    och smarta objekt genom att instansiera. Däremot finns det inget bättre än att få den satisfactionen när man får
    allt att fungera. En väldigt bra uppgift och kul att man fick komma på lite själv vad man ville göra.
     */
}

