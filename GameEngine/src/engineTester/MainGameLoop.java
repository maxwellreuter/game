// Manages the main game loop

package engineTester;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Scene;
import textures.ModelTexture;
import toolbox.MousePicker;
import toolbox.GameMouse;

public class MainGameLoop {
	
	public static final float ASPECT_RATIO = (float) 1920 / (float) 1080;
	public static String SCENE = "desert"; // options: grassy, desert, snowy, swamp, night

	public static void main(String[] args) {
		
		// startup
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Scene scene = new Scene(SCENE);
		MasterRenderer renderer = new MasterRenderer();
		
		// player, camera
		RawModel characterModel = OBJLoader.loadObjModel("test", loader);
		TexturedModel character = new TexturedModel(characterModel, new ModelTexture(loader.loadTexture("dark_grey")));
		Player player = new Player(character, new Vector3f(512, -64, -400), 0, 180, 0, 0.027f);
		Camera camera = new Camera(player);
		
		// user interface
		MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), scene.getTerrain());
		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		GuiTexture gui = new GuiTexture(loader.loadTexture("console"), new Vector2f(-0.733f, -0.85f), new Vector2f(ASPECT_RATIO * 0.15f, 0.15f));
		guis.add(gui);
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		
		// persist display until user exit
		while (!Display.isCloseRequested()) {
			// update player and camera
			player.move(scene.getTerrain()); // if using multiple terrains, test which terrain the player is on and send to this method
			camera.move();
			
			// mouse picking
			picker.update();
			Vector3f terrainPoint = picker.getCurrentTerrainPoint();
			if(terrainPoint!=null) {
				if(GameMouse.leftClick() || GameMouse.isLeftButtonDown()) {
					player.setTarget(terrainPoint);
				}
			}
			
			// rendering
			renderer.processEntity(player);
			renderer.processTerrain(scene.getTerrain());
			for(Entity entity:scene.getEntities()) {
				renderer.processEntity(entity);
			}
			renderer.render(camera, scene);
			guiRenderer.render(guis);
			DisplayManager.updateDisplay();
		}

		// cleanup
		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
