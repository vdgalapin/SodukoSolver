/**
 *
 * @author Vyron Galapin
 */
import java.util.*;
import java.io.*;
import javax.swing.JFileChooser;

public final class SodukoBoard extends Soduko {

    //Create a soduko boad with two dimensional list
    private final int[][] board;
    /*
    The constructor will take the name of the board.
    The exception will be thrown if the file cannot be found.
     */
    public SodukoBoard() throws FileNotFoundException {
        //Assign the board with 9 rows and 9 columns
        board = new int[9][9];
        //Load the file into the board
        Load();
    }

    /*
    The exception will be thrown if the file cannot be found.
     */
    public void Load() throws FileNotFoundException {
        //Allows us to choose a file
        JFileChooser file_chosen = new JFileChooser();
        file_chosen.showOpenDialog(null);
     
        //This allows us to iterate through the file
        Scanner input = new Scanner(file_chosen.getSelectedFile());
        //Iterate through the file
        while (input.hasNextInt()) {
            for(int row = 0; row < board.length; row++) {
                for (int column = 0; column < board[row].length; column++) {
                    board[row][column] = input.nextInt();
                }
            }
        }
    }

    /*
    Checks the row by iterating through the two dimensional list
     */
    public boolean AllowedInRow(int row, int value) {
        //Iterates through the columns
        for (int col = 0; col < board[row].length; col++) {
            //If the value exist in that row it will return false
            if (board[row][col] == value) {
                return false;
            }
        }
        //If it is not in the row it will return true
        return true;
    }

    /*
    Checks the column by iterating through the two dimensional list
     */
    public boolean AllowedInColumn(int column, int value) {
        //Iterate through the rows
        for (int row = 0; row < board.length; row++) {
            //If the value exist in that column it will return false
            if (board[row][column] == value) {
                return false;
            }
        }
        //If it is not in the column it will return true
        return true;
    }

    /*
    Checks the block by iterating through the two dimensional list
     */
    public boolean AllowedInBlock(int row, int column, int value) {
        //This allows us to be in the right block 
        int BlockRowStart = row / 3 * 3;
        int BlockColumnStart = column / 3 * 3;
        //Iterate through the two dimensional list using the values above
        for (int R = BlockRowStart; R < BlockRowStart + 3; R++) {
            for (int C = BlockColumnStart; C < BlockColumnStart + 3; C++) {
                //If the value exist in that block it will return false
                if (board[R][C] == value) {
                    return false;
                }
            }
        }
        //If it is notin the block it will return true
        return true;
    }

    /*
    Check the row, column, and block at once
     */
    public boolean Allowed(int row, int column, int value) {;
        return AllowedInRow(row, value) && AllowedInColumn(column, value) && AllowedInBlock(row, column, value);
    }

    //Added a divider to make it look nice
    public void PrintDivider() {
        //The lenght of the divide
        int length_divider = 25;
        for (int starting = 0; starting < length_divider; starting++) {
            System.out.print("-");
        }
        //To break the line
        System.out.println("");
    }

    /*
    Prints the whole soduko board
     */
    public void PrintSoduko() {
        //Iterate through the row to print
        for (int row = 0; row < 9; row++) {
            //If the value got through 0,3,6,9 then print the divider
            if (row % 3 == 0) {
                PrintDivider();
            }
            //iterate through the column to print
            for (int col = 0; col < 9; col++) {
                //If the value gor through 0,3,6,9 then prints the " | " divider
                if (col % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][col] + " ");
            }
            System.out.println("|");
        }
        //Print the last divider
        PrintDivider();
    }

    /*
    The method where to start solving
     */
    public boolean solve() {
        return solve(0, 0);
    }

    /*
    The recursion to solve the soduko
     */
    public boolean solve(int row, int column) {
        //If its done at one row it will move to the next row
        //And assign the value of the column to 0
        if (column == 9) {
            column = 0;
            row++;
        }
        //if the value of the row is 9 then then it is solve
        if (row == 9) {
            return true;
        }
        //If the value is 0 then we are allowed to enter a value to try it
        if (board[row][column] == 0) {
            //Iterate through the value of 1 to 9 to check it the value is allowed
            for (int value = 1; value <= 9; value++) {
                //Use the method to check the row, column and block simulatneously 
                //If the value is allowed
                if (Allowed(row, column, value)) {
                    System.out.println(row + " " + column + " " + value);
                    board[row][column] = value;
                    //This is a recursive call to iterate through the next column to check
                    if (solve(row, column + 1)) {
                        return true;
                    }
                //If the  value is not allowed then the value will be just 0
                board[row][column] = 0;
                }
            }
            //If none of the value from 1 - 9 is allowed 
            return false;
        } //If the value is not 0 means it alread has a value on it.
        else {
            //This is a recursive call to iterate through the next column to check
            return solve(row, column + 1);
        }
    }
}
