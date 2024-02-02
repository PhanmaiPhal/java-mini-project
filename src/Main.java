
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main  {
    // Constants for showtimes
    private static final String MORNING = "Morning";
    private static final String AFTERNOON = "Afternoon";
    private static final String NIGHT = "Night";

    // Constants for seat status
    private static final String AVAILABLE = "AV";
    private static final String BOOKED = "BO";

    // Array to store hall information
    private static String[][] halls = {
            {"Hall A", MORNING, AVAILABLE, "7:30-9:00","9:30-12:00"},
            {"Hall B", AFTERNOON, AVAILABLE, "12:00-1:30","2:00-4:00"},
            {"Hall C", NIGHT, AVAILABLE, "7:00-8:30","9:30-11:00"}
    };

    // Booking history array
    private static String[][] bookingHistory = new String[10][5];
    private static int bookingIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookHall(scanner);
                    break;
                case 2:
                    checkHallSeats();
                    break;
                case 3:
                    checkShowtimeSchedule();
                    break;
                case 4:
                    viewBookingHistory();
                    break;
                case 5:
                    rebootHall();
                    break;
                case 6:
                    System.out.println("Exiting Hall Booking System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("======================================WELCOME=========================================");
        System.out.println("\n--- Hall Booking System Menu ---");
        System.out.println("1. Book Hall");
        System.out.println("2. Check Hall Seats");
        System.out.println("3. Check Showtime Schedule");
        System.out.println("4. View Booking History");
        System.out.println("5. Reboot Hall");
        System.out.println("6. Exit");
        System.out.println("====================================================================================");
        System.out.print("Enter your choice: ");
    }

    private static void bookHall(Scanner scanner) {
        System.out.println("\n--- Book Hall ---");

        // Input details
        System.out.print("Enter showtime (Morning/Afternoon/Night): ");
        String showtime = scanner.next();

        // Validate showtime using regex
        if (!Pattern.matches("(?i)(Morning|Afternoon|Night)", showtime)) {
            System.out.println("Invalid showtime. Please enter Morning, Afternoon, or Night.");
            return;
        }

        System.out.print("Enter hall capacity: ");
        int capacity = scanner.nextInt();

        // Find available hall
        String[] availableHall = findAvailableHall(showtime, capacity);

        // Display available hall options
        System.out.println("Available Halls:");
        for (int i = 0; i < availableHall.length; i++) {
            System.out.println((i + 1) + ". " + availableHall[i]);
        }

        // Select hall
        System.out.print("Select a hall (1-" + availableHall.length + "): ");
        int hallChoice = scanner.nextInt();
        String selectedHall = availableHall[hallChoice - 1];

        // Mark seats as booked
        markSeatsAsBooked(selectedHall);

        // Input student details
        System.out.print("Enter seat number: ");
        String seatNo = scanner.next();
        System.out.print("Enter student card ID: ");
        String studentCardID = scanner.next();

        // Record booking history
        recordBookingHistory(selectedHall, seatNo, studentCardID);

        System.out.println("Booking successful!");
    }

    private static String[] findAvailableHall(String showtime, int capacity) {
        // Search for available halls based on showtime and capacity
        // For simplicity, assume capacity can be accommodated in any hall with the given showtime
        return new String[]{"Hall A", "Hall B", "Hall C"};
    }

    private static void markSeatsAsBooked(String hallName) {
        // Mark seats in the selected hall as booked
        for (String[] hall : halls) {
            if (hall[0].equals(hallName)) {
                for (int i = 3; i < hall.length; i++) {
                    hall[i] = BOOKED;
                }
            }
        }
    }

    private static void checkHallSeats() {
        System.out.println("\n--- Check Hall Seats ---");

        // Display hall seats
        for (String[] hall : halls) {
            System.out.println("Hall: " + hall[0]);
            for (int i = 3; i < hall.length; i++) {
                System.out.print(hall[i] + " ");
            }
            System.out.println();
        }
    }

    private static void checkShowtimeSchedule() {
        System.out.println("\n--- Showtime Schedule ---");

        // Display showtime schedule
        for (String[] hall : halls) {
            System.out.println("Hall: " + hall[0] + ", Showtime: " + hall[1]);
        }
    }

    private static void viewBookingHistory() {
        System.out.println("\n--- Booking History ---");

        // Display booking history
        System.out.println("Hall\tSeat No\tStudent ID\tDate\t\tTime");
        for (int i = 0; i < bookingIndex; i++) {
            System.out.println(bookingHistory[i][0] + "\t\t" + bookingHistory[i][1] + "\t\t" +
                    bookingHistory[i][2] + "\t\t" + bookingHistory[i][3] + "\t\t" + bookingHistory[i][4]);
        }
    }

    private static void recordBookingHistory(String hallName, String seatNo, String studentCardID) {
        // Record booking history
        bookingHistory[bookingIndex++] = new String[]{hallName, seatNo, studentCardID, "2024-02-02", "Night"};
    }

    private static void rebootHall() {
        System.out.println("\n--- Reboot Hall ---");

        // Input hall name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hall name to reboot: ");
        String hallName = scanner.next();

        // Simulate rebooting process
        System.out.println("Rebooting " + hallName + "...");
        // Assume rebooting takes some time
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Update hall's availability status
        for (String[] hall : halls) {
            if (hall[0].equals(hallName)) {
                hall[2] = AVAILABLE; // Reset availability status
            }
        }

        System.out.println(hallName + " has been successfully rebooted!");
    }
}


