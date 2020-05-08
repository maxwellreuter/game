package terrains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import entities.Light;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class Scene {
	
	Vector3f DESERT_FOG = new Vector3f(94/(float)256, 178/(float)256, 128/(float)256); 	// r, g, b
	private Vector3f fog;
	private MasterRenderer renderer;
	private List<Entity> entities;
	private Terrain terrain;
	private Light light;
	
	public Scene(String scene) {
		this.renderer = new MasterRenderer();
		if(scene == "grassy") {
			createGrassyScene();
		}else if(scene == "desert") {
			createDesertScene();
		}
	}
	
	public Vector3f getSkyColor() {
		return fog;
	}
	
	public Vector3f getFogColor() {
		return fog;
	}
	
	public Light getLight() {
		return this.light;
	}
	
	private void createGrassyScene() {
		this.fog = new Vector3f(135/(float)256, 206/(float)256, 228/(float)256); // r, g, b
		float treeScale = 5;
		float grassScale = 1.5f;
		float fernScale = 0.7f;
		
		Loader loader = new Loader();
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		
		TexturedModel tree = new TexturedModel(
				OBJLoader.loadObjModel("tree", loader),
				new ModelTexture(loader.loadTexture("tree")));
		
		TexturedModel grass = new TexturedModel(
				OBJLoader.loadObjModel("grassModel", loader),
				new ModelTexture(loader.loadTexture("grassTexture")));
		grass.getTexture().setHasTransparency(true);
		grass.getTexture().setUseFakeLighting(true);
		
		TexturedModel fern = new TexturedModel(
				OBJLoader.loadObjModel("fern", loader),
				new ModelTexture(loader.loadTexture("fern")));
		fern.getTexture().setHasTransparency(true);
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture,
				gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		
		Light light = new Light(new Vector3f(0, 90000, 0), new Vector3f(1, 1, 1));
	
		
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
		//List<Entity> entities = new ArrayList<Entity>();
		
		Entity rocks_entity = new Entity(rocks, new Vector3f(495 + 55, terrain.getHeightOfTerrain(495 + 55, -398.25f - 45f), -398.25f - 45f),0,0,0,0.035f);
		entities.add(new Entity(bank_booth, new Vector3f(495, terrain.getHeightOfTerrain(495, -398.25f - 3.5f), -398.25f - 3.5f),0,0,0,0.00225f));
		entities.add(new Entity(bank_booth, new Vector3f(495, terrain.getHeightOfTerrain(495, -398.25f - 3.5f), -398.25f),0,0,0,0.00225f));
		entities.add(new Entity(bank_booth, new Vector3f(495, terrain.getHeightOfTerrain(495, -398.25f - 3.5f), -398.25f + 3.5f),0,0,0,0.00225f));
		entities.add(new Entity(zaros_altar3, new Vector3f(495 + 17, terrain.getHeightOfTerrain(495 + 17, -398.25f - 45f), -398.25f - 45f),0,0,0,0.029f));
		entities.add(new Entity(rocks, new Vector3f(495 + 55, terrain.getHeightOfTerrain(495 + 55, -398.25f - 45f), -398.25f - 45f),0,0,0,0.029f));
		entities.add(new Entity(dummy, new Vector3f(495 + 35, terrain.getHeightOfTerrain(495 + 35, -398.25f - 15f), -398.25f - 15f),0,0,0,0.029f));
		entities.add(new Entity(cave_entrance, new Vector3f(495 - 15, terrain.getHeightOfTerrain(495 - 15, -398.25f - 55f), -398.25f - 55f),0,0,0,0.029f));
		entities.add(new Entity(obelisk, new Vector3f(495 - 2, terrain.getHeightOfTerrain(495 - 2, -398.25f - 55f), -398.25f - 55f),0,0,0,0.029f));
		entities.add(new Entity(ahrim, new Vector3f(495 + 24, terrain.getHeightOfTerrain(495 + 24, -398.25f - 45f), -398.25f - 45f),0,0,0,0.029f));
		entities.add(rocks_entity);
		
		Random random = new Random();
		random.setSeed(2);
		int occurances = 65;
		for(int i=0;i<occurances;i++){
			float x = random.nextFloat()*1600 - 800;
			float z = random.nextFloat() * -800;
			float y = terrain.getHeightOfTerrain(x, z);
			entities.add(new Entity(tree, new Vector3f(x, y, z),0,0,0, treeScale));
		}
		for(int i=0;i<occurances;i++){
			float x = random.nextFloat()*1600 - 800;
			float z = random.nextFloat() * -800;
			float y = terrain.getHeightOfTerrain(x, z);
			entities.add(new Entity(grass, new Vector3f(x, y, z), 0, 0, 0, grassScale));
		}
		for(int i=0;i<occurances;i++){
			float x = random.nextFloat()*1600 - 800;
			float z = random.nextFloat() * -800;
			float y = terrain.getHeightOfTerrain(x, z);
			entities.add(new Entity(fern, new Vector3f(x, y, z), 0, 0, 0, fernScale));
		}
		
		this.entities = entities;
		renderer.setSkyColor(fog.x, fog.y, fog.z);
		renderer.setFogColor(fog.x, fog.y, fog.z);
		this.light = light;
		this.terrain = terrain;

	}
	
	private void createDesertScene() {
		// TODO
	}

	public List<Entity> getEntities() {
		return this.entities;
	}

	public Terrain getTerrain() {
		return this.terrain;
	}

}
