package net.edshaw.ld48.Weapons;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by edward on 14/12/2013.
 */
public class Sword implements AttackArea{

	@Override
	public Circle getArea() {
		return new Circle(0f, 1f, 0.5f);
	}
}
