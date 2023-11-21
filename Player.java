package tictactoe;

public interface Player {
    void play(char[][] field);
    char getSymbol();
    void setSymbol(char c);
    String getName();
    void setName(String name);



}
