import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class InputHelper {
    public static void main(String[] args) {
        
    }

    // this method loops until a valid integer input is received. Returns int
    public static int getInt(Scanner in, String prompt) {
        boolean done = false;
        int x = 0;

        System.out.println(prompt);
        do {
            if (in.hasNextInt()) {
                x = in.nextInt();
                done = true;
            } else {
                System.out.println("Error: Not an integer");
            }
            in.nextLine(); //clear buffer
        } while (!done);
        return x;
    }

    public static int getRangedInt(Scanner in, String prompt, int min, int max) {
        boolean done = false;
        int x = 0;

        System.out.println(prompt);
        do {
            if (in.hasNextInt()) {
                x = in.nextInt();
                //checks for value in range
                if (x <= max && x >= min) {
                    done = true;
                } else {
                    System.out.println("Error blah");
                }
            } else {
                System.out.println("Error");
            }
            in.nextLine(); //clear buffer
        } while (!done);
        return x;
    }

    public static String getRegExString(Scanner in, String prompt, String regEx) {
        String input;
        boolean valid = false;

        do {
            System.out.println(prompt);
            input = in.nextLine();
            if (input.matches(regEx)) {
                valid = true;
            } else {
                System.out.println("Does not match");
            }
        } while (!valid);

        return input;
    }

    public static String getNonZeroLenString(Scanner in, String prompt) {
        boolean done = false;
        String word;
        System.out.println(prompt);
        do {
            word = in.nextLine();
            if (word.length() > 0) {
                done = true;
            } else {
                System.out.println("Error: Zero length string");
            }
        } while (!done);

        return word;
    }

    public static double getDouble(Scanner in, String prompt) {
        boolean done = false;
        double x = 0;

        do {
            System.out.println(prompt);
            if (in.hasNextDouble()) {
                x = in.nextDouble();
                done = true;
            } else {
                System.out.println("Error: Not a double");
            }
            in.nextLine();
        } while (!done);
        return x;
    }

    public static double getRangedDouble(Scanner in, String prompt, double min, double max) {
        boolean done = false;
        double x = 0;

        System.out.println(prompt);
        do {
            if (in.hasNextInt()) {
                x = in.nextInt();
                //checks for value in range
                if (x <= max && x >= min) {
                    done = true;
                } else {
                    System.out.println("Error: Out of range");
                }
            } else {
                System.out.println("Error: Not an integer");
            }
            in.nextLine(); //clear buffer
        } while (!done);
        return x;
    }

    public static int getPositiveNonZeroInt(Scanner in, String prompt) {
        boolean done = false;
        int x = 0;

        System.out.println(prompt);
        do {
            if (in.hasNextInt()) {
                x = in.nextInt();
                //checks for value in range
                if (x > 0) {
                    done = true;
                } else {
                    System.out.println("Error: Enter a positive nonzero integer");
                }
            } else {
                System.out.println("Error: Not an integer");
            }
            in.nextLine(); //clear buffer
        } while (!done);
        return x;
    }

    public static boolean getYNConfirm(Scanner in, String prompt) {
        boolean done = false;
        String YN;
        do {
            System.out.println(prompt);
            YN = in.nextLine();
            YN = YN.toUpperCase();
            if (YN.equals("N") || YN.equals("Y")) {
                done = true;
            } else {
                System.out.println("Error: Invalid");
            }
        } while (!done);
        if (YN.equals("N")) {
            return false;
        } else {
            return true;
        }
    }

    public static void prettyHelper(String message){
        int length = 60;
        int y = length - message.length() + 2;
        for (int i = 0; i <= length; i++) {
            System.out.print("*");
        }
        System.out.println("");
        for (int i = 0; i <= y; i++) {
            if(i == (y/2)){
                System.out.print(message);
            }else if((0 <= i && i <= 2 ) || ((i >= y - 3) && (i < y))){
                System.out.print("*");
            }else{
                System.out.print(" ");
            }
        }
        System.out.println(" ");
        for (int i = 0; i <= length; i++) {
            System.out.print("*");
        }
    }
}