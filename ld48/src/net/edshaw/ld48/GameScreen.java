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

	private SpriteBatch batch;


	private final World world;

	public GameScreen(Game game){
		this.game = game;
		batch = new SpriteBatch();
		world = new World();
	}


	public void update(float delta){
		Gdx.gl.glClearColor(0.8f, 0.7f, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		world.update(delta);
	}

	public void draw(float delta){

		world.draw(delta, batch);

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
