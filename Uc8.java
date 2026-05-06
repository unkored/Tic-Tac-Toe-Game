import java.util.Random;
import java.util.Scanner;

public class Uc8 {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        initializeBoard();
        printBoard();

        tossAndAssignSymbols();
        displayTossResult();

        boolean gameOver = false;

        while (!gameOver) {

            if (isHumanTurn) {
                System.out.println("Human's turn");

                int slot = getUserSlot();
                int row = getRowFromSlot(slot);
                int col = getColFromSlot(slot);

                if (isValidMove(row, col)) {
                    placeMove(row, col, humanSymbol);
                    printBoard();

                    isHumanTurn = false; // switch turn
                } else {
                    System.out.println("Invalid move, try again.");
                }

            } else {
                System.out.println("Computer's turn");

                computerMove();
                printBoard();

                isHumanTurn = true; // switch turn
            }

            // Stop condition
            if (isBoardFull()) {
                System.out.println("Game over! It's a draw.");
                gameOver = true;
            }
        }
    }

    // ---------------- BOARD SETUP ----------------

    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    static void printBoard() {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // ---------------- TOSS ----------------

    static void tossAndAssignSymbols() {
        Random random = new Random();
        int toss = random.nextInt(2);

        if (toss == 0) {
            isHumanTurn = true;
            humanSymbol = 'X';
            computerSymbol = 'O';
        } else {
            isHumanTurn = false;
            humanSymbol = 'O';
            computerSymbol = 'X';
        }
    }

    static void displayTossResult() {
        System.out.println("=== Toss Result ===");

        if (isHumanTurn) {
            System.out.println("Human plays first.");
        } else {
            System.out.println("Computer plays first.");
        }

        System.out.println("Human symbol: " + humanSymbol);
        System.out.println("Computer symbol: " + computerSymbol);
    }

    // ---------------- USER INPUT ----------------

    static int getUserSlot() {
        System.out.print("Enter a slot number (1-9): ");
        return scanner.nextInt();
    }

    static int getRowFromSlot(int slot) {
        return (slot - 1) / 3;
    }

    static int getColFromSlot(int slot) {
        return (slot - 1) % 3;
    }

    // ---------------- GAME LOGIC ----------------

    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 &&
               col >= 0 && col < 3 &&
               board[row][col] == '-';
    }

    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // ---------------- COMPUTER MOVE ----------------

    static void computerMove() {
        Random random = new Random();
        int slot, row, col;

        while (true) {
            slot = random.nextInt(9) + 1;

            row = (slot - 1) / 3;
            col = (slot - 1) % 3;

            if (board[row][col] == '-') {
                board[row][col] = computerSymbol;
                System.out.println("Computer chose slot: " + slot);
                break;
            }
        }
    }

    // ---------------- DRAW CHECK ----------------

    static boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
