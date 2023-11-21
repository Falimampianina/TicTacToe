package tictactoe;

import java.util.*;

public class ComputerMedium extends Computer{
    private String name = "medium";

    @Override
    public void play(char[][] field) {
        System.out.println("Making move level \""+name+"\"");
        int a = bestMov(field);
        if (a>0){
            // if there is a possibility to win, do it
            field[(a/10)-1][(a%10)-1]=super.getSymbol();
        }else if (a<0){
            // if there is not, don't let the opponent win
            field[(-1*a/10)-1][(-1*a%10)-1]=super.getSymbol();
        }else{
            int coordinates = (random.nextInt(3)+1)*10+ random.nextInt(3)+1;
            // verify if the cell is empty
            while(field[(coordinates/10)-1][(coordinates%10)-1]!=' '){
                coordinates = (random.nextInt(3)+1)*10+ random.nextInt(3)+1;
            }
            // put the symbol in the field
            field[(coordinates/10)-1][(coordinates%10)-1]=getSymbol();
        }
    }

    // getting every possible moves
    public Set<Integer> allPossibleMoves(char[][] field){
        Set<Integer> moves = new HashSet<>();
        for (int a = 0; a < 3; a++){
            for (int b = 0; b < 3; b++){
                if(field[a][b] == ' '){
                    moves.add((a+1)*10+b+1);
                }
            }
        }
        return moves;
    }

    // search a coordinate for a winning move or for the move that doesn't let the opponent win
    public int bestMov(char[][] field){
        int a = 0;
        Set<Integer> moves = allPossibleMoves(field);
        for (Integer i : moves){
            field[i/10-1][i%10-1] = super.getSymbol();
            if(verifyIfWin(field, super.getSymbol())){
                // undo the move
                field[i/10-1][i%10-1] = ' ';
                a = i;
                // return the coordinate of the winning move
                return a;
            }else{
                field[i/10-1][i%10-1] = ' ';
            }
        }
        // bestMov for the opponent
        for (Integer i : moves){
            field[i/10-1][i%10-1] = getOpponentSymbol();
            if(verifyIfWin(field, getOpponentSymbol())){
                // undo the move
                field[i/10-1][i%10-1] = ' ';
                a = -1*i;
                // return the coordinate of the winning move from the opponent but negate
                return a;
            }else{
                field[i/10-1][i%10-1] = ' ';
            }
        }
        return a;
    }

    public boolean verifyIfWin(char[][] field, char symbol){
        // player wins horizontally
        int row = 0;
        for (int a = 0; a < 3; a++){
            for (int b = 0; b < 3; b++){
                if(field[a][b] ==symbol){
                    row++;
                }
            }
            if (row == 3){
                return true;
            }else{
                row=0;
            }
        }
        // player wins vertically
        int column = 0;
        for (int a = 0; a < 3; a++){
            for (int b = 0; b < 3; b++){
                if(field[b][a] == symbol){
                    column++;
                }
                if(column==3){
                    return true;
                }
            }
            column = 0;
        }
        // player win diagonally
        int diagonal =0;
        for (int i=0; i < 3 ; i++){
            if(field[i][i]==symbol){
                diagonal++;
            }
        }
        if (diagonal==3){
            return true;
        }
        if((field[0][2])==symbol && (field[1][1])==symbol && (field[2][0])==symbol){
            return true;
        }
        return false;
    }
    // getting the symbol of the opponent
    public char getOpponentSymbol(){
        if(super.getSymbol()=='X'){
            return 'O';
        }else{
            return 'X';
        }
    }

}
