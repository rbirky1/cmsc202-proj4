/**
 * @file CarpenterAnt.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version 11.21.12
 * @project CMSC 202 - Fall '12 - Project 4
 * @section Section 02
 */
package proj4;

/**
 * @author rachaelbirky
 * Represents a Carpenter Ant with 10 life, 10 cost
 * deals 10 damage
 */
public class CarpenterAnt extends Ant{

	/**
	 * @name CarpenterAnt()
	 * @description Constructor; creates a new Carpenter Ant with 10 life, 10 cost and it's name
	 * by passing these values to the super constructor Ant
	 */
	public CarpenterAnt(){
		super(10, 10, "Carpenter Ant");
	}
	
	/**
	 * @name attack()
	 * @description deals 10 damage to a zombie
	 * Preconditions: none
	 * Postconditions: the ant will deal damage to the first zombie in the horde
	 * @param game - the current game being played
	 * @return none
	 */
	@Override
	public void attack(Game g){
		Zombie z = g.getHorde().elementAt(0);
		z.takeDamage(10);
	}
}
