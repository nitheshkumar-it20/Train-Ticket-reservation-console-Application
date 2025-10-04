import java.util.*;

public class TicketSystem {

    private final List<String> availableBerths = new ArrayList<>(Arrays.asList("L", "M", "U"));
    private final Queue<Passenger> RACQueue = new LinkedList<>();
    private final Queue<Passenger> waitingList = new LinkedList<>();
    private final List<Passenger> confirmedPassengers = new ArrayList<>();

    private static final int RAC_LIMIT = 2;
    private static final int WAIT_LIMIT = 2;

    private int ticketCounter = 1;

    // Booking tickets
    public void bookTicket(String name, int age, String gender, String berthPreference) {
        String ticketId = "T" + ticketCounter++;
        Passenger pass;

        if (!availableBerths.isEmpty()) {
            String allocateBerth = allocBerth(age, gender, berthPreference);
            pass = new Passenger(name, age, gender, berthPreference, allocateBerth, ticketId);
            confirmedPassengers.add(pass);
            availableBerths.remove(allocateBerth);
            System.out.println("✅ Ticket Confirmed: " + pass);
        } else if (RACQueue.size() < RAC_LIMIT) {
            pass = new Passenger(name, age, gender, berthPreference, "RAC", ticketId);
            RACQueue.offer(pass);
            System.out.println("🟡 Ticket in RAC: " + pass);
        } else if (waitingList.size() < WAIT_LIMIT) {
            pass = new Passenger(name, age, gender, berthPreference, "Waiting List", ticketId);
            waitingList.offer(pass);
            System.out.println("⚪ Ticket in Waiting List: " + pass);
        } else {
            System.out.println("❌ No tickets available");
        }
    }

    // Allocating berth function
    private String allocBerth(int age, String gender, String preference) {
        if (age > 60 || (gender.equalsIgnoreCase("female") && availableBerths.contains("L"))) {
            return "L";
        }
        if (availableBerths.contains(preference)) {
            return preference;
        }
        return availableBerths.get(0);
    }

    // Canceling ticket
    public void cancelTicket(String ticketId) {
        Optional<Passenger> passOpt = confirmedPassengers.stream()
                .filter(p -> p.ticketid.equals(ticketId))
                .findFirst();

        if (passOpt.isPresent()) {
            Passenger pass = passOpt.get();
            confirmedPassengers.remove(pass);

            // Free the berth
            availableBerths.add(pass.allotedberth);
            System.out.println("❌ Ticket cancelled successfully: " + ticketId);

            // Move RAC to Confirmed
            if (!RACQueue.isEmpty()) {
                Passenger racPass = RACQueue.poll();
                String allocateBerth = allocBerth(racPass.age, racPass.gender, racPass.berthpreference);

                racPass.allotedberth = allocateBerth;
                confirmedPassengers.add(racPass);
                availableBerths.remove(allocateBerth);

                System.out.println("🔄 RAC passenger promoted to Confirmed: " + racPass);
            }

            // Move Waiting List to RAC
            if (!waitingList.isEmpty()) {
                Passenger waitPass = waitingList.poll();
                waitPass.allotedberth = "RAC";
                RACQueue.offer(waitPass);

                System.out.println("🔄 Waiting List passenger promoted to RAC: " + waitPass);
            }

        } else {
            System.out.println("⚠️ Ticket ID not found: " + ticketId);
        }
    }


    // Print confirmed tickets
    public void printBookedTickets() {
        if (confirmedPassengers.isEmpty()) {
            System.out.println("No confirmed tickets.");
        } else {
            System.out.println("✅ Confirmed Tickets:");
            for (Passenger pass : confirmedPassengers) {
                System.out.println(pass);
            }
        }
    }

    // Print available tickets
    public void printAvailableTickets() {
        System.out.println("Available Berths: " + availableBerths.size());
        System.out.println("Available RAC: " + (RAC_LIMIT - RACQueue.size()));
        System.out.println("Available Waiting List: " + (WAIT_LIMIT - waitingList.size()));
    }

    // Print RAC tickets
    public void printRACTickets() {
        if (RACQueue.isEmpty()) {
            System.out.println("No RAC tickets.");
        } else {
            System.out.println("🟡 RAC Tickets:");
            for (Passenger pass : RACQueue) {
                System.out.println(pass);
            }
        }
    }

    // Print Waiting List tickets
    public void printWaitTickets() {
        if (waitingList.isEmpty()) {
            System.out.println("No Waiting List tickets.");
        } else {
            System.out.println("⚪ Waiting List Tickets:");
            for (Passenger pass : waitingList) {
                System.out.println(pass);
            }
        }
    }
}
