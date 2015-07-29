/**
 * @file WeaverAnt.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version ..12
 * @project CMSC 202 - Fall '12 - Project
 * @section Section 02
 */
package proj4;

/**
 * @author rachaelbirky
 * Represents a Weaver Ant (an ant that attacks the second zombie in the horde instead of the first) with 10 life, 20 cost
 * deals 15 damage
 */
public class WeaverAnt extends Ant{
	
	/**
	 * @name WeaverAnt()
	 * @description Constructor; creates a new Weaver Ant with 10 life, 20 cost and it's name
	 * by passing these values to the super constructor Ant
	 */
	public WeaverAnt(){
		super(10, 20, "Weaver Ant");
	}
	
	/**
	 * @name attack()
	 * @description deals 15 damage to a zombie
	 * Preconditions: none
	 * Postconditions: the ant will deal damage to the SECOND zombie in the horde
	 *   if there is only one zombie in the horde, the weaver ant does nothing
	 * @param game - the current game being played
	 * @return none
	 */
	public void attack(Game g){
		//try catch since the vector trims size t elements
		try{ 
			Zombie z = g.getHorde().elementAt(1);
			z.takeDamage(15);
		}
		catch (ArrayIndexOutOfBoundsException e){
			//There is only one zombie, so do nothing
		}
	}
}
