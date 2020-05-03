// Manages the main game loop

package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture,
				gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

		// RawModel model = OBJLoader.loadObjModel("runescapeCharacter", loader);
		RawModel model = OBJLoader.loadObjModel("tree", loader);

		/*
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("blink-182")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);

		// runescapeCharacter settings
		// Entity entity = new Entity(staticModel, new Vector3f(0,-75,-600),0,0,0,1);
		// Light light = new Light(new Vector3f(0,0,-20),new Vector3f(1,1,1));

		Entity entity = new Entity(staticModel, new Vector3f(0, -5, -25), 0, 0, 0, 1);
		//List<Entity> entities = new ArrayList<>();
		//entities.add(entity);
		*/
		
		Light light = new Light(new Vector3f(20000, 20000, 20000), new Vector3f(1, 1, 1));
		
		
		TexturedModel texturedTree = new TexturedModel(OBJLoader.loadObjModel("tree", loader),
				new ModelTexture(loader.loadTexture("tree")));
		TexturedModel texturedGrass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),
				new ModelTexture(loader.loadTexture("grassTexture")));
		texturedGrass.getTexture().setHasTransparency(true);
		texturedGrass.getTexture().setUseFakeLighting(true);
		TexturedModel texturedFern = new TexturedModel(OBJLoader.loadObjModel("fern", loader),
				new ModelTexture(loader.loadTexture("fern")));
		texturedFern.getTexture().setHasTransparency(true);
		//texturedFern.getTexture().setUseFakeLighting(true);
		
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		for(int i=0;i<1000;i++){
		   entities.add(new Entity(texturedTree, new Vector3f(random.nextFloat()*1600 - 800,0,random.nextFloat() * -800),0,0,0,3f));
		   entities.add(new Entity(texturedGrass, new Vector3f(random.nextFloat() * 1600 -800, 0, random.nextFloat() * -800), 0, 0, 0, 1));
		   entities.add(new Entity(texturedFern, new Vector3f(random.nextFloat() * 1600 -800, 0, random.nextFloat() * -800), 0, 0, 0, 0.6f));
		}
		
		Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap);
		Terrain terrain2 = new Terrain(-1, -1, loader, texturePack, blendMap);	

		MasterRenderer renderer = new MasterRenderer();
		
		RawModel characterModel = OBJLoader.loadObjModel("character", loader);
		TexturedModel character = new TexturedModel(characterModel, new ModelTexture(loader.loadTexture("grey")));
		
		Player player = new Player(character, new Vector3f(0, 0, -20), 0, 180, 0, 0.027f);
		Camera camera = new Camera(player);
		
		// persist display until user exit
		while (!Display.isCloseRequested()) {
			//entity.increaseRotation(0, 1, 0);
			camera.move();
			player.move();
			renderer.processEntity(player);
			
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			
			for(Entity entity:entities) {
			 renderer.processEntity(entity);
			}
			//renderer.processEntity(entity);
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
