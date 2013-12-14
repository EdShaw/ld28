package net.edshaw.ld48;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	enum Direction {
		// Define in clockwise order.
		N, NE, E, SE, S, SW, W, NW;

		Vector2 toVec(){
			Vector2 vec = new Vector2(0,1);
			vec.rotate(45*this.ordinal());
			return vec;
		}

		float toAngle(){
			return this.ordinal()*45.0f;
		}
	}


	abstract void draw(float delta, SpriteBatch b);
	abstract void update(float delta);

}
