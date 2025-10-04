import java.util.Scanner;

public class TicketBooking {
    public static void main(String[] args) {
        TicketSystem ticket = new TicketSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Railway Booking System =====");
            System.out.println("1. Book ticket");
            System.out.println("2. Cancel ticket");
            System.out.println("3. View confirmed tickets");
            System.out.println("4. View available tickets");
            System.out.println("5. View RAC tickets");
            System.out.println("6. View waiting list tickets");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // consume leftover newline

                    System.out.print("Enter gender: ");
                    String gender = scanner.nextLine();

                    System.out.print("Enter berth preference (L/M/U): ");
                    String berthPreference = scanner.nextLine();

                    ticket.bookTicket(name, age, gender, berthPreference);
                    break;

                case 2:
                    System.out.print("Enter Ticket ID to cancel: ");
                    String ticketId = scanner.nextLine();
                    ticket.cancelTicket(ticketId);
                    break;

                case 3:
                    ticket.printBookedTickets();
                    break;

                case 4:
                    ticket.printAvailableTickets();
                    break;

                case 5:
                    ticket.printRACTickets();
                    break;

                case 6:
                    ticket.printWaitTickets();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
