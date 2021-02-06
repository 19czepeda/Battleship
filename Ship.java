import java.util.ArrayList;
public class Ship {
    private ArrayList<String> location = new ArrayList<String>();
    private String name;
    private int size;
    
    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
    }
    // the ship is public 
    // all ships are 3 cells long  
    // Battleship 3 cells, War Machine 3 cells, Submarine 3 cells  
    public ArrayList<String> getLocations() {
        return location; 
    } 
    
    public int getSize(){
        return size; 
        
    }
    
    public void setLocation(ArrayList<String> locationToSet) {
        this.location.addAll(locationToSet);
        
    }
    // these methods localize the ships when the field is set 
    public String check(String guess) {
        String result = "miss";
        if (location.contains(guess)){
            location.remove(guess);
            result = location.isEmpty() ? "kill" : "hit";
            
        }
        
        return result; 
    }
    // if the player guesses the correct location, the ship has been hit 
    // the ship is one hit closer to being sunk 
    public String getName() {
        
        return name;
    }
}