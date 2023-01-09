package tictactoe;

import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    Field field = new Field();
    Moves player1 = new HumanMove(field);
    Moves player2 = new HumanMove(field);


    public void startGame() {
        menu();
    }

    private void input() {
        field.fieldInitialization();
        field.printField();
    }


    private void menu() {
        System.out.println("Input command:");
        String answer = scanner.nextLine().toLowerCase();

        String[] subAnswer = answer.trim().split(" ");
        while (subAnswer.length != 3) {
            if (answer.equalsIgnoreCase("Exit")) {
                return;
            }
            System.out.println("Bad parameters!");
            System.out.println("Input command:");
            answer = scanner.nextLine();
            subAnswer = answer.trim().split(" ");
        }
        switch (subAnswer[1]) {
            case "easy" -> player1 = new EasyAIMove(field);
            case "medium" -> player1 = new MediumAIMove(field);
            case "user" -> player1 = new HumanMove(field);
            case "hard" -> player1 = new HardAIMove(field, 'X');
        }
        switch (subAnswer[2]) {
            case "easy" -> player2 = new EasyAIMove(field);
            case "medium" -> player2 = new MediumAIMove(field);
            case "user" -> player2 = new HumanMove(field);
            case "hard" -> player2 = new HardAIMove(field, 'O');
        }
        input();
        while (!field.checkOnFinished()) {
            player1.move();
            if (field.checkOnFinished()) {
                return;
            }
            player2.move();
        }
    }
}
