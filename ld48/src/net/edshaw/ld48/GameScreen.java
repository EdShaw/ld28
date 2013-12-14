package net.edshaw.ld48;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

	Game game;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Player me;
	private List<Entity> ents = new ArrayList<>();

	public GameScreen(Game game){
		this.game = game;

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h/w);
		camera.setToOrtho(false, 400, 300);
		batch = new SpriteBatch();

		me = new Player();
		ents.add(me);
	}


	public void update(float delta){
		for(Entity e : ents){
			e.update(delta);
		}
	}

	public void draw(float delta){

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// TODO: Draw background;
		batch.enableBlending();

		// Draw sprites :)
		for(Entity e : ents){
			e.draw(delta, batch);
		}

		batch.disableBlending();
		batch.end();

	}

	// Boilerplate onwards.
	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	@Override
	public void resize(int i, int i2) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
