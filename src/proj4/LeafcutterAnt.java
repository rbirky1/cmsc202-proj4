/**
 * @file LeafcutterAnt.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version ..12
 * @project CMSC 202 - Fall '12 - Project
 * @section Section 02
 */
package proj4;

/**
 * @author rachaelbirky
 * Represents a Leafcutter Ant with 10 life, 20 cost
 * deals 10 damage
 */
public class LeafcutterAnt extends Ant{

	/**
	 * @name LeafcutteraAnt()
	 * @description Constructor; creates a new Leafcutter Ant with 10 life, 20 cost and it's name
	 * by passing these values to the super constructor Ant
	 */
	public LeafcutterAnt(){
		super(10, 20, "Leafcutter Ant");
	}
	
	/**
	 * @name attack()
	 * @description deals 10 damage to a zombie
	 * Preconditions: none
	 * Postconditions: the ant will deal damage to the first zombie in the horde
	 * @param game - the current game being played
	 * @return none
	 */
	public void attack(Game g){
		Zombie z = g.getHorde().elementAt(0);
		z.takeDamage(10);
	}
}
