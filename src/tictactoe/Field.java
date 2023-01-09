package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Field {
    private String input;
    private char[][] field = new char[3][3];
    private String coordinates;

    public void setInput(String input) {
        this.input = input;
    }

    public char[][] getField() {
        return field;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates.replaceAll(" ", "");
    }

    protected void fieldInitialization() {
        for (char[] chars : field) {
            Arrays.fill(chars, '_');
        }
    }

    protected void printField() {
        System.out.println("---------");
        for (char[] chars : field) {
            System.out.print("| ");
            for (char aChar : chars) {
                System.out.print(aChar);
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    protected void fillField() {
        int i = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
        int j = Integer.parseInt(String.valueOf(coordinates.charAt(1)));
        if (checkerOnXorO()) {
            field[i - 1][j - 1] = 'X';
        } else {
            field[i - 1][j - 1] = 'O';
        }
    }

    protected void fillFieldForAI() {
        int i = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
        int j = Integer.parseInt(String.valueOf(coordinates.charAt(1)));
        if (checkerOnXorO()) {
            field[i][j] = 'X';
        } else {
            field[i][j] = 'O';
        }
    }

    private boolean checkerOnXorO() {
        int countOfX = 0;
        int countOfO = 0;
        for (char[] chars : field) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    countOfX++;
                } else if (aChar == 'O') {
                    countOfO++;
                }
            }
        }
        return countOfO >= countOfX;
    }

    protected boolean fieldChecker() {
        if (!coordinates.matches("[\\d]+")) {
            System.out.println("You should enter numbers!");
            return false;
        }
        int i = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
        int j = Integer.parseInt(String.valueOf(coordinates.charAt(1)));
        if (i < 1 || i > 3 || j < 1 || j > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else if (field[i - 1][j - 1] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    protected boolean checkerForAI() {
        int i = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
        int j = Integer.parseInt(String.valueOf(coordinates.charAt(1)));
        return field[i - 1][j - 1] == '_';
    }

    protected boolean checkOnFinished() {
        return CheckOnWin() || checkOnDraw();
    }


    protected boolean CheckOnWin() {
        int diagonallyX = 0;
        int diagonallyO = 0;
        int sideDiagonallyO = 0;
        int sideDiagonallyX = 0;
        for (int i = 0; i < field.length; i++) {
            int countOfXinRow = 0;
            int countOfOinRow = 0;
            int countOfXinColumn = 0;
            int countOfOinColumn = 0;
            if (field[i][field.length - 1 - i] == 'X') {
                sideDiagonallyX++;
            }
            if (field[i][field.length - 1 - i] == 'O') {
                sideDiagonallyO++;
            }
            if (field[i][i] == 'X') {
                diagonallyX++;
            }
            if (field[i][i] == 'O') {
                diagonallyO++;
            }
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'X') {
                    countOfXinRow++;
                }
                if (field[i][j] == 'O') {
                    countOfOinRow++;
                }
                if (field[j][i] == 'X') {
                    countOfXinColumn++;
                }
                if (field[j][i] == 'O') {
                    countOfOinColumn++;
                }
            }
            if (countOfOinRow == 3 || countOfOinColumn == 3 || diagonallyO == 3 || sideDiagonallyO == 3) {
                System.out.println("O wins");
                return true;
            }
            if (countOfXinRow == 3 || countOfXinColumn == 3 || diagonallyX == 3 || sideDiagonallyX == 3) {
                System.out.println("X wins");
                return true;
            }
        }
        return false;
    }

    protected boolean checkOnDraw() {
        for (char[] chars : field) {
            for (char aChar : chars) {
                if (aChar == '_') {
                    return false;
                }
            }
        }
        System.out.println("Draw");
        return true;
    }

    protected String logicForMediumLevel() {
        int diagonallyX = 0;
        int diagonallyO = 0;
        int sideDiagonallyO = 0;
        int sideDiagonallyX = 0;
        for (int i = 0; i < field.length; i++) {
            int countOfXinRow = 0;
            int countOfOinRow = 0;
            int countOfXinColumn = 0;
            int countOfOinColumn = 0;
            if (field[i][field.length - 1 - i] == 'X') {
                sideDiagonallyX++;
                if (sideDiagonallyX == 2 && checkSideDiagonally() != null) {
                    return checkSideDiagonally();
                }
            }
            if (field[i][field.length - 1 - i] == 'O') {
                sideDiagonallyO++;
                if (sideDiagonallyO == 2 && checkSideDiagonally() != null) {
                    return checkSideDiagonally();
                }
            }
            if (field[i][i] == 'X') {
                diagonallyX++;
                if (diagonallyX == 2 && checkDiagonally() != null) {
                    return checkDiagonally();
                }
            }
            if (field[i][i] == 'O') {
                diagonallyO++;
                if (diagonallyO == 2 && checkDiagonally() != null) {
                    return checkDiagonally();
                }
            }
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'X') {
                    countOfXinRow++;
                    if (countOfXinRow == 2 && checkRow(field[i]) != -1) {
                        return String.valueOf(i) + checkRow(field[i]);
                    }
                }
                if (field[i][j] == 'O') {
                    countOfOinRow++;
                    if (countOfOinRow == 2 && checkRow(field[i]) != -1) {
                        return String.valueOf(i) + checkRow(field[i]);
                    }
                }
                if (field[j][i] == 'X') {
                    countOfXinColumn++;
                    if (countOfXinColumn == 2 && checkColumn(i) != null) {
                        return checkColumn(i);
                    }
                }
                if (field[j][i] == 'O') {
                    countOfOinColumn++;
                    if (countOfOinColumn == 2 && checkColumn(i) != null) {
                        return checkColumn(i);
                    }
                }
            }
        }
        return null;
    }

    private int checkRow(char[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] == '_') {
                return i;
            }
        }
        return -1;
    }

    private String checkColumn(int index) {
        for (int i = 0; i < field.length; i++) {
            if (field[i][index] == '_') {
                return String.valueOf(i) + (index);
            }
        }
        return null;
    }

    private String checkSideDiagonally() {
        for (int i = 0; i < field.length; i++) {
            if (field[i][field.length - 1 - i] == '_') {
                return String.valueOf(i) + (field.length - 1 - i);
            }
        }
        return null;
    }

    private String checkDiagonally() {
        for (int i = 0; i < field.length; i++) {
            if (field[i][i] == '_') {
                return String.valueOf(i) + (i);
            }
        }
        return null;
    }


}
