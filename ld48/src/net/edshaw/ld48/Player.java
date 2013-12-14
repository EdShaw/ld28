package net.edshaw.ld48;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

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

	Sprite sprite;
	Texture texture;

	Vector2 pos = new Vector2(0,0);
	Direction dir = Direction.N;


	public Player(){
		texture = new Texture(Gdx.files.internal("data/player.png"));
		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		sprite = new Sprite(texture);
		sprite.setSize(16, 16);
	}

	public void draw(float v, SpriteBatch batch){

		sprite.setPosition(16*pos.x, 16*pos.y);
		sprite.setRotation(-dir.toAngle());
		sprite.draw(batch);

	}
	public void update(float v){


		boolean up = Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W);
		boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S);
		boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
		boolean righ = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);

		if (up)   pos.y += 0.1;
		if (down) pos.y -= 0.1;
		if (left) pos.x -= 0.1;
		if (righ) pos.x += 0.1;

		if (up) dir = Direction.N;
		if (righ) dir = Direction.E;
		if (down) dir = Direction.S;
		if (left) dir = Direction.W;

		if (up && righ) dir = Direction.NE;
		if (righ && down) dir = Direction.SE;
		if (down && left) dir = Direction.SW;
		if (left && up) dir = Direction.NW;

	}

}
