package tictactoe;

import java.util.Scanner;

public class HumanMove implements Moves{
    Field field;
    Scanner scanner = new Scanner(System.in);

    public HumanMove(Field field) {
        this.field = field;
    }

    @Override
    public void move() {
        System.out.println("Enter the coordinates:");
        String coordinates = scanner.nextLine();
        field.setCoordinates(coordinates);
        while (!field.fieldChecker()) {
            System.out.println("Enter the coordinates:");
            coordinates = scanner.nextLine();
            field.setCoordinates(coordinates);
        }
        field.fillField();
        field.printField();
    }
}
