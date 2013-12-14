package net.edshaw.ld48;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class RenderDebug {

	static class DebugObj {
		Object obj;
		Color col;
	}

	public final static List<DebugObj> objs = new ArrayList<>();

	public static void rect(Rectangle rect) {
		rect(rect, new Color(1,0,0,0.25f));
	}

	public static void rect(Rectangle rect, Color col) {
		DebugObj obj = new DebugObj();
		obj.obj=rect;
		obj.col = col;
		objs.add(obj);
	}

	public static void circle(Circle circ, Color col) {
		DebugObj obj = new DebugObj();
		obj.obj=circ;
		obj.col = col;
		objs.add(obj);
	}

	public static void render(World world, float delta){
		for (DebugObj obj : objs){
			if (obj.obj instanceof Rectangle){
				Rectangle rect = (Rectangle) obj.obj;
				ShapeRenderer r = new ShapeRenderer();
				r.setProjectionMatrix(world.camera.combined);
				r.begin(ShapeRenderer.ShapeType.Filled);
				r.setColor(obj.col);
				r.rect(rect.x, rect.y, rect.width, rect.height);
				r.end();
			} if (obj.obj instanceof Circle){
				Circle rect = (Circle) obj.obj;
				ShapeRenderer r = new ShapeRenderer();
				r.setProjectionMatrix(world.camera.combined);
				r.begin(ShapeRenderer.ShapeType.Filled);
				r.setColor(obj.col);
				r.circle(rect.x, rect.y, rect.radius);
				r.end();
			}
		}
		objs.clear();

	}
}
