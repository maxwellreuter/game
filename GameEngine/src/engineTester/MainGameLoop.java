// Manages the main game loop

package engineTester;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();

		// RawModel model = OBJLoader.loadObjModel("runescapeCharacter", loader);
		RawModel model = OBJLoader.loadObjModel("person", loader);

		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("blink-182")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);

		// runescapeCharacter settings
		// Entity entity = new Entity(staticModel, new Vector3f(0,-75,-600),0,0,0,1);
		// Light light = new Light(new Vector3f(0,0,-20),new Vector3f(1,1,1));

		Entity entity = new Entity(staticModel, new Vector3f(0, -5, -25), 0, 0, 0, 1);
		List<Entity> entities = new ArrayList<>();
		entities.add(entity);
		
		
		Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

		Camera camera = new Camera();

		MasterRenderer renderer = new MasterRenderer();
		
		// persist display until user exit
		while (!Display.isCloseRequested()) {
			camera.move();
			
			for (Entity e : entities) {
				entity.increaseRotation(0, 1, 0);
				renderer.processEntity(e);
			}
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
