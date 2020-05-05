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
		
		//TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
		//TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
		//TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
		//TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("sand"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("sand"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("sand"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("sand"));
		
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
		
		Light light = new Light(new Vector3f(0, 90000, 0), new Vector3f(1, 1, 1));
		
		
		TexturedModel texturedTree = new TexturedModel(OBJLoader.loadObjModel("cactus", loader),
				new ModelTexture(loader.loadTexture("tree")));
		TexturedModel texturedGrass = new TexturedModel(OBJLoader.loadObjModel("cactus2", loader),
				new ModelTexture(loader.loadTexture("tree")));
		texturedGrass.getTexture().setHasTransparency(true);
		texturedGrass.getTexture().setUseFakeLighting(true);
		TexturedModel texturedFern = new TexturedModel(OBJLoader.loadObjModel("cactus3", loader),
				new ModelTexture(loader.loadTexture("tree")));
		texturedFern.getTexture().setHasTransparency(true);
		//texturedFern.getTexture().setUseFakeLighting(true);
		
		TexturedModel bank_booth = new TexturedModel(OBJLoader.loadObjModel("bank_booth", loader),
				new ModelTexture(loader.loadTexture("box")));
		
		TexturedModel zaros_altar3 = new TexturedModel(OBJLoader.loadObjModel("zaros_altar3", loader),
				new ModelTexture(loader.loadTexture("dark_grey")));
		
		TexturedModel rocks = new TexturedModel(OBJLoader.loadObjModel("rocks", loader),
				new ModelTexture(loader.loadTexture("dark_grey")));
		
		TexturedModel dummy = new TexturedModel(OBJLoader.loadObjModel("dummy", loader),
				new ModelTexture(loader.loadTexture("dark_grey")));
		
		TexturedModel cave_entrance = new TexturedModel(OBJLoader.loadObjModel("cave_entrance", loader),
				new ModelTexture(loader.loadTexture("dark_grey")));
		
		TexturedModel obelisk = new TexturedModel(OBJLoader.loadObjModel("obelisk", loader),
				new ModelTexture(loader.loadTexture("dark_grey")));
		
		TexturedModel ahrim = new TexturedModel(OBJLoader.loadObjModel("ahrim", loader),
				new ModelTexture(loader.loadTexture("dark_grey")));
		
		Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap2");
		
		List<Entity> entities = new ArrayList<Entity>();
		entities.add(new Entity(bank_booth, new Vector3f(495, terrain.getHeightOfTerrain(495, -398.25f - 3.5f), -398.25f - 3.5f),0,0,0,0.00225f));
		entities.add(new Entity(bank_booth, new Vector3f(495, terrain.getHeightOfTerrain(495, -398.25f - 3.5f), -398.25f),0,0,0,0.00225f));
		entities.add(new Entity(bank_booth, new Vector3f(495, terrain.getHeightOfTerrain(495, -398.25f - 3.5f), -398.25f + 3.5f),0,0,0,0.00225f));
		entities.add(new Entity(zaros_altar3, new Vector3f(495 + 17, terrain.getHeightOfTerrain(495 + 17, -398.25f - 45f), -398.25f - 45f),0,0,0,0.029f));
		entities.add(new Entity(rocks, new Vector3f(495 + 55, terrain.getHeightOfTerrain(495 + 55, -398.25f - 45f), -398.25f - 45f),0,0,0,0.029f));
		entities.add(new Entity(dummy, new Vector3f(495 + 35, terrain.getHeightOfTerrain(495 + 35, -398.25f - 15f), -398.25f - 15f),0,0,0,0.029f));
		entities.add(new Entity(cave_entrance, new Vector3f(495 - 15, terrain.getHeightOfTerrain(495 - 15, -398.25f - 55f), -398.25f - 55f),0,0,0,0.029f));
		entities.add(new Entity(obelisk, new Vector3f(495 - 2, terrain.getHeightOfTerrain(495 - 2, -398.25f - 55f), -398.25f - 55f),0,0,0,0.029f));
		entities.add(new Entity(ahrim, new Vector3f(495 + 24, terrain.getHeightOfTerrain(495 + 24, -398.25f - 45f), -398.25f - 45f),0,0,0,0.029f));
		
		Random random = new Random();
		random.setSeed(2);
		int occurances = 65;
		for(int i=0;i<occurances;i++){
			float x = random.nextFloat()*1600 - 800;
			float z = random.nextFloat() * -800;
			float y = terrain.getHeightOfTerrain(x, z);
			entities.add(new Entity(texturedTree, new Vector3f(x, y, z),0,0,0,0.027f));
		}
		for(int i=0;i<occurances;i++){
			float x = random.nextFloat()*1600 - 800;
			float z = random.nextFloat() * -800;
			float y = terrain.getHeightOfTerrain(x, z);
			entities.add(new Entity(texturedGrass, new Vector3f(x, y, z), 0, 0, 0, 0.015f));
		}
		for(int i=0;i<occurances;i++){
			float x = random.nextFloat()*1600 - 800;
			float z = random.nextFloat() * -800;
			float y = terrain.getHeightOfTerrain(x, z);
			entities.add(new Entity(texturedFern, new Vector3f(x, y, z), 0, 0, 0, 0.01f));
		}

		MasterRenderer renderer = new MasterRenderer();
		
		RawModel characterModel = OBJLoader.loadObjModel("test", loader);
		TexturedModel character = new TexturedModel(characterModel, new ModelTexture(loader.loadTexture("dark_grey")));
		
		Player player = new Player(character, new Vector3f(512, -64, -400), 0, 180, 0, 0.027f);
		Camera camera = new Camera(player);
		
		// persist display until user exit
		while (!Display.isCloseRequested()) {
			//entity.increaseRotation(0, 1, 0);
			player.move(terrain); // if using multiple terrains, test which terrain the player is on and send to this method
			camera.move();
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
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
