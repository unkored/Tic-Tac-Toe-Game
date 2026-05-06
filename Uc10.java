import java.util.Random;
import java.util.Scanner;

public class Uc10 {

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

                    if (hasWon(humanSymbol)) {
                        System.out.println("Human wins!");
                        gameOver = true;
                    } else if (isDraw()) {
                        System.out.println("Game over! It's a draw.");
                        gameOver = true;
                    } else {
                        isHumanTurn = false;
                    }

                } else {
                    System.out.println("Invalid move, try again.");
                }

            } else {
                System.out.println("Computer's turn");

                computerMove();
                printBoard();

                if (hasWon(computerSymbol)) {
                    System.out.println("Computer wins!");
                    gameOver = true;
                } else if (isDraw()) {
                    System.out.println("Game over! It's a draw.");
                    gameOver = true;
                } else {
                    isHumanTurn = true;
                }
            }
        }
    }

    // Board setup
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

    // Toss logic
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

    // User input
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

    // Game logic
    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 &&
               col >= 0 && col < 3 &&
               board[row][col] == '-';
    }

    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // Computer move
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

    // Win check
    static boolean hasWon(char symbol) {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol &&
                board[i][1] == symbol &&
                board[i][2] == symbol) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbol &&
                board[1][j] == symbol &&
                board[2][j] == symbol) {
                return true;
            }
        }

        if (board[0][0] == symbol &&
            board[1][1] == symbol &&
            board[2][2] == symbol) {
            return true;
        }

        if (board[0][2] == symbol &&
            board[1][1] == symbol &&
            board[2][0] == symbol) {
            return true;
        }

        return false;
    }

    // Board full check
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

    // UC10: Draw check
    static boolean isDraw() {
        return isBoardFull() &&
               !hasWon(humanSymbol) &&
               !hasWon(computerSymbol);
    }
}
