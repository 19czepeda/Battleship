import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Game {
    private ArrayList<Ship> ship = new ArrayList<Ship>();
    int numGuess = 0;
    
    public static void main(String[] args) {
        
        Game game = new Game();
        game.setup();
        
    } 
    // set up game
    // we use Arrays for the field
    // at the very beginning of the game there are 0 guesses
    
    private void setup() {
        ship.add(new Ship("Sub", 3));
        ship.add(new Ship("Battle Ship", 3));
        ship.add(new Ship("War Machine", 3));
        setLocations();
        
        System.out.println("Welcome to the Battle Ship game. Viel Glueck!");
        play();
    }
    // set up ships
    // 3 ships each one is 3 cells long
    // computer sets location of the ships
    // the player is welcomed at beginning of the game
    
    private void play() {
        String guess, result;
        Scanner input = new Scanner(System.in);
        while (!ship.isEmpty()){
            result = "miss";
            numGuess++;
            System.out.println("Enter a guess");
            guess = input.nextLine();
            guess = guess.toUpperCase();
            for (int i = 0; i < ship.size(); i++){
                result = ship.get(i).check(guess);
                if(result.equals("kill")){
                    result = ("you sunk " + ship.get(i).getName());
                    ship.remove(i);
                    break;
                }else if (result.equals("hit")){
                    break;
                }
            }
            System.out.println(result);
        }
        input.close();
        finish();
    }
    // this method is an if and else statement inside a for loop 
    // the player inputs a string as their guess ie "b4"
    // if the player guesses the wrong cell, it will be a miss
    // if the player guess the right cell, it will be a hit
    // computer checks if it is a hit or miss
    // when all three cells of a ship are hit, the game will print "you sunk shipx"
    // with every guess, the number of guesses increases
    // the computer prints the results 
    
    private void finish(){
        if (numGuess == 9){
            System.out.println("Congratulations! you got a perfect score!");
        }else if(numGuess < 20){
            System.out.println("Congratulations! it took you " + numGuess + " guesses.");
        }else if(numGuess < 30){
            System.out.println("Congrats! It took you " + numGuess + " guesses.");
        }else{
            System.out.println("Congrats! It took you " + numGuess + " guesses");
        }
    }
    // at the end of the game, the player will see how many hits it took them to complete
    // more than 30 guesses is pretty bad, 9 guesses is perfect score
    
    private void setLocations() {
        Random rand = new Random();
        ArrayList<String> locationToSet = new ArrayList<String>();
        ArrayList<String> temp = null;
        int let, num, incl, incn;
        String alpha = "ABCDEFGHIJKL";
        boolean worked;
        for (int i = 0; i< ship.size(); i++){
            worked = false; 
            start: 
            while (!worked){
                locationToSet.clear(); //clear the location setter
                worked = true;
                let = rand.nextInt(5);
                num = 1 + rand.nextInt(5);
                //this makes the ship vertical or horizontal
                if (num % 2 == 0){
                    incl = 1;//vertical
                    incn = 0;
                }else{
                    incl = 0;
                    incn = 1;//horizontal
                }//close if
                
                for (int j = 0; j < ship.get(i).getSize(); j++){
                    String loc = "" + alpha.charAt(let) + num;
                    let += incl;
                    num += incn;
                    
                    for (int t = 0; t < ship.size(); t++){
                        if (t != i){
                            temp = ship.get(t).getLocations();
                            if (temp.contains(loc)){
                                worked = false;
                                continue start; //you failed start over
                            }
                        }
                    }//close t for we passed if we get here
                    locationToSet.add(loc);
                }//close for j
                ship.get(i).setLocation(locationToSet);
            }
        }
    }
    // the player plays against Computer AI
    // we don't know how to perfectly print the board, but its 10x10
    // the computer can see it but not you, maybe draw out grid for demo
    // the computer selects a random location on the grid for the ships
    // letters are x axis 
    // numbers are y axis
    // the ships can either be vertical or horizontal 
    // in order for the player to make a guess, they must first guess a letter
    // then the number for example a2
}
            
            
                    