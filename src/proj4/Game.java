/**
 * This is the Game.java file posted in the Project4 project
 * description. It contains only the nextFight method. Students
 * must complete the remainder of the Game methods and add the
 * Game instance variables.
 * 
 * @file Game.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version 11.13.12
 * @project CMSC 202 - Fall '12 - Project 4
 * @section Section 02
 */

package proj4;
/** Represents a game of Ants vs Zombies**/
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

public class Game implements GameInterface {

    /******************** instance variables ********************/
	private Vector<Ant> colony = new Vector<Ant>();			//The ants the player has recruited
	private Vector<Zombie> horde = new Vector<Zombie>();	//The zombies currently attacking
	
	private int INITIAL_FOOD = 100; //The player starts with 100 food
	private int roundNumber=1;		//The round starts with 1 ends at 5 (terminates at 6)
	private int food=INITIAL_FOOD;  //The food variable that is edited in-game
	private String colonyDesc;		//A formatted string of the names of the ants in the current colony
	private String hordeDesc;		//A formatted string of the names of the zombies in the current horde
	private boolean gameOver;		//A boolean to track whether the current game is still in-game
	private boolean isInvasionOver;	//A boolean to track whether the current invasion is still in-game
	private int numArmyAnts=0;		//The number of army ants recruited throughout the entire game
									//	used to edit the damage an Army Ant deals

    /*************** methods declared in GameInterface ****************/
	
	/**
	 * @name getRoundNumber()
	 * @description Return the current round for the game.
	 * Precondition: round number must be an initialized integer
	 * Postcondition: the current round number will be returned
	 * @param none
	 * @return roundNumber, 1 through 5 inclusive
	 */
	@Override
	public int getRoundNumber() {
		return this.roundNumber;
	}
	
	/**
	 * @name getFood()
     * @description Return the amount of food the player's colony currently has.
     * Precondition: food must be an initialized integer
	 * Postcondition: the current amount of food is returned
     * @return food remaining
     */
	@Override
	public int getFood() {
		return this.food;
	}

	 /**
	 * @name getColonyDesc()
     * @description Return a string that lists all of the ants in the player's colony.
     * Precondition: the colony must be declared
	 * Postcondition: a string with the names of the ants currently in the colony
     * @return Multiline description of colony.
     */
	@Override
	public String getColonyDesc() {
		colonyDesc = "";
		for(Ant a : colony)
			colonyDesc += a.getDesc() + " - remaining life: "+a.getLife()+"\n";
		return this.colonyDesc;
	}

	/**
	 * @name recruitAnt()
     * @description Callback invoked when the player attempts to recruit an ant.
     * 	If the player has enough food, the ant is added to the colony
     * Precondition: a valid ant type is given
	 * Postcondition: if the ant is recruit-able, it is added to the colony
     * @param String antType Type of ant to recruit
     * @return true if the player may recruit the ant, false if not.
     */
	@Override
	public boolean recruitAnt(String antType){
		Ant a = Ant.determineAntType(antType);
		if (a.getCost()<=food){
			colony.add(a);
			removeFood(a.getCost());
			if (a instanceof ArmyAnt)
				numArmyAnts++;
			return true;
		}
		else return false;
	}

