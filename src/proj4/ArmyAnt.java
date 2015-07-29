/**
 * @file ArmyAnt.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version 11.13.12
 * @project CMSC 202 - Fall '12 - Project
 * @section Section 02
 */
package proj4;

/**
 * @author rachaelbirky
 * Represents an Army Ant (an ant that deals 5 extra damage for each army ant recruited during the game, including itself) with 30 life, 35 cost
 * deals 10 damage plus 5 extra damage for each army ant recruited during the game, including itself
 */
public class ArmyAnt extends Ant{
	
	/**
	 * @name ArmyAnt()
	 * @description Constructor; creates a new Army Ant with 30 life, 35 cost and it's name
	 * by passing these values to the super constructor Ant
	 */
	public ArmyAnt(){
		super(30, 35, "Army Ant");
	}
	
	/**
	 * @name attack()
	 * @description deals appropriate amount of damage to a zombie
	 *   10 damage, plus 5 for every Army Ant recruited in the game (including the current Ant)
	 * Preconditions: none
	 * Postconditions: the ant will deal damage to the first zombie in the horde
	 * @param game - the current game being played
	 * @return none
	 */
	@Override
	public void attack(Game g){
		//get zombie to attack
		Zombie z = g.getHorde().elementAt(0);
		
		//Deal 10 damage, plus 5 for every Army Ant recruited in the game (including the current Ant)
		z.takeDamage(10+(5*g.getNumArmyAnts()));
	}

}
