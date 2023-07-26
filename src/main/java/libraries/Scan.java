package libraries;

import java.util.Locale;
import java.util.Scanner;

/**
 * Classe pour proposer une saisie à l'utilisateur avec verification de la saisie
 */
public class Scan {
    private static Scanner scan = new Scanner(System.in);
    private static Locale locale;

    public static void setLocale(Locale local) {
        locale = local;
        scan.useLocale(locale);
    }

    public static void reload() {
        scan = new Scanner(System.in);
        scan.useLocale(locale);
    }

    public static int getInt() {
        do {
            try {
                System.out.print("\n- ");
                String choice = scan.nextLine();
                System.out.print("\n");
                return Integer.parseInt(choice);
            }
            catch (Exception e) {
                System.out.println("--- Erreur de saisie ! ---");
            }
        } while (true);
    }

    public static double getDouble() {
        do {
            try {
                System.out.print("\n- ");
                String choice = scan.nextLine();
                System.out.print("\n");
                return Double.parseDouble(choice);
            }
            catch (Exception e) {
                System.out.println("--- Erreur de saisie ! ---");
            }
        } while (true);
    }

    public static long getLong() {
        do {
            try {
                System.out.print("\n- ");
                String choice = scan.nextLine();
                System.out.print("\n");
                return Long.parseLong(choice);
            }
            catch (Exception e) {
                System.out.println("--- Erreur de saisie ! ---");
            }
        } while (true);
    }

    public static byte getByte() {
        do {
            try {
                System.out.print("\n- ");
                String choice = scan.nextLine();
                System.out.print("\n");
                return Byte.parseByte(choice);
            }
            catch (Exception e) {
                System.out.println("--- Erreur de saisie ! ---");
            }
        } while (true);
    }

    public static short getShort() {
        do {
            try {
                System.out.print("\n- ");
                String choice = scan.nextLine();
                System.out.print("\n");
                return Short.parseShort(choice);
            }
            catch (Exception e) {
                System.out.println("--- Erreur de saisie ! ---");
            }
        } while (true);
    }

    public static float getFloat() {
        do {
            try {
                System.out.print("\n- ");
                String choice = scan.nextLine();
                System.out.print("\n");
                return Float.parseFloat(choice);
            }
            catch (Exception e) {
                System.out.println("--- Erreur de saisie ! ---");
            }
        } while (true);
    }

    public static char getChar() {
        do {
            try {
                System.out.print("\n- ");
                String choice = scan.nextLine();
                System.out.print("\n");
                return choice.charAt(0);
            }
            catch (Exception e) {
                System.out.println("--- Erreur de saisie ! ---");
            }
        } while (true);
    }

    public static String getString() {
        System.out.print("\n- ");
        String choice = scan.nextLine();
        System.out.print("\n");
        return choice;
    }
}
