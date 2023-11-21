package tictactoe;

import java.util.Random;

public class Computer implements Player{
    private String name = "easy";
    private char symbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    protected final Random random = new Random();
    @Override
    public void play(char[][] field) {
        System.out.println("Making move level \""+name+"\"");
        int coordinates = (random.nextInt(3)+1)*10+ random.nextInt(3)+1;
        // verify if the cell is empty
        while(field[(coordinates/10)-1][(coordinates%10)-1]!=' '){
            coordinates = (random.nextInt(3)+1)*10+ random.nextInt(3)+1;
        }
        // put the symbol in the field
        field[(coordinates/10)-1][(coordinates%10)-1]=getSymbol();
    }
}
