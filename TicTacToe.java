package tictactoe;

import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TicTacToe {
    private  StringTokenizer stringTokenizer;
    static final Scanner scanner = new Scanner(System.in);
    private Player player1;
    private Player player2;
    private Player activPlayer;

    private String status;

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getActivPlayer() {
        return activPlayer;
    }

    public void setActivPlayer(Player activPlayer) {
        this.activPlayer = activPlayer;
    }

    private final char[][] field = new char[3][3];

    public void initializeField() {
        /*System.out.print("Enter the cells:"); // the cell must be initialized with 9 symbols, 3 of each symbol
        String initialState = scanner.nextLine();
        initialState.toUpperCase(); //Change the input to upper case
        String cleanedInput = initialState.replaceAll("\\s", ""); // remove all spaces from the input
        // verify if the input has exactly 9 characters
        if (cleanedInput.length() != 9) {
            System.out.println("Not a valid initial state. Try again");
            initializeField();
        }*/
        //initialize the field
        int k = 0;
        for (int a = 0; a < 3; a++){
            for (int b = 0; b < 3; b++){
                field[a][b]=' ';
            }
            k=k+2;
        }
        //System.out.println(field);
    }
    public void outputField(){
        System.out.println("----------");
        for (int a = 0; a < 3; a++){
            System.out.print("| ");
            for (int b = 0; b < 3; b++){
                System.out.print(field[a][b] + " ");
            }
            System.out.println("|");
        }
        System.out.println("----------");
    }
    // ask the player to enter the coordinates for his next mov
    public void coorEnter(){
        getActivPlayer().play(field);
        outputField();
        if (verifyIfWon(getActivPlayer())){
            inputCommand();
        }else{
            if(isFieldFull()){
                System.out.println("It's a draw!");
                inputCommand();
            }else{
                switchPlayer();
                coorEnter();
            }
        }
    }

    private void inputCommand() {
        System.out.print("Input command: ");
        String input = scanner.nextLine();
        stringTokenizer = new StringTokenizer(input);
        if(stringTokenizer.countTokens()<1 || stringTokenizer.countTokens()>3 || stringTokenizer.countTokens()==2){
            System.out.println(stringTokenizer.countTokens());
            System.out.println("Bad parameters!");
            inputCommand();
        }else if(stringTokenizer.countTokens()==1){
            String command = stringTokenizer.nextToken();
            if(command.equals("exit")){
                status = "exit";
            }else {
                System.out.println("Bad parameters!");
                inputCommand();
            }
        }else{
            if(!stringTokenizer.nextToken().contains("start")){
                System.out.println("Bad parameters!");
                inputCommand();
            }else{
                String playerOne = stringTokenizer.nextToken();
                if (playerOne.contains("user")){
                    player1 = new Human();
                    player1.setSymbol('X');
                    setActivPlayer(player1);
                }else if (playerOne.contains("easy")){
                    player1 = new Computer();
                    player1.setSymbol('X');
                    setActivPlayer(player1);
                }else if (playerOne.contains("medium")){
                    player1 = new ComputerMedium();
                    player1.setSymbol('X');
                    System.out.println("Medium");
                    setActivPlayer(player1);
                }else if (playerOne.contains("hard")) {
                    player1 = new ComputerHard();
                    player1.setSymbol('X');
                    System.out.println("Hard");
                    setActivPlayer(player1);
                }else{
                    System.out.println("Bad parameters!");
                    inputCommand();
                }
                String playerTwo = stringTokenizer.nextToken();
                if (playerTwo.contains("user")){
                    player2 = new Human();
                    player2.setSymbol('O');
                }else if (playerTwo.contains("easy")){
                    player2 = new Computer();
                    player2.setSymbol('O');
                }else if (playerTwo.contains("medium")){
                    player2 = new ComputerMedium();
                    player2.setSymbol('O');
                }else if (playerTwo.contains("hard")){
                    player2 = new ComputerHard();
                    player2.setSymbol('O');
                }else{
                    System.out.println("Bad parameters!");
                    inputCommand();
                }
            }
        }
    }

    private boolean isFieldFull() {
        for (int a = 0; a < 3; a++){
            for (int b = 0; b < 3; b++){
                if(field[a][b] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean verifyIfWon(Player player) {
        // player wins horizontally
        int row = 0;
        for (int a = 0; a < 3; a++){
            for (int b = 0; b < 3; b++){
                if(field[a][b] ==player.getSymbol()){
                    row++;
                }
            }
            if (row == 3){
                System.out.println(player.getSymbol() + " wins");
                return true;
            }else{
                row=0;
            }
        }
        // player wins vertically
        int column = 0;
        for (int a = 0; a < 3; a++){
            for (int b = 0; b < 3; b++){
                if(field[b][a] == player.getSymbol()){
                    column++;
                }
                if(column==3){
                    System.out.println(player.getSymbol() + " wins");
                    return true;
                }
            }
            column = 0;
        }
        // player win diagonally
        int diagonal =0;
        for (int i=0; i < 3 ; i++){
            if(field[i][i]==player.getSymbol()){
                diagonal++;
            }
        }
        if (diagonal==3){
            System.out.println(player.getSymbol() + " wins");
            return true;
        }
        if((field[0][2])==player.getSymbol() && (field[1][1])==player.getSymbol() && (field[2][0])==player.getSymbol()){
            System.out.println(player.getSymbol() + " wins");
            return true;
        }
        return false;
    }
    public void switchPlayer(){
        if(activPlayer==player1){
            activPlayer = player2;
        }else{
            activPlayer=player1;
        }
    }
    /*public void whoIsFirst(){
        int o = 0;
        int x = 0;
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                if (field[a][b] == 'X') {
                    x++;
                }else if (field[a][b] == 'O'){
                    o++;
                }
            }
        }
        if (x <= o){
            activplayer='X';
        }else{
            activplayer='O';
        }
    }*/
    public void start(){
        inputCommand();
        while(!Objects.equals(this.status, "exit")){
            initializeField();
            outputField();
            //whoIsFirst();
            coorEnter();
        }
    }
    public void restore(){
        setPlayer1(null);
        setPlayer2(null);
        setActivPlayer(null);
    }
}
