package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        char[][] cinemaRoom = createCinemaRoom(numRows, seatsInRow);
        processAction(cinemaRoom);
    }

    static char[][] createCinemaRoom(int rows, int seats) {
        char[][] room = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                room[i][j] = 'S';
            }
        }
        return room;
    }

    static void updateRoom(char[][] room, int row, int seat) {
        room[row - 1][seat - 1] = 'B';
    }

    static void showRoom(char[][] room) {
        System.out.println("Cinema:");
        System.out.print(" ");
        int rows = room.length;
        int seats = room[0].length;

        // header
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        System.out.print("\n");

        // rows
        for (int i = 1; i <= rows; i++) {
            System.out.print(i);
            for (int j = 1; j <= seats; j++) {
                System.out.print(" " + room[i - 1][j - 1]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void buy(char[][] room) {
        Scanner scanner = new Scanner(System.in);
        int numRows = room.length;
        int seatsInRow = room[0].length;
        int totalSeats = numRows * seatsInRow;
        int price;
        while (true) {
            System.out.println("Enter a row number:");
            int selectedRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int selectedSeat = scanner.nextInt();
            if (selectedRow < 0 || selectedRow > numRows || selectedSeat < 0 || selectedSeat > seatsInRow) {
                System.out.println("Wrong input!");
            } else if (room[selectedRow - 1][selectedSeat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                if (totalSeats <= 60) {
                    price = 10;
                } else {
                    int firstHalf = numRows / 2;
                    if (selectedRow <= firstHalf) {
                        price = 10;
                    } else {
                        price = 8;
                    }
                }
                System.out.println("Ticket price: $" + price);
                updateRoom(room, selectedRow, selectedSeat);
                break;
            }


        }

    }

    static int countPurchased(char[][] room) {
        int count = 0;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (room[i][j] == 'B') {
                    count++;
                }
            }
        }
        return count;
    }

    static int currentIncome(char[][] room) {
        int numRows = room.length;
        int seatsInRow = room[0].length;
        int totalSeats = numRows * seatsInRow;
        int firstHalf = numRows / 2;
        int income = 0;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (room[i][j] == 'B') {
                    if (totalSeats <= 60) {
                        income += 10;
                    } else {
                        if (i + 1 <= firstHalf) {
                            income += 10;
                        } else {
                            income += 8;
                        }
                    }
                }
            }

        }

        return income;
    }

    static int maxIncome(char[][] room) {
        int numRows = room.length;
        int seatsInRow = room[0].length;
        int totalSeats = numRows * seatsInRow;
        int firstHalf = numRows / 2;
        if (totalSeats <= 60) {
            return totalSeats * 60;
        } else {
            return firstHalf * seatsInRow * 10 + (numRows - firstHalf) * seatsInRow * 8;
        }

    }

    static void statistics(char[][] room) {
        int numRows = room.length;
        int seatsInRow = room[0].length;
        int totalSeats = numRows * seatsInRow;
        int purchased = countPurchased(room);
        System.out.printf("Number of purchased tickets: %d%n", purchased);
        System.out.printf("Percentage: %.2f", (float) purchased / totalSeats * 100);
        System.out.println("%");
        System.out.printf("Current income: $%d%n", currentIncome(room));
        System.out.printf("Total income: $%d%n", maxIncome(room));

    }


    static void showMenu() {
        String menu = """
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""";
        System.out.println();
        System.out.println(menu);
    }

    static void processAction(char[][] room) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            int action = scanner.nextInt();
            System.out.println();
            switch (action) {
                case 1:
                    showRoom(room);
                    break;
                case 2:
                    buy(room);
                    break;
                case 3:
                    statistics(room);
            }
            if (action == 0) {
                break;
            }
        }
    }
}