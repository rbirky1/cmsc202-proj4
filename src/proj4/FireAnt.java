/**
 * @file FireAnt.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version ..12
 * @project CMSC 202 - Fall '12 - Project
 * @section Section 02
 */
package proj4;

/**
 * @author rachaelbirky
 * Represents a Fire Ant (an ant that deals extra damage to flammable zombies) with 20 life, 15 cost
 * deals 20 damage if the zombie is flammable, 10 otherwise
 */
public class FireAnt extends Ant{
	
	/**
	 * @name FireAnt()
	 * @description Constructor; creates a new Fire Ant with 20 life, 15 cost and it's name
	 * by passing these values to the super constructor Ant
	 */
	public FireAnt(){
		super(20, 15, "Fire Ant");
	}
	
	/**
	 * @name attack()
	 * @description deals 10 damage to a zombie, 20 if the zombie is flammable
	 * Preconditions: none
	 * Postconditions: the ant will deal damage to the first zombie in the horde
	 * @param game - the current game being played
	 * @return none
	 */
	public void attack(Game g){
		Zombie z = g.getHorde().elementAt(0);
		if(z instanceof Flammable)
			z.takeDamage(20);
		else
			z.takeDamage(10);
	}

}
