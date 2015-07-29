/**
 * @file SugarAnt.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version ..12
 * @project CMSC 202 - Fall '12 - Project
 * @section Section 02
 */
package proj4;

/**
 * @author rachaelbirky
 * Represents a Sugar Ant (an ant that gains 5 food when it defeats a zombie) with 20 life, 20 cost
 * deals 10 damage
 */
public class SugarAnt extends Ant{
	
	/**
	 * @name SugarAnt()
	 * @description Constructor; creates a new Sugar Ant with 20 life, 20 cost and it's name
	 * by passing these values to the super constructor Ant
	 */
	public SugarAnt(){
		super(20, 20, "Sugar Ant");
	}
	
	/**
	 * @name attack()
	 * @description deals 10 damage to a zombie
	 *   if it defeats the zombie (even if itself dies during the attack), gain an extra 5 food.
	 * Preconditions: none
	 * Postconditions: the ant will deal damage to the first zombie in the horde
	 * @param game - the current game being played
	 * @return none
	 */
	public void attack(Game g){
		Zombie z = g.getHorde().elementAt(0);
		z.takeDamage(10);
		if (z.getLife()<=0)
			g.addFood(5);
	}

}
