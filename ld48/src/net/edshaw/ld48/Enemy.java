package net.edshaw.ld48;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.graphics.Color;

public class Enemy extends Entity {

	Sprite sprite;
	Texture texture;

	Direction dir = Direction.N;

	boolean attacking=false;


	public Enemy(World world, Vector2 pos){
		super(world, pos.x, pos.y, 1, 1);
		this.health = 20;

		texture = new Texture(Gdx.files.internal("data/enemy.png"));
		texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

		sprite = new Sprite(texture);
		sprite.setSize(1, 1);
		sprite.setOrigin(0.5f, 0.5f);
	}

	public void draw(float v, SpriteBatch batch){

		sprite.setPosition(pos.x, pos.y);
		sprite.translate(-0.5f, -0.5f);
		sprite.setRotation(-dir.toAngle());
		if(attacking){
			sprite.setColor(Color.LIGHT_GRAY);
		} else {
			sprite.setColor(Color.WHITE);
		}
		sprite.draw(batch);


	}
	public void update(float v){

		Vector2 playerPos = world.me.pos.cpy();

		Vector2 repel = new Vector2(0,0);

		for(Entity e : world.ents){
			if (e instanceof Enemy){
				Vector2 dir = this.pos.cpy().sub(e.pos);
				float dist2 = dir.len2(); if (Float.isNaN(dist2) || Float.isInfinite(dist2) || dist2 < 1) dist2 =1;
				repel.add(dir.div(dist2*15));
			}
		}

		repel.clamp(0.0f, 0.4f);

		move(repel.x, repel.y);
		Vector2 direction = playerPos.sub(this.pos).clamp(0.0f, 0.08f);
		move(direction.x, direction.y);

		attacking = world.me.bounds.overlaps(this.bounds);
	}

}
