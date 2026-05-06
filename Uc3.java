import java.util.Random;
import java.util.Scanner;

public class Uc3 {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        tossAndAssignSymbols();
        displayTossResult();

        if (isHumanTurn) {
            int slot = getUserSlot();
            placeMove(slot, humanSymbol);
            printBoard();
        } else {
            System.out.println("Computer will make a move later...");
        }
    }

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

    static int getUserSlot() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a slot number (1-9): ");
        return scanner.nextInt();
    }

    static void placeMove(int slot, char symbol) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        board[row][col] = symbol;
    }
}
