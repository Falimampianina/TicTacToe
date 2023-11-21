package tictactoe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComputerHard extends ComputerMedium{
    private String name = "hard";

    @Override
    public void play(char[][] field) {
        System.out.println("Making move level \""+name+"\"");
        int[] move = minimax(field, super.getSymbol());
        field[move[0]][move[1]] = super.getSymbol();

    }
    public int utility(char[][] field , char c){
        int a = -100;
        if (verifyIfWin(field , c)){
            a = 6 - countSymbol(field , c);
        }else if ( verifyIfWin(field , getTheOtherSymbol(c))){
            a = -6 + countSymbol(field , getTheOtherSymbol(c));
        }else{
            if(isFieldFull(field)){
                a = 0;
            }
        }
        return a;
    }

    public boolean isTerminal(char[][] field){
        if(verifyIfWin(field, super.getSymbol())){
            return true;
        }else if(verifyIfWin(field, getTheOtherSymbol(super.getSymbol()))){
            return true;
        }else{
            return isFieldFull(field);
        }
    }

    private int[] minimax(char[][] field, char player) {
        int[] result = new int[]{-1, -1};
        int bestScore = (player == super.getSymbol()) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    field[i][j] = player;
                    int score = minimaxScore(field, 0, false);
                    field[i][j] = ' ';
                    if ((player == super.getSymbol() && score > bestScore) || (player == getTheOtherSymbol(super.getSymbol()) && score < bestScore)) {
                        bestScore = score;
                        result[0] = i;
                        result[1] = j;
                    }
                }
            }
        }
        return result;
    }

    private int minimaxScore(char[][] field, int depth, boolean isMaximizing) {
        if (isTerminal(field)) {
            return utility(field, super.getSymbol());
        }

        if (isMaximizing) {
            int maxScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == ' ') {
                        field[i][j] = super.getSymbol();
                        int score = minimaxScore(field, depth + 1, false);
                        field[i][j] = ' ';
                        maxScore = Math.max(score, maxScore);
                    }
                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == ' ') {
                        field[i][j] = getOpponentSymbol();
                        int score = minimaxScore(field, depth + 1, true);
                        field[i][j] = ' ';
                        minScore = Math.min(score, minScore);
                    }
                }
            }
            return minScore;
        }
    }
    private boolean isFieldFull(char[][] field) {
        for (int a = 0; a < 3; a++){
            for (int b = 0; b < 3; b++){
                if(field[a][b] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
    public int countSymbol(char[][] field , char c){
        int count = 0;
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                if (field[a][b] == c) {
                    count++;
                }
            }
        }
        return count;
    }
    public char getTheOtherSymbol(char c){
        if(c == 'X'){
            return 'O';
        }else{
            return 'X';
        }
    }

}
