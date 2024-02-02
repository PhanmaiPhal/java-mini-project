
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int bookingIndex;
    static String [][] bookingHistory = new String[20][20];;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean isBoolean = true;
        int rows = 0;
        int cols = 0 ;
        do {
            System.out.print("Config total rows in hall: ");
            String rowsInput = scanner.nextLine();
            if (isValidNumber(rowsInput)) {
                rows = Integer.parseInt(rowsInput);
                isBoolean = false;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for rows.");
            }
        } while (isBoolean);
        do {
            System.out.print("Config total rows in hall: ");
            String colsInput = scanner.nextLine();
            if (isValidNumber(colsInput)) {
                cols = Integer.parseInt(colsInput);
                isBoolean = false;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for rows.");
            }
        } while (isBoolean);
        String[][] hall1 = new String[rows][cols];
        String[][] hall2 = new String[rows][cols];
        String[][] hall3 = new String[rows][cols];
        initCode(hall1, hall2, hall3);
    }
    public static void hallA(String[][] array) {
        char seat = 65;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null)
                    System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + "::AV|\t");
                else
                    System.out.print("|" + (char) (seat + i) +  "-" + (j + 1) +"::" + array[i][j] + "|\t");
            }
            System.out.println();
        }
    }
    public static void hallB(String[][] array){
        char seat = 65;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null)
                    System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + "::AV|\t");
                else
                    System.out.print("|" + (char) (seat + i) +  "-" + (j + 1) +"::" + array[i][j] + "|\t");
            }
            System.out.println();
        }
    }
    public static void hallC(String[][] array){
        char seat = 65;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == null)
                    System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + "::AV|\t");
                else
                    System.out.print("|" + (char) (seat + i) +  "-" + (j + 1) +"::" + array[i][j] + "|\t");
            }
            System.out.println();
        }
    }
    /*Utility*/
    private static void showtime () {
        System.out.println("# Daily Showtime of CSTAD Hall:");
        System.out.println("# A) " + "Morning (10:00AM - 12:30PM)");
        System.out.println("# B) " + "Afternoon (3:00AM - 5:30PM)");
        System.out.println("# C) " + "Night (7:00AM - 9:30PM)");
    }
    private static void welcomeMenu(){
        System.out.println("-+".repeat(25));
        System.out.println("CSTAD HALL BOOKING");
        System.out.println("-+".repeat(25));
    }
    private static String menu (Scanner scanner){
        String userInput;
        do {
            System.out.println("[[ Application Menu ]]");
            System.out.println("<A> Booking");
            System.out.println("<B> Hall");
            System.out.println("<C> Showtime");
            System.out.println("<D> Reboot");
            System.out.println("<E> History");
            System.out.println("<F> Exit");
            System.out.print("> Please select menu no:");
            userInput = scanner.nextLine();
            if(isValidOptionInput(userInput)) {
                return  userInput;
            } else {
                System.out.println("Invalid input. Please enter a valid Option.");
            }
        }while(true);

    }
    private static void booking (){
        System.out.println("-+".repeat(25));
        System.out.println("# Start booking process");
        System.out.println("-+".repeat(25));
        System.out.println("# Showtime Information");
        System.out.println("# A) " + "Morning (10:00AM - 12:30PM)");
        System.out.println("# B) " + "Afternoon (3:00AM - 5:30PM)");
        System.out.println("# C) " + "Night (7:00AM - 9:30PM)");
        System.out.println("-+".repeat(25));
    }
    private static String hallMenu(Scanner scanner) {
        String userInput;
        do {
            System.out.println("# INSTRUCTION");
            System.out.println("# Single: C-1");
            System.out.println("# Multiple (separate by comma) : C-1, C-2");
            System.out.print("> Please select available seat:");
            userInput = scanner.nextLine();
            if(isValidSeatInput(userInput)) {
                return  userInput;
            } else {
                System.out.println("There No Seat [" + userInput + "] (Please Input Again)" );
            }
        } while (!isValidSeatInput(userInput));
        return userInput;
    }
    private static void bookSeat(Scanner scanner, String[][] array, String showtime) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        String userInput = hallMenu(scanner);
        String[] seatEntries = userInput.split(",");
        System.out.print("> Please enter Student ID :");
        String userID = scanner.nextLine();
        System.out.println("-+".repeat(25));
        System.out.print("> Are You sure to book? (Y/n)");
        String userOp = scanner.nextLine();
        Matcher matcher2 = pattern.matcher(userOp);
        if(matcher2.matches() && userOp.equalsIgnoreCase("Y")) {
            for (String seatEntry : seatEntries) {
                String[] splitInput = seatEntry.split("-");
                int rowIndex = splitInput[0].charAt(0) - 'A';
                int colIndex = Integer.parseInt(splitInput[1]) - 1;
                if (array[rowIndex][colIndex] == null) {
                    array[rowIndex][colIndex] = "BO";
                    System.out.println("# [" + seatEntry.toUpperCase() + "] booked successfully");
                    recordBookingHistory( seatEntry, userID, showtime);
                } else {
                    System.out.println("# [" + seatEntry.toUpperCase() + "] is already booked.");
                }
            }
        } else if(userOp.equalsIgnoreCase("N")) {
            System.out.println("Booking is Cancel");
        } else {
            System.out.println("Please Choose Option (Y/n)");
        }
    }
    private static boolean isValidNumber(String input) {
        String regex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    private static boolean isValidSeatInput(String input) {
        String regex = "^[A-Za-z]-[0-9]+(,[A-Za-z]-[0-9]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    private static boolean isValidOptionInput (String input) {
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    private static void initCode(String[][] hall1, String[][] hall2, String[][] hall3){
        while(true) {
            do{
                welcomeMenu();
                String userDesire = menu(scanner);
                switch (userDesire.toLowerCase()) {
                    case "a" -> {
                        booking();
                        boolean  isBoolean = true;
                        do{
                            System.out.print("> Please select show time (A | B | C) : ");
                            String userInputHall = scanner.nextLine();
                            if(isValidOptionInput(userInputHall)){
                                switch (userInputHall.toLowerCase()){
                                    case "a" -> {
                                        System.out.println("# HALL A");
                                        hallA(hall1);
                                        bookSeat(scanner, hall1, "Morning");
                                        isBoolean = false;
                                    }
                                    case "b" -> {
                                        System.out.println("# HALL B");
                                        hallB(hall2);
                                        bookSeat(scanner, hall2, "Afternoon");
                                        isBoolean = false;
                                    }
                                    case "c" -> {
                                        System.out.println("# HALL C");
                                        hallC(hall3);
                                        bookSeat(scanner, hall3, "Evening");
                                        isBoolean = false;
                                    }
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a valid Option.");
                            }
                        } while (isBoolean);
                    }
                    case "b" -> {
                        System.out.println("-+".repeat(25));
                        System.out.println("# Hall Information");
                        System.out.println("-+".repeat(25));
                        System.out.println("# Hall - Morning ");
                        hallA(hall1);
                        System.out.println("-+".repeat(25));
                        System.out.println("# Hall - Afternoon ");
                        hallB(hall2);
                        System.out.println("-+".repeat(25));
                        System.out.println("# Hall - Evening");
                        hallC(hall3);
                    }
                    case "c" -> showtime();
                    case "d" -> {
                        for(int i = 0; i < hall1.length ; i++) {
                            for(int j = 0; j < hall1[i].length; j++) {
                                hall1[i][j] = null;
                                hall2[i][j] = null;
                                hall3[i][j] = null;
                            }
                        }
                    }
                    case "e" -> {
                        viewBookingHistory();
                    }
                    case "f" -> System.exit(0);
                }
            }while(true);
        }
    }
    private static void viewBookingHistory() {
        System.out.println("\n--- Booking History ---");
        // Display booking history
        System.out.println("Hall\tSeat No\tStudent ID\tDate\t\tTime");
        for (int i = 0; i < bookingIndex; i++) {
            if (bookingHistory[i].length >= 4) {
                System.out.println(bookingHistory[i][0] + "\t\t" + bookingHistory[i][1] + "\t\t" +
                        bookingHistory[i][2] + "\t\t" + bookingHistory[i][3]);
            } else {
                System.out.println("Invalid booking history entry at index " + i);
            }
        }
    }

    private static void recordBookingHistory(String seatNo, String studentCardID, String showtime) {
        // Check if there is enough space in the array
        if (bookingIndex < bookingHistory.length) {
            // Record booking history
            bookingHistory[bookingIndex++] = new String[]{seatNo, studentCardID, "2024-02-02", showtime};
        } else {
            System.out.println("Booking history array is full. Cannot record new bookings.");
        }
    }



}
