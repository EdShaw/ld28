package net.edshaw.ld48;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import net.edshaw.ld48.Weapons.Sword;
import net.edshaw.ld48.Weapons.Weapon;

public class Player extends Entity {

	Sprite sprite;
	Texture texture;

	Direction dir = Direction.N;

	Weapon weapon;


	public Player(World world, Vector2 pos){
		super(world, pos.x, pos.y, 1,1);
		this.health = 1;

		texture = new Texture(Gdx.files.internal("data/player.png"));
		texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

		sprite = new Sprite(texture);
		sprite.setSize(1, 1);
		sprite.setOrigin(0.5f, 0.5f);

		weapon = new Weapon(10, new Sword());
	}

	public void draw(float v, SpriteBatch batch){

		sprite.setPosition(pos.x,pos.y);
		sprite.translate(-0.5f, -0.5f);
		sprite.setRotation(-dir.toAngle());
		sprite.draw(batch);
	}
	public void update(float v){


		boolean up = Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W);
		boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S);
		boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
		boolean righ = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);

		Vector2 vel = new Vector2(0,0);
		if (up)   vel.add(+0.0f, +0.1f);
		if (down) vel.add(+0.0f, -0.1f);
		if (left) vel.add(-0.1f, +0.0f);
		if (righ) vel.add(+0.1f, +0.0f);	

		if (up) dir = Direction.N;
		if (righ) dir = Direction.E;
		if (down) dir = Direction.S;
		if (left) dir = Direction.W;

		if (up && righ) dir = Direction.NE;
		if (righ && down) dir = Direction.SE;
		if (down && left) dir = Direction.SW;
		if (left && up) dir = Direction.NW;

		move(vel.x, vel.y);

		//Map collisions
		TiledMapTileLayer layer  = (TiledMapTileLayer)world.tilemap.getLayers().get(0);

		RenderDebug.rect(this.bounds);

		int leftMost = (int)    (pos.x - bounds.width / 2);
		int rightMost = (int)   (pos.x + bounds.width / 2);
		int topMost = (int)     (pos.y - bounds.height / 2);
		int botMost = (int)     (pos.y + bounds.height / 2);

		Rectangle intersector = new Rectangle();
	    Vector2 separator = new Vector2();

		for (int x = leftMost; x<= rightMost; x++){
			for (int y = topMost; y<= botMost; y++){
				TiledMapTileLayer.Cell cell = layer.getCell(x,y);
				if(cell != null){
					Rectangle tileRect = new Rectangle();
					tileRect.set(x,y,1,1);

					RenderDebug.rect(tileRect, new Color(0,1,0,0.1f));
					if (cell.getTile().getProperties().containsKey("wall")){
						if(Intersector.intersectRectangles(bounds, tileRect, intersector)){
							if (intersector.getAspectRatio()<1){//width smaller
								move(intersector.width*((pos.x>(x+0.5f))?1:-1), 0);
							} else {
								move(0, intersector.height*((pos.y>(y+0.5f))?1:-1));
							}
						}

					}
				}
			}

		}

		boolean attack = Gdx.input.isKeyPressed(Input.Keys.SPACE);
		if (attack) {
			Circle area = weapon.area.getArea();
			Vector2 areaPos = new Vector2(area.x, area.y).rotate(-dir.toAngle()).add(pos);
			area.setPosition(areaPos);
			RenderDebug.circle(area, new Color(0,0,1,1));

			for (Entity ent : world.ents){
				System.out.println("Attack?");
				if(ent!=this) if(Intersector.overlaps(area, ent.bounds)){
					System.out.println("Attack!");
					ent.hurt(weapon.damage);
				}
			}
		}

	}
}
