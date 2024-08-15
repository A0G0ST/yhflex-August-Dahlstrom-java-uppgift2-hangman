import java.util.Scanner;
import java.util.Arrays;

public class Hangman {
    // Maximalt antal gissningar
    public static final int MAX_GUESSES = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Exempelord att gissa
        String hemligtOrd = "java";
        char[] ordArray = new char[hemligtOrd.length()];
        Arrays.fill(ordArray, '_');
        int felaktigaGissningar = 0;
        boolean speletÄrÖver = false;

        // Loopa tills spelet är över
        while (!speletÄrÖver) {
            // Visa meny
            System.out.println("\nVälj ett alternativ:");
            System.out.println("1. Visa gissningar och gissningar kvar");
            System.out.println("2. Gissa en bokstav");
            System.out.println("3. Gissa hela ordet");
            System.out.println("4. Avsluta");
            String val = scanner.nextLine();

            switch (val) {
                case "1":
                    visaStatus(ordArray, felaktigaGissningar);
                    break;
                case "2":
                    System.out.println("Gissa en bokstav:");
                    char gissadBokstav = scanner.nextLine().toLowerCase().charAt(0);
                    if (!gissaBokstav(hemligtOrd, ordArray, gissadBokstav)) {
                        felaktigaGissningar++;
                    }
                    break;
                case "3":
                    System.out.println("Gissa hela ordet:");
                    String gissadOrd = scanner.nextLine().toLowerCase();
                    if (gissadOrd.equals(hemligtOrd)) {
                        System.out.println("Grattis! Du gissade rätt ord.");
                        speletÄrÖver = true;
                    } else {
                        System.out.println("Fel!");
                        felaktigaGissningar++;
                    }
                    break;
                case "4":
                    System.out.println("Spelet avslutas.");
                    speletÄrÖver = true;
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
                    break;
            }

            // Kontrollera om spelaren har vunnit eller förlorat
            if (Arrays.equals(ordArray, hemligtOrd.toCharArray())) {
                System.out.println("Grattis! Ordet var: " + hemligtOrd);
                speletÄrÖver = true;
            } else if (felaktigaGissningar >= MAX_GUESSES) {
                System.out.println("Tyvärr, du har förlorat! Ordet var: " + hemligtOrd);
                speletÄrÖver = true;
            }

            // Rita gubben efter varje felaktig gissning
            ritaGubbe(felaktigaGissningar);
        }

        scanner.close();
    }

    // Funktion för att visa status
    public static void visaStatus(char[] ordArray, int felaktigaGissningar) {
        System.out.println("Nuvarande status: " + String.valueOf(ordArray));
        System.out.println("Antal felaktiga gissningar: " + felaktigaGissningar + "/" + MAX_GUESSES);
    }

    // Funktion för att hantera gissningar av enskilda bokstäver
    public static boolean gissaBokstav(String hemligtOrd, char[] ordArray, char gissadBokstav) {
        boolean korrektGissning = false;
        for (int i = 0; i < hemligtOrd.length(); i++) {
            if (hemligtOrd.charAt(i) == gissadBokstav) {
                ordArray[i] = gissadBokstav;
                korrektGissning = true;
            }
        }
        if (!korrektGissning) {
            System.out.println("Fel gissning!");
        }
        return korrektGissning;
    }

    // Funktion för att rita gubben beroende på antal felaktiga gissningar
    public static void ritaGubbe(int felaktigaGissningar) {
        switch (felaktigaGissningar) {
            case 0:
                System.out.println("____\n|   |\n|\n|\n|\n|\n/|\\");
                break;
            case 1:
                System.out.println("____\n|   |\n|   o\n|\n|\n|\n/|\\");
                break;
            case 2:
                System.out.println("____\n|   |\n|   o\n|   |\n|\n|\n/|\\");
                break;
            case 3:
                System.out.println("____\n|   |\n|   o\n|  /|\n|\n|\n/|\\");
                break;
            case 4:
                System.out.println("____\n|   |\n|   o\n|  /|\\\n|\n|\n/|\\");
                break;
            case 5:
                System.out.println("____\n|   |\n|   o\n|  /|\\\n|  /\n|\n/|\\");
                break;
            case 6:
                System.out.println("____\n|   |\n|   o\n|  /|\\\n|  / \\\n|\n/|\\");
                break;
        }
    }
}
