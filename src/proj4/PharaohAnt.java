/**
 * @file PharahAnt.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version ..12
 * @project CMSC 202 - Fall '12 - Project
 * @section Section 02
 */
package proj4;

/**
 * @author rachaelbirky
 * Represents a Pharaoh Ant (an ant that deals extra damage to gigantic zombies) with 10 life, 15 cost
 * deals 30 damage if the zombie is gigantic, 10 otherwise
 */
public class PharaohAnt extends Ant{

	/**
	 * @name PharaohAnt()
	 * @description Constructor; creates a new Pharaoh Ant with 10 life, 15 cost and it's name
	 * by passing these values to the super constructor Ant
	 */
	public PharaohAnt(){
		super(10, 15, "Pharaoh Ant");
	}
	
	/**
	 * @name attack()
	 * @description deals appropriate appropriate amount of damage to a zombie
	 *   30 if the zombie is gigantic, 10 otherwise
	 * Preconditions: none
	 * Postconditions: the ant will deal damage to the first zombie in the horde
	 * @param game - the current game being played
	 * @return none
	 */
	public void attack(Game g){
		Zombie z = g.getHorde().elementAt(0);
		if (z instanceof Gigantic)
			z.takeDamage(30);
		else
			z.takeDamage(10);
	}
}
