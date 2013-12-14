package net.edshaw.ld48.Weapons;

/**
 * Created by edward on 14/12/2013.
 */
public class Weapon {

	public final AttackArea area;
	public final int damage;

	public Weapon(int damage, AttackArea area) {
		this.damage = damage;
		this.area = area;
	}

}
