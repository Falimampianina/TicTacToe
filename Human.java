package tictactoe;

public class Human implements Player{
    private String name;
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

    @Override
    public void play(char[][] field) {
        System.out.print("Enter the coordinates: ");
        String input = TicTacToe.scanner.nextLine();
        // clean the input so that it contains no space anymore
        String cleanedInput = input.replaceAll("\\s","");
        if(cleanedInput.length()==2){
            try{
                int coordinates = Integer.parseInt(cleanedInput);
                // verify if the coordinates are valid ( from 1 to 3)
                if ((coordinates/10) > 3 || (coordinates%10)>3){
                    System.out.println("Coordinates should be from 1 to 3!");
                    play(field);
                }else{
                    // verify if the cell is empty
                    if (field[(coordinates/10)-1][(coordinates%10)-1]==' '){
                        // put the symbol in the field, output the field and verify if a player had won or a draw or not finished
                        field[(coordinates/10)-1][(coordinates%10)-1]=getSymbol();
                    }else{
                        System.out.println("This cell is occupied! Choose another one!");
                        play(field);
                    }
                }
            } catch (Exception e){
                System.out.println("You should enter numbers!");
                play(field);
            }
        }else{
            System.out.println("You should enter numbers!");
            play(field);
        }
    }
}