	/**
	 * @name readHordeFile()
     * @description Read and parse the Zombie String within a zombie file.
     * Precondition: a valid, existing filename is given
	 * Postcondition: the file is read and closed appropriately
	 *   each character is interpreted and a new zombie is made for each
     * @param filename File containing Zombie String
     * @return none
     */
	@Override
	public void readHordeFile(String filename) {
		try{
			java.io.FileReader file = new java.io.FileReader(filename);
			java.io.BufferedReader buf = new java.io.BufferedReader(file);
			String zombieString = buf.readLine();
			
			char[] zombieChars = zombieString.toCharArray();
			for (int i=0; i<zombieChars.length; i++)
				//If a number, add multiple of previous type
				if ((int)zombieChars[i]>48 && (int)zombieChars[i]<58){
					for (int j=0; j<(int)zombieChars[i]-48; j++)
					horde.add(Zombie.makeZombie(zombieChars[i-1]));
				}
				//If a char, add a zombie of that type
				else horde.add(Zombie.makeZombie(zombieChars[i]));
			buf.close();
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	 /**
	 * @name getHordeDesc()
     * @description Return a string that lists all of the zombies in the current invasion
     * Precondition: none 
	 * Postcondition: a string of the names of the zombies currently in the horde is returned
     * @return Multiline description of horde.
     */
	@Override
	public String getHordeDesc() {
		hordeDesc = "";
		for (Zombie z : horde)
			hordeDesc += z.getDesc()+" - remaining life: "+z.getLife()+"\n";
		return this.hordeDesc;
	}

	/**
	 * @name isInvasionOver()
     * @description Execute a fight between the first ant in the colony and first zombie in the horde.
     * Precondition: horde and colony have been initialized
	 * Postcondition: if the horde or the colony is empty, the current invasion is declared over
     * @return true if the horde or the colony is over
     */
	@Override
	public boolean isInvasionOver() {
		isInvasionOver = (horde.isEmpty() || colony.isEmpty());
		if(isInvasionOver){
			for (int i=0; i<colony.size(); i++){
				colony.elementAt(i).restoreHealth();
				
			}
		incrRoundNumber();
		}
		return isInvasionOver;
	}

	/**
	 * @name isGameOver()
     * @description Determine if the game is over or not.
     * Precondition: 
	 * Postcondition: if the last round has ended or the player has no food to buy ants and his colony is empty, but the horde is not, the game is declared over. 
     * @return true if it is the last round or the player cannot buy more ants and there are still zombies in the horde 
     */
	@Override
	public boolean isGameOver() {
		//Game is over if 1.it's the last round 2.the colony is empty (and the player cannot buy more ants)
		//horde = (Vector<Zombie>) horde.clone();
		//colony = (Vector<Ant>) colony.clone();
		//gameOver = (roundNumber==6 && isInvasionOver()) || (/*food<10 &&*/ colony.isEmpty() && !horde.isEmpty());
		 return gameOver;
	}

	/**
	 * @name getEndingMessage()
     * @description Return a string that describe how the game ended.  If the
     * player lost, simply return "Game Over", otherwise return the
     * player's score.
     * Precondition: none
	 * Postcondition: if the player lost (their colony is empty and the horde is not) "Game over" is returned
	 *  if the player won, the score (amount of food) is displayed
     * @return Description of ending condition.
     */
	@Override
	public String getEndingMessage() {
		if (!horde.isEmpty() && colony.isEmpty()) return "Game Over";
		else return "Score: "+food;
	}
	
	/**
	 * @name getAntTypes()
     * @description Return an array of all types of ants that may be recruited.
     * This array will be used to construct the recruitment buttons
     * during Recruit phase.
     * Precondition: none
	 * Postcondition: An array of (String) ants available to recruit is returned
     * @return String[] of ant names
     */
	@Override
	public String[] getAntTypes() {
		//Get an array of Strings, each being an Ant Type
		String[] antTypes = Ant.getAntTypes();
		
		//Create a new Vector of Strings for easy resizing
		Vector<String> antsAvailable= new Vector<String>();
		
		//For each antType, if there is enough food, add to ants user can buy
		for (int i=0; i<antTypes.length; i++){
			if (Ant.determineAntType(antTypes[i]).getCost()<=food)
				antsAvailable.add(antTypes[i]);
		}
		
		//Create a String[] of same size as Vector
		String[] antsAvailableArray = new String[antsAvailable.size()];
		//Copy contents of vector into array
		antsAvailable.copyInto(antsAvailableArray);
		//return as the string array
		return antsAvailableArray;
	}

	/**
	 * @name getAntCost()
     * @description Return the cost to recruit a particular ant.
     * Precondition: Ant cannot be null
	 * Postcondition: The cost of the desired ant is returned
     * @param antType Type of ant to recruit.
     * @return Food cost to recruit.
     */
	@Override
	public int getAntCost(String antType) throws IllegalArgumentException{
		Ant a = Ant.determineAntType(antType);
		if (a==null) throw new IllegalArgumentException("Cannot be null");
		else return a.getCost();
	}
	
    /**
     * Execute a fight between the first ant in the colony and first
     * zombie in the horde.
     */
    public void nextFight() {
		Ant a = colony.elementAt(0);
		a.attack(this);
	
		Zombie z = horde.elementAt(0);
		if ((a instanceof LeafcutterAnt) && (z.getLife() <= 0)) {
		    // leafcutters have first strike, so opposing zombie gets no attack
		}
		else {
		    z.attack(this);
		}
	
		// reap all things dead
		boolean keepReaping = true;
		while (keepReaping) {
		    keepReaping = false;
		    for (int i = 0; i < colony.size(); ) {
			a = colony.elementAt(i);
			if (a.getLife() > 0) {
			    i++;
			}
			else {
			    colony.remove(i);
			    if (a instanceof CitronellaAnt) {
				for (Ant a2 : colony) {
				    a2.takeDamage(2);
				}
				for (Zombie z2: horde) {
				    z2.takeDamage(2);
				}
			    }
			    keepReaping = true;
			}
		    }
	
		    for (int i = 0; i < horde.size(); ) {
			z = horde.elementAt(i);
			if (z.getLife() > 0) {
			    i++;
			}
			else {
			    horde.remove(i);
			    food += z.getReward();
			}
		    }
		}
		if (colony.size() == 0 && horde.size() > 0) {
		    gameOver = true;
		}
    }

    /******************** other methods ********************/

    /**
	 * @name addFood() 
     * @description adds the amount passed to the food that player has
     * Precondition: amount is an integer
	 * Postcondition: the amount is added to the instance variable food
     * @param amount
     * @return none
     */
    public void addFood(int amount){
    	food+=amount;
    }
    
    /**
	 * @name getColony()
     * @description a copy of the instance variable ants vector is returned 
     * Precondition: colony is a vector of ants, not null
	 * Postcondition: a copy of the instance variable ants vector is returned 
     * @param none
     * @return a vector of ants representing all the ants currently in the colony
     */
    public Vector<Ant> getColony() {
    	colony = (Vector<Ant>) colony.clone();
		return /*(Vector<Ant>)*/ colony/*.clone()*/;
	}
    
    /**
	 * @name getHorde()
     * @description a copy of the instance variable zombies vector is returned 
     * Precondition: horde is a vector of ants, not null
	 * Postcondition: a copy of the instance variable zombies vector is returned 
     * @param none
     * @return a vector of zombies representing all the zombies currently in the horde
     */
	public Vector<Zombie> getHorde() {
		horde = (Vector<Zombie>) horde.clone();
		return /*(Vector<Zombie>)*/ horde/*.clone()*/;
	}

    /**
	 * @name incrRoundNumber()
     * @description adds one to the round number when an invasion ends
     * Precondition: round number has been initialized
	 * Postcondition:  the round number is increased by one
     * @param none
     * @return none
     */
  	private void incrRoundNumber(){
  		roundNumber++;
  	}
  	
    /**
	 * @name subtractFood()
     * @description removes the appropriate amount of food (equal to the cost of the ant recruited)
     * Precondition: none
	 * Postcondition: the food instance variable is edited
     * @param amount -- the amount of food that an ant costs to subtract from the palyer's total food
     * @return none
     */
  	public void removeFood(int amount){
  		this.food-=amount;
  	}
  	
    /**
	 * @name getNumArmyAnts()
     * @description returns an int representing the number of army ants that were recruited during the game
     * Precondition: none
	 * Postcondition: the number of army ants recruited is returned
     * @param none
     * @return numArmyAnts
     */
  	public int getNumArmyAnts(){
  		return this.numArmyAnts;
  	}
  	
    //TESTING
    public static void main(String[] args){
    	Game g = new Game();
    	g.readHordeFile("horde_5.data");
    	System.out.println("Horde contains:");
    	System.out.println(g.getHordeDesc());
    	
    	String[] ants = g.getAntTypes();
    	System.out.println("The ants you can buy:");
    	for (String s : ants){
    		System.out.println(s);
    	}
    	
    	//Add ants to colony
    	g.recruitAnt("Army Ant");
    	g.recruitAnt("Carpenter Ant");
    	
    	System.out.println("\nColony contains:");
    	System.out.println(g.getColonyDesc());
    }
}
