import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int size = 3;

    public static void main(String[] args) {

        System.out.println("Enter a number of users: ");
        int numberOfUsers = getIntFromInput(Integer.MAX_VALUE);


        System.out.println("Enter a number of dimensions: ");
        int dimensions = getIntFromInput(Integer.MAX_VALUE);
        String[][] board = new String[dimensions][dimensions];
        for (int  i = 0; i < dimensions; i++)
            Arrays.fill(board[i], " ");

        int winner = 0;
        int fullCells = dimensions * dimensions;
        for (int i = 1; winner == 0 && fullCells != 0; i++) {
            if (i > numberOfUsers) {
                i = 1;
            }

            boolean isCellEmpty = true;
            int row = 0;
            int column = 0;

            while (isCellEmpty) {
                System.out.println("user " + i + " turn\n" +
                        "Please choose i and j as \"i j\" format which i and j are between 1 and " + dimensions +
                        " (i is row number and j is column number): ");

                row = getIntFromInput(dimensions) - 1;
                column = getIntFromInput(dimensions) - 1;

                if (board[row][column].equals(" ")) {
                    board[row][column] = String.valueOf(i);
                    isCellEmpty = false;
                } else {
                    System.out.println("This cell is already full. Please choose another one.");
                }
            }
            drawTable(dimensions, board);

            if (hasWinner(board, row, column))
                winner = i;
            --fullCells;
        }

        if (winner == 0)
            System.out.println("No Winner");
        else
            System.out.println("Winner is " + winner);
    }

    public static int getIntFromInput(int checkValue) {
        boolean isInputValid = false;
        int number = 0;

        while (!isInputValid) {
            String input = scanner.next();

            try {
                number =  Integer.parseInt(input);
                if (1 <= number && number <= checkValue)
                    isInputValid = true;
                else
                    throw new NumberFormatException();
            } catch (Exception e) {
                System.out.println("Invalid input\ntry again");
            }
        }

        return number;
    }

    public static void drawTable(int dimensions, String[][] board) {
        for (int j = 0; j < dimensions; j++) {
            for (int k = 0; k < dimensions; k++) {
                System.out.print("|");

                for (int l = 0; l < size; l++) {
                    if (l == size / 2)
                        System.out.print(board[j][k]);
                    else
                        System.out.print(" ");
                }


            }

            System.out.println("|");
            for (int l = 0; l < dimensions * size; l++) {
                System.out.print("--");
            }
            System.out.println();
        }
    }

    public static boolean hasWinner(String[][] board, int row, int column) {
        return checkWinnerHorizontal(board, row, column) ||
                checkWinnerVertical(board, row, column) ||
                checkWinnerDiagonal(board, row, column, true);
    }

    public static boolean checkWinnerHorizontal(String[][] board, int fix, int varying) {
        try {
            if (board[fix][varying].equals(board[fix][varying + 1]) && board[fix][varying].equals(board[fix][varying + 2])) {
                return true;
            }
        } catch (Exception ignored) {
        }

        try {
            if (board[fix][varying].equals(board[fix][varying - 1]) && board[fix][varying].equals(board[fix][varying + 1])) {
                return true;
            }
        } catch (Exception ignored) {
        }

        try {
            if (board[fix][varying].equals(board[fix][varying - 1]) && board[fix][varying].equals(board[fix][varying - 2])) {
                return true;
            }
        } catch (Exception ignored) {
        }

        return false;
    }

    public static boolean checkWinnerVertical(String[][] board, int fix, int varying) {
        try {
            if (board[fix][varying].equals(board[fix + 1][varying]) && board[fix][varying].equals(board[fix + 2][varying])) {
                return true;
            }
        } catch (Exception ignored) {
        }

        try {
            if (board[fix][varying].equals(board[fix - 1][varying]) && board[fix][varying].equals(board[fix + 1][varying])) {
                return true;
            }
        } catch (Exception ignored) {
        }

        try {
            if (board[fix][varying].equals(board[fix - 1][varying]) && board[fix][varying].equals(board[fix - 2][varying])) {
                return true;
            }
        } catch (Exception ignored) {
        }

        return false;
    }

    public static boolean checkWinnerDiagonal(String[][] board, int row, int column, boolean isMainDiameter) {
        int coefficient = isMainDiameter ? 1 : -1;

        try {
            if (board[row][column].equals(board[row - coefficient * 1][column + 1]) &&
                    board[row][column].equals(board[row - coefficient * 2][column + 2])) {
                return true;
            }
        } catch (Exception ignored) {
        }

        try {
            if (board[row][column].equals(board[row + coefficient * 1][column - 1]) &&
                    board[row][column].equals(board[row - coefficient * 1][column + 1])) {
                return true;
            }
        } catch (Exception ignored) {
        }

        try {
            if (board[row][column].equals(board[row + coefficient * 1][column - 1]) &&
                    board[row][column].equals(board[row + coefficient * 2][column - 2])) {
                return true;
            }
        } catch (Exception ignored) {
        }

        return false;
    }
}