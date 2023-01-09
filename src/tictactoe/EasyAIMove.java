package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class EasyAIMove implements Moves{
    Field field;
    Scanner scanner = new Scanner(System.in);

    public EasyAIMove(Field field) {
        this.field = field;
    }

    @Override
    public void move() {
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        StringBuilder coordinates = new StringBuilder();
        coordinates.append(random.nextInt(3) + 1).append(random.nextInt(3) + 1);
        field.setCoordinates(coordinates.toString());
        while (!field.checkerForAI()) {
            coordinates = new StringBuilder();
            coordinates.append(random.nextInt(3) + 1).append(random.nextInt(3) + 1);
            field.setCoordinates(coordinates.toString());
        }
        field.fillField();
        field.printField();
    }
}
