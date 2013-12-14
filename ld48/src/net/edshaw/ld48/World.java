package net.edshaw.ld48;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class World {

	public final Player me;
	public final List<Entity> ents = new ArrayList<>();
	public final List<Entity> toKill = new ArrayList<>();

	TiledMap tilemap;
	private final OrthogonalTiledMapRenderer mapRenderer;

	public OrthographicCamera camera;

	public World(){
		tilemap = new TmxMapLoader().load("data/TestLevel.tmx");
		float unitScale = 1/16f;
		mapRenderer = new OrthogonalTiledMapRenderer(tilemap, unitScale);

		me = new Player(this, new Vector2(3,3));
		ents.add(me);

		ents.add(new Enemy(this, new Vector2(5,10)));
		ents.add(new Enemy(this, new Vector2(0,10)));
		ents.add(new Enemy(this, new Vector2(10,0)));
		ents.add(new Enemy(this, new Vector2(30,30)));
		ents.add(new Enemy(this, new Vector2(30,60)));
		ents.add(new Enemy(this, new Vector2(60,60)));

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h/w);
		camera.setToOrtho(false, 32, 17);
	}

	public void update(float delta){
		for(Entity e : ents){
			e.update(delta);
		}

		ents.removeAll(toKill);
		toKill.clear();

	}

	public void draw(float delta, SpriteBatch batch){
		camera.position.x = me.pos.x;
		camera.position.y = me.pos.y;

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		mapRenderer.setView(camera);
		mapRenderer.render();
		batch.begin();
		batch.enableBlending();
		// Draw sprites :)

		for(Entity e : ents){
			e.draw(delta, batch);
		}
		batch.disableBlending();
		batch.end();

		//Debug
		RenderDebug.render(this, delta);
	}

}
