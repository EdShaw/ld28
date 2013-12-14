package net.edshaw.ld48;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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

	public final World world;

	public final Vector2 pos;
	public final Rectangle bounds;

	public float health;

	public Entity(World world, float x, float y, float w, float h){
		this.world = world;
		this.pos = new Vector2(x,y);
		this.bounds = new Rectangle(x, y, w, h);
	}

	abstract void draw(float delta, SpriteBatch b);
	abstract void update(float delta);

	void move(float x, float y){
		pos.add(x,y);
		bounds.setCenter(pos);
	}

	void hurt(float damage){
		this.health -= damage;
		if (this.health<0){
			this.world.toKill.add(this);
		}
	}

}
