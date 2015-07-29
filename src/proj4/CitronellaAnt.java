/**
 * @file CitronellaAnt.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version ..12
 * @project CMSC 202 - Fall '12 - Project
 * @section Section 02
 */
package proj4;

/**
 * @author rachaelbirky
 * Represents a Citronella Ant (an ant that, when killed, deals 2 damage to every ant and zombie) with 20 life, 25 cost
 * deals 10 damage
 */
public class CitronellaAnt extends Ant{
	
	/**
	 * @name CitronellaAnt()
	 * @description Constructor; creates a new Citronella Ant with 20 life, 25 cost and it's name
	 * by passing these values to the super constructor Ant
	 */
	public CitronellaAnt(){
		super(20, 25, "Citronella Ant");
	}

	/**
	 * @name attack()
	 * @description deals appropriate amount of damage to first zombie in horde
	 * Preconditions: none
	 * Postconditions: the ant will deal damage to a zombie
	 * @param game - the current game being played
	 * @return none
	 */
	@Override
	public void attack(Game g){
		Zombie z = g.getHorde().elementAt(0);
		z.takeDamage(10);
	}
	
}
