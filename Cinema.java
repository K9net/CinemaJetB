package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        int rows = cinemaRows();
        int seats = cinemaSeats();
        char[][] chars = openCinema(rows, seats);
        int count = 1;
        int purchasedTickets = 0;
        int totalIncome = 0;
        int a;
        while (count != 0) {
            int choice = userChoice();
            switch (choice) {
                case 0:
                    count = 0;
                    break;
                case 1:
                    createCinemaRoom(chars, rows, seats);
                    break;
                case 2:
                    purchasedTickets++;
                    a = calculateTicketPrice(chars, rows, seats);
                    totalIncome += a;
                    break;
                case 3:
                    showStatistics(rows, seats, purchasedTickets, totalIncome);
                    break;
                default:
                    break;
            }
        }
    }

    public static String percentageSoldTicket(int rows, int seats,
                                              int purchasedTickets) {
        double a = rows * seats;
        double onePer = 100 / a;
        double per = onePer * purchasedTickets;
        String result = String.format("Percentage: %.2f", per);
        return result + "%";
    }

    public static int totalIncome(int rows, int seats) {
        int tickets = rows * seats;
        int profit = 0;
        if (tickets < 60) {
            profit = tickets * 10;
        } else if (tickets > 60) {
            int k = rows / 2;
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < seats; j++) {
                    profit += 10;
                }
            }
            for (int i = 0; i < rows - k; i++) {
                for (int j = 0; j < seats; j++) {
                    profit += 8;
                }
            }
        }
        return profit;
    }


    public static void showStatistics(int rows, int seats,
                                      int purchasedTickets, int totalIncome) {
        int profit = totalIncome(rows, seats);
        String percentage = percentageSoldTicket(rows, seats, purchasedTickets);
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println(percentage);
        System.out.println("Current income: $" + totalIncome);
        System.out.println("Total income: $" + profit);
        System.out.println();
    }

    public static int userChoice() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.print("> ");
        int choice = in.nextInt();
        System.out.println();
        return choice;
    }

    public static int cinemaRows() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        return in.nextInt();
    }

    public static int cinemaSeats() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        return in.nextInt();
    }

    public static char[][] openCinema(int rows, int seats) {
        System.out.println();
        rows++;
        seats++;
        char[][] chars = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                chars[i][j] = 'S';
            }
        }
        return chars;
    }

    public static void createCinemaRoom(char[][] chars, int rows, int seats) {
        rows++;
        seats++;
        chars[0][0] = ' ';
        System.out.println("Cinema:");
        for (int i = 0; i < rows; i++) {
            if (i != 0) {
                System.out.print(i + " ");
            }
            for (int j = 0; j < seats; j++) {
                if (i == 0 && j != 0) {
                    System.out.print(j + " ");
                } else if (j < seats - 1) {
                    System.out.print(chars[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int calculateTicketPrice(char[][] chars, int rows,
                                           int seats) {
        boolean a = true;
        int p = rows - 1;
        int q = seats - 1;
        int profit = 0;
        while (a){
            int rowNumber = inputRowNumber();
            int seatNumber = inputSeatNumber();
            if (rowNumber > rows || rowNumber <= 0 ||
                    seatNumber > seats || seatNumber <= 0) {
                System.out.println();
                System.out.println("Wrong input!");
                System.out.println();
            } else if (chars[rowNumber][seatNumber - 1] == 'B') {
                System.out.println();
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            } else {
                chars[rowNumber][seatNumber - 1] = 'B';
                int tickets = p * q;
                if (tickets < 60) {
                    profit = 10;
                    System.out.println();
                    System.out.println("Ticket price: $" + profit);
                } else if (tickets > 60) {
                    if (p / 2 >= rowNumber) {
                        profit = 10;
                    } else {
                        profit = 8;
                    }
                    System.out.println();
                    System.out.println("Ticket price: $" + profit);
                }
                System.out.println();
                a = false;
            }
        }
        return profit;
    }

    public static int inputRowNumber() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a row number:");
        System.out.print("> ");
        return in.nextInt();
    }

    public static int inputSeatNumber() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a seat number in that row:");
        System.out.print("> ");
        return in.nextInt();
    }
}