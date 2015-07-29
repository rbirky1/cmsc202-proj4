/**
 * @file BulletAnt.java
 * @author Rachael Birky <rbirky1@gl.umbc.edu>
 * @version 11.13.12
 * @project CMSC 202 - Fall '12 - Project 4
 * @section Section 02
 */
package proj4;

/**
 * @author rachaelbirky
 * Represents a Bullet Ant with 1 life, 10 cost
 * deals 25 damage
 */
public class BulletAnt extends Ant{
	
	/**
	 * @name BulletAnt()
	 * @description Constructor; creates a new Bullet Ant with 1 life, 10 cost and it's name
	 * by passing these values to the super constructor Ant
	 */
	public BulletAnt(){
		super(1, 10, "Bullet Ant");
	}
	
	/**
	 * @name attack()
	 * @description deals 25 damage to a zombie
	 * Preconditions: none
	 * Postconditions: the ant will deal damage to the first zombie in the horde
	 * @param game - the current game being played
	 * @return none
	 */
	@Override
	public void attack(Game g){
		Zombie z = g.getHorde().elementAt(0);
		z.takeDamage(25);
	}
	
}
