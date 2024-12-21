import java.util.Scanner;

public class w2051686_PlaneManagement {
    // Initiates a 2D array named seats to hold the seating plan
    public static int[][] seats = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    // Initiates a 1D array to store ticket information
    public static Ticket[] tickets_list = new Ticket[56];

    public static void main(String[] args) {
        // Main program that takes inputs and assigns the relevant methods
        boolean done = false;
        while (!done) {
            // Here we use a boolean function to run the program again and again when we give an invalid input
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Welcome to the Plane Management application!");
                for (int i = 0; i < 45; i++) {
                    System.out.print("*");
                }
                System.out.println();
                System.out.println("*                  MENU OPTION              *");
                for (int i = 0; i < 45; i++) {
                    System.out.print("*");
                }
                System.out.println();
                int preference;
                do {
                    System.out.println("   1) Buy a seat");
                    System.out.println("   2) Cancel a seat");
                    System.out.println("   3) Find first available seat");
                    System.out.println("   4) Show seating plan");
                    System.out.println("   5) Print ticket information and total sales");
                    System.out.println("   6) Search ticket");
                    System.out.println("   0) Quit");
                    System.out.println("Enter the number for the preferred option: ");
                    preference = sc.nextInt();
                    switch (preference) {
                        case 0:
                            System.out.println("Exiting program");
                            done = true;
                            break;
                        case 1:
                            buySeat();
                            break;
                        case 2:
                            cancelSeat();
                            break;
                        case 3:
                            findFirstAvailable();
                            break;
                        case 4:
                            printSeats();
                            break;
                        case 5:
                            printTicketArray();
                            break;
                        case 6:
                            search_tickets();
                            break;
                        default:
                            System.out.println("Invalid option. Please select again.");
                            done = true;
                    }
                } while (preference != 0 && !done);
            } catch (Exception e) {
                System.out.println("Enter an integer");
            }
        }
    }

    public static void buySeat() {
        // This method takes in row and column from the user and books the seat.
        Scanner sc = new Scanner(System.in);
        int row = getRowLetter(); // Uses the getRowLetter method
        int column = getColumnNumber(); // Uses the getColumnNumber method
        if (row == -1 || column == -1) {
            System.out.println("Invalid input");
            return;
        }
        if (column >= 1 && column <= seats[row].length) {
            if (seats[row][column - 1] == 0) {
                seats[row][column - 1] = 1; // Changes the seat array according to whether it's booked or not
                System.out.println("What's your name:");
                String name = sc.nextLine();
                System.out.println("What's your surname:");
                String surname = sc.nextLine();
                System.out.println("What's your email:");
                String email = sc.nextLine();
                double price;
                if (column <= 5) {
                    price = 200;
                } else if (column >= 6 && column <= 9) {
                    price = 150;
                } else {
                    price = 180;
                }
                Person person = new Person(name, surname, email); // Creates a Person object
                Ticket ticket = new Ticket(row, column - 1, price, person); // Creates a Ticket object including person details
                tickets_list[row * seats[row].length + column - 1] = ticket; // Stores the ticket data by the Ticket object to the ticket array
                ticket.save(); // Displays the Ticket object
                System.out.println("Seat booked successfully!");
            } else {
                System.out.println("Seat is already booked.");
            }
        } else {
            System.out.println("Invalid column number.");
        }
    }

    public static void printSeats() {
        // This method prints out the seats in the plane
        char[][] displaySeats = new char[seats.length][]; // Creates a duplicate of the seats array

        for (int i = 0; i < seats.length; i++) {
            displaySeats[i] = new char[seats[i].length]; // Defines the columns
        }
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    displaySeats[i][j] = 'O';
                } else if (seats[i][j] == 1) {
                    displaySeats[i][j] = 'X';
                } // Prints "O" instead of "0" and "X" instead of "1"
            }
        }
        for (int i = 0; i < displaySeats.length; i++) {
            for (int j = 0; j < displaySeats[i].length; j++) {
                System.out.print(displaySeats[i][j] + " ");
            }
            System.out.println();
        } // Iterates through the array to print the seating plan
    }

    public static void cancelSeat() {
        // This method cancels the bought seats
        int row = getRowLetter();
        int column = getColumnNumber();
        if (row == -1 || column == -1) {
            System.out.println("Invalid input");
            return;
        }
        if (column >= 1 && column <= seats[row].length) {
            if (seats[row][column - 1] == 1) {
                seats[row][column - 1] = 0; // Makes available a booked seat by changing the "1" in the seats array to "0"
                tickets_list[row * seats[row].length + column - 1] = null; // Removes the ticket information from the ticket array and assigns null to it
                System.out.println("Seat canceled successfully!");
            } else {
                System.out.println("Seat is already free");
            }
        } else {
            System.out.println("Invalid column number.");
        }
    }

    public static int getRowLetter() {
        Scanner sc = new Scanner(System.in);
        char rowLetter;
        while (true) {
            System.out.println("Enter row letter (A, B, C, D): ");
            String input = sc.next().toUpperCase();
            if (input.length() == 1) {
                rowLetter = input.charAt(0);
                if (rowLetter >= 'A' && rowLetter <= 'D') {
                    break;
                }
            }
            System.out.println("Invalid input. Please enter a valid row letter");
        }
        int row;
        switch (rowLetter) {
            case 'A':
                row = 0;
                break;
            case 'B':
                row = 1;
                break;
            case 'C':
                row = 2;
                break;
            case 'D':
                row = 3;
                break;
            default:
                row = -1;
        }
        return row;
    }

    public static int getColumnNumber() {
        //this takes in the colomn number form the user
        Scanner sc = new Scanner(System.in);
        int column;
        while (true) {
            System.out.println("Enter column number (1 to 14): ");
            if (sc.hasNextInt()) {
                column = sc.nextInt();
                if (column >= 1 && column <= 14) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 14.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 14.");
                sc.next(); // Consume the invalid input
            }
        }
        return column;
    }

    public static void findFirstAvailable() {
        for (int row = 0; row < seats.length; row++) {
            for (int column = 0; column < seats[row].length; column++) {
                if (seats[row][column] == 0) {
                    char rowLetter = (char) ('A' + row); // Convert row to letter
                    System.out.println("Available seat found at Row: " + rowLetter + (column + 1)); // Increment column by 1 to make it 1-indexed
                    return;
                }
            }
        }
        System.out.println("No available seats found.");
    }


    public static void printTicketArray() {
        //this will print out the ticket info including the person info after booking a seat
        double totalSales = 0.0;
        System.out.println("Ticket Information:");
        for (Ticket ticket : tickets_list) {//this goes through the ticket array and prints out the ticke info to the console
            if (ticket != null) {
                ticket.printTicketInfo();
                totalSales += ticket.getPrice();//this adds the total sale for the session
                System.out.println("-------------------");
            }
        }
        System.out.println("Total Sales: $" + totalSales);//prints out the total sales
    }

    public static void search_tickets() {
        //this method shows the ticket info of the given seat number
        getRowLetter();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter column number: ");
        int column = sc.nextInt() - 1;
        if (column >= 0 && column < tickets_list.length) {
            Ticket ticket = tickets_list[column];
            if (ticket == null) {
                System.out.println("Seat available");
            } else {
                ticket.printTicketInfo();
            }//this goes through the ticket array and finds the relavent ticket info
        } else {
            System.out.println("Invalid column number.");
        }
    }
}
