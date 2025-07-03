import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static LinkedList<Client> clients = new LinkedList<>();
    static EventBST eventBST = new EventBST();
    static BookingQueue bookingQueue = new BookingQueue();
    static ExpenseManager expenseManager = new ExpenseManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Event Management System ===");
            System.out.println("1. Add Client\n2. View Clients\n3. Add Event\n4. View Events");
            System.out.println("5. Search Event\n6. Book Event\n7. View Bookings");
            System.out.println("8. Set Budget\n9. Add Expense\n10. View Expenses");
            System.out.println("11. Remove Expense\n12. View Balance\n13. Update Event\n14. Delete Event");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int c = sc.nextInt(); sc.nextLine();  // Consuming the newline character

            switch (c) {
                case 1 -> addClient();
                case 2 -> clients.forEach(System.out::println);
                case 3 -> addEvent();
                case 4 -> eventBST.inorder();  // Assuming inorder() prints all events
                case 5 -> searchEvent();
                case 6 -> bookEvent();
                case 7 -> bookingQueue.listBookings();  // Assuming listBookings() lists all bookings
                case 8 -> {
                    System.out.print("Enter budget: Rs");
                    expenseManager.setBudget(sc.nextDouble()); sc.nextLine();  // Consuming newline
                }
                case 9 -> addExpense();
                case 10 -> expenseManager.viewExpenses();
                case 11 -> removeExpense();
                case 12 -> System.out.println("Balance: Rs" + expenseManager.getBalance());
                case 13 -> updateEvent();
                case 14 -> deleteEvent();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addClient() {
        System.out.print("Enter Client ID: ");
        int id = sc.nextInt(); sc.nextLine();  // Consume newline
        System.out.print("Enter Client Name: ");
        String name = sc.nextLine();
        System.out.print("Phone (10 digits): ");
        String phone = sc.nextLine();

        if (Validator.isValidPhone(phone)) {
            clients.add(new Client(id, name, phone));
            System.out.println("Client added.");
        } else {
            System.out.println("Invalid phone.");
        }
    }

    static void addEvent() {
        System.out.print("Event ID: ");
        int id = sc.nextInt(); sc.nextLine();  // Consume newline
        System.out.print("Event Name: ");
        String name = sc.nextLine();
        System.out.print("Date (yyyy-MM-dd): ");
        String date = sc.nextLine();
        System.out.print("Venue: ");
        String venue = sc.nextLine();

        if (Validator.isFutureDate(date)) {
            eventBST.insert(new Event(id, name, date, venue));
            System.out.println("Event added.");
        } else {
            System.out.println("Invalid date. Must be future.");
        }
    }

    static void updateEvent() {
        System.out.print("Enter Event ID to update: ");
        int eventId = sc.nextInt(); sc.nextLine();  // Consume newline
        Event event = eventBST.search(eventId);
        if (event != null) {
            System.out.println("Updating event: " + event);
            System.out.print("Enter new Event Name: ");
            String name = sc.nextLine();
            System.out.print("Enter new Date (yyyy-MM-dd): ");
            String date = sc.nextLine();
            System.out.print("Enter new Venue: ");
            String venue = sc.nextLine();

            if (Validator.isFutureDate(date)) {
                eventBST.delete(eventId);  // Delete old event
                eventBST.insert(new Event(eventId, name, date, venue));  // Insert updated event
                System.out.println("Event updated.");
            } else {
                System.out.println("Invalid date. Must be future.");
            }
        } else {
            System.out.println("Event not found.");
        }
    }

    static void deleteEvent() {
        System.out.print("Enter Event ID to delete: ");
        int eventId = sc.nextInt(); sc.nextLine();  // Consume newline
        boolean deleted = eventBST.delete(eventId);
        if (deleted) {
            System.out.println("Event deleted.");
        } else {
            System.out.println("Event not found.");
        }
    }

    static void searchEvent() {
        System.out.print("Enter Event ID: ");
        int id = sc.nextInt(); sc.nextLine();  // Consume newline
        Event e = eventBST.search(id);
        if (e != null)
            System.out.println("Found: " + e);
        else
            System.out.println("Event not found.");
    }

    static void bookEvent() {
        System.out.print("Enter Client ID: ");
        int clientId = sc.nextInt(); sc.nextLine();  // Consume newline
        Client client = clients.stream().filter(c -> c.getClientId() == clientId).findFirst().orElse(null);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.print("Enter Event ID: ");
        int eventId = sc.nextInt(); sc.nextLine();  // Consume newline
        Event event = eventBST.search(eventId);
        if (event != null) {
            bookingQueue.addBooking(new Booking(client, event));
            System.out.println("Booked.");
        } else {
            System.out.println("Event not found.");
        }
    }

    static void addExpense() {
        System.out.println("Available categories: " + Arrays.toString(Category.values()));
        System.out.print("Enter category: ");
        String catInput = sc.nextLine().toUpperCase();
        try {
            Category category = Category.valueOf(catInput);
            System.out.print("Amount: Rs");
            double amt = sc.nextDouble(); sc.nextLine();  // Consume newline
            expenseManager.addExpense(category, amt);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category.");
        }
    }

    static void removeExpense() {
        System.out.println("Available categories: " + Arrays.toString(Category.values()));
        System.out.print("Enter category to remove: ");
        String catInput = sc.nextLine().toUpperCase();
        try {
            Category category = Category.valueOf(catInput);
            expenseManager.removeExpense(category);
            System.out.println("Expense removed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category.");
        }
    }
}
