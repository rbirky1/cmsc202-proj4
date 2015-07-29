/**
 * @file ThiefAnt.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version ..12
 * @project CMSC 202 - Fall '12 - Project
 * @section Section 02
 */
package proj4;

/**
 * @author rachaelbirky
 * Represents a Thief Ant (an ant that deals half the damage it receives) with 25 life, 15 cost
 */
public class ThiefAnt extends Ant{
	
	/**
	 * @name ThiefAnt()
	 * @description Constructor; creates a new Thief Ant with 25 life, 15 cost and it's name
	 * by passing these values to the super constructor Ant
	 */
	public ThiefAnt(){
		super(25, 15, "Thief Ant");
	}

	/**
	 * @name attack()
	 * @description a method stub -- since the thief ant technically deals no damage itself, only reflects damage
	 * Preconditions: none
	 * Postconditions: none
	 * @param game - the current game being played
	 * @return none
	 */
	@Override
	public void attack(Game g){
	}

	/**
	 * @name takeDamage()
	 * @description the ant receives damage and deals half the damage it recieves to a zombie
	 * Preconditions: none
	 * Postconditions: the ant will receive damage and deal damage to the first zombie in the horde
	 * @param amount - the damage the zombie gives the ant
	 * 	z - the attacking zombie (that also receives damage)
	 * @return none
	 */
	@Override
	public void takeDamage(int amount, Zombie z){
		z.takeDamage((int)amount/2);
		super.takeDamage(amount);
	}
}
