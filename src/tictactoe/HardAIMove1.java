package tictactoe;

import java.util.Scanner;

public class HardAIMove1 {
    char[][] field;

    public HardAIMove1(char[][] field) {
        this.field = field;
    }

    public Move move() {
        return new Move(field).findBestMove();
    }


    public class Move {
        int row, col;
        char[][] field;

        public Move(char[][] field) {
            this.field = field;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public char player = 'X', opponent = 'O';

        public Boolean isMovesLeft() {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (field[i][j] == '_')
                        return true;
            return false;
        }

        public int evaluate() {
            for (int row = 0; row < 3; row++) {
                if (field[row][0] == field[row][1] &&
                        field[row][1] == field[row][2]) {
                    if (field[row][0] == player)
                        return +10;
                    else if (field[row][0] == opponent)
                        return -10;
                }
            }

            for (int col = 0; col < 3; col++) {
                if (field[0][col] == field[1][col] &&
                        field[1][col] == field[2][col]) {
                    if (field[0][col] == player)
                        return +10;

                    else if (field[0][col] == opponent)
                        return -10;
                }
            }

            if (field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
                if (field[0][0] == player)
                    return +10;
                else if (field[0][0] == opponent)
                    return -10;
            }

            if (field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
                if (field[0][2] == player)
                    return +10;
                else if (field[0][2] == opponent)
                    return -10;
            }

            return 0;
        }

        public int minimax(int depth, Boolean isMax) {
            int score = evaluate();

            if (score == 10)
                return score;

            if (score == -10)
                return score;

            if (!isMovesLeft())
                return 0;

            int best;
            if (isMax) {
                best = -1000;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (field[i][j] == '_') {
                            field[i][j] = player;

                            best = Math.max(best, minimax(depth + 1, !isMax));

                            field[i][j] = '_';
                        }
                    }
                }
            } else {
                best = 1000;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (field[i][j] == '_') {
                            field[i][j] = opponent;

                            best = Math.min(best, minimax(depth + 1, !isMax));

                            field[i][j] = '_';
                        }
                    }
                }
            }
            return best;
        }

        public Move findBestMove() {
            int bestVal = -1000;
            Move bestMove = new Move(field);
            bestMove.row = -1;
            bestMove.col = -1;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == '_') {
                        field[i][j] = player;

                        int moveVal = minimax(0, false);

                        field[i][j] = '_';

                        if (moveVal > bestVal) {
                            bestMove.row = i;
                            bestMove.col = j;
                            bestVal = moveVal;
                        }
                    }
                }
            }
            return bestMove;
        }
    }
}
