package tictactoe;


public class HardAIMove implements Moves {
    Field field;
    char XorO;

    public HardAIMove(Field field,char XorO) {
        this.field = field;
        this.XorO = XorO;
    }

    @Override
    public void move() {
        AI ai = new AI(XorO);
        System.out.println("Making move level \"hard\"");
        AI.Move bestMove = ai.findBestMove(field.getField());
        field.setCoordinates(String.valueOf(bestMove.row) + String.valueOf(bestMove.col));
        field.fillFieldForAI();
        field.printField();
    }

}
