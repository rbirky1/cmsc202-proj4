/**
 * @file Ant.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version ..12
 * @project CMSC 202 - Fall '12 - Project
 * @section Section 02
 */
package proj4;

/**
 * Represents an ant, and all the properties and behaviors that all ants share
 *
 */
public abstract class Ant {

	private int origLife;
	private int life;
	private int cost;
	private String desc;
	public static final String[] antTypes = {"Army Ant", "Bullet Ant", "Carpenter Ant", "Citronella Ant", "Fire Ant", "Leafcutter Ant", "Pharaoh Ant", "Sugar Ant", "Thief Ant", "Weaver Ant"};
	
	/**
	 * @name Ant()
	 * @description Constructor; creates a new Ant by storing property values
	 * @param life - the amount of life the and has
	 * @param cost - how much food is required to recruit this ant
	 * @param desc - a String representing the ant's name/type
	 */
	protected Ant(int life, int cost, String desc){
		this.origLife = life;
		this.life = life;
		this.cost = cost;
		this.desc = desc;
	}
	
	
	/**
	 * @name getLife()
	 * @description returns the current ant's current amount of life
	 * Preconditions: none
	 * Postconditions: the ant's life will be returned as an integer
	 * @return life - an integer representing the ant's life
	 */
	public int getLife(){
		return life;
	}
	
	/**
	 * @name getCost()
	 * @description returns the current ant's cost
	 * Preconditions: none
	 * Postconditions: the ant's recruitment cost will be returned as an integer
	 * @return cost - an integer representing the ant's cost in food
	 */
	public int getCost(){
		return cost;
	}
	
	/**
	 * @name takeDamage()
	 * @description subtracts the amount passed from the ant's life
	 * 	used when the ant takes damage from a Citronella ant
	 * Preconditions: amount must be a valid integer
	 * Postconditions: the ant's life will be reduced by the given amount
	 * @param amount - a positive integer representing the damage taken by the ant
	 * @return none
	 */
	public void takeDamage(int amount){
		life-=amount;
	}
	
	/**
	 * @name takeDamage()
	 * @description subtracts the amount passed from the ant's life
	 * 	used when the ant takes damage from a zombie
	 * Preconditions: amount must be a valid integer
	 * Postconditions: the ant's life will be reduced by the given amount
	 * @param amount - an positive integer representing the damage taken by the ant
	 * @param z - the zombie attacking the current ant
	 * @return none
	 */
	public void takeDamage(int amount, Zombie z){
		life-=amount;
	}
	
	/**
	 * @name attack()
	 * @description deals appropriate amount of damage to a zombie
	 * Preconditions: none
	 * Postconditions: the ant will deal damage to a zombie
	 * @param game - the current game being played
	 * @return none
	 */
	public abstract void attack(Game g);
	
	/**
	 * @name getDesc()
	 * @description returns the current ant's name as a String
	 * Preconditions: none
	 * Postconditions: the ant's name will be returned as a String
	 * @return desc - a string representing the current ant's name
	 */
	public String getDesc() {
		return this.desc;
	}
	
	/**
	 * @name determineAntType()
	 * @description Given a String representing an ant's name, returns an ant of the appropriate type
	 * Preconditions: antType must be a string
	 * Postconditions: a new ant instance of the appropriate type is created and returned
	 * @param antType - a String representing an ant's name
	 * @return new <AntType>
	 */
	public static Ant determineAntType(String antType) {
		char f=antType.charAt(0);
		char s=antType.charAt(1);
		switch(f){
			case 'A': return new ArmyAnt();
			case 'B': return new BulletAnt();
			case 'C': {if (s=='a') {return new CarpenterAnt();} else {return new CitronellaAnt();}}
			case 'F': return new FireAnt();
			case 'L': return new LeafcutterAnt();
			case 'P': return new PharaohAnt();
			case 'S': return new SugarAnt();
			case 'T': return new ThiefAnt();
			case 'W': return new WeaverAnt();
			default: return null;
			}
	}	
	
	/**
	 * @name getAntTypes()
	 * @description returns a String[] of all Ant Types (all children of Ant)
	 * Preconditions: none
	 * Postconditions: a String[] of all Ant Types is returned
	 * @return antTypes - a final String[] in Ant that contains all the different Ant Types' names
	 */
	public static String[] getAntTypes(){
		return antTypes;
	}
	
	/**
	 * @name restoreHealth()
	 * @description Restores the current ant's health to its original value
	 * Preconditions: none
	 * Postconditions: the ant's health is reassigned to the original amount
	 * @return none
	 */
	public void restoreHealth(){
		this.life=origLife;
	}
}
