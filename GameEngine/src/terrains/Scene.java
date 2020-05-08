package terrains;

import java.util.ArrayList;
import java.util.List;
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
import toolbox.Triplet;

public class Scene {
	
	private MasterRenderer renderer;
	private List<Entity> entities;
	private Terrain terrain;
	private Light light;
	private Loader loader;
	private TerrainTexturePack texturePack;
	private TerrainTexture blendMap;
	
	public Scene(String scene) {
		this.renderer = new MasterRenderer();
		this.loader = new Loader();
		if(scene == "grassy") {
			createGrassyScene();
		}else if(scene == "desert") {
			createDesertScene();
		}else if(scene == "snowy") {
			createSnowyScene();
		}else if(scene == "swamp") {
			createSwampScene();
		}else if(scene == "night") {
			createNightScene();
		}
	}
	
	public Light getLight() {
		return this.light;
	}
	
	public List<Entity> getEntities() {
		return this.entities;
	}

	public Terrain getTerrain() {
		return this.terrain;
	}
	
	private void createGrassyScene() {
		
		List<String> terrainTextures = new ArrayList<String>();
		terrainTextures.add("grassy"); 		// background texture
		terrainTextures.add("dirt"); 		// r texture
		terrainTextures.add("pinkFlowers"); // g texture
		terrainTextures.add("path"); 		// b texture
		
		String blendMapFilename = "blendMap";
		String heightMapFilename = "heightmap2";
		
		Vector3f fog = new Vector3f(135/(float)256, 206/(float)256, 228/(float)256); // r, g, b
		
		List<Triplet<String, String, Float>> dynamicEntityDefinitions = new ArrayList<Triplet<String, String, Float>>();
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("tree", "tree", 5f));
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("grassModel", "grassTexture", 1.5f));
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("fern", "fern", 0.7f));
		
		int dynamicSeed = 2;
		int dynamicOccurances = 65;
		
		createScene(
				terrainTextures,
				blendMapFilename, heightMapFilename,
				fog,
				dynamicEntityDefinitions,
				dynamicSeed, dynamicOccurances
		);

	}
	
	private void createDesertScene() {
		
		List<String> terrainTextures = new ArrayList<String>();
		terrainTextures.add("sand"); // background texture
		terrainTextures.add("sand"); // r texture
		terrainTextures.add("sand"); // g texture
		terrainTextures.add("sand"); // b texture
		
		String blendMapFilename = "blendMap";
		String heightMapFilename = "heightmap2";
		
		Vector3f fog = new Vector3f(194/(float)256, 178/(float)256, 128/(float)256); // r, g, b
		
		List<Triplet<String, String, Float>> dynamicEntityDefinitions = new ArrayList<Triplet<String, String, Float>>();
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("cactus", "tree", 0.027f));
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("cactus2", "tree", 0.015f));
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("cactus3", "tree", 0.01f));
		
		int dynamicSeed = 2;
		int dynamicOccurances = 65;
		
		createScene(
				terrainTextures,
				blendMapFilename, heightMapFilename,
				fog,
				dynamicEntityDefinitions,
				dynamicSeed, dynamicOccurances
		);
		
	}

	private void createSnowyScene() {
		
		List<String> terrainTextures = new ArrayList<String>();
		terrainTextures.add("snowy"); // background texture
		terrainTextures.add("snowy"); // r texture
		terrainTextures.add("snowy"); // g texture
		terrainTextures.add("snowy"); // b texture
		
		String blendMapFilename = "blendMap";
		String heightMapFilename = "heightmap2";
		
		Vector3f fog = new Vector3f(255/(float)256, 250/(float)256, 250/(float)256); // r, g, b
		
		List<Triplet<String, String, Float>> dynamicEntityDefinitions = new ArrayList<Triplet<String, String, Float>>();
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("small_rocks", "light_blue", 0.047f));
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("ground_spike", "light_blue", 0.035f));
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("big_rocks", "dark_grey", 0.047f));
		
		int dynamicSeed = 4;
		int dynamicOccurances = 65;
		
		createScene(
				terrainTextures,
				blendMapFilename, heightMapFilename,
				fog,
				dynamicEntityDefinitions,
				dynamicSeed, dynamicOccurances
		);
		
	}

	private void createSwampScene() {
		
		List<String> terrainTextures = new ArrayList<String>();
		terrainTextures.add("swampy"); // background texture
		terrainTextures.add("swampy"); // r texture
		terrainTextures.add("swampy"); // g texture
		terrainTextures.add("swampy"); // b texture
		
		String blendMapFilename = "blendMap";
		String heightMapFilename = "heightmap2";
		
		Vector3f fog = new Vector3f(35/(float)256, 60/(float)256, 35/(float)256); // r, g, b
		
		List<Triplet<String, String, Float>> dynamicEntityDefinitions = new ArrayList<Triplet<String, String, Float>>();
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("grass_and_rocks", "swampy2", 0.1f));
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("mushroom", "brown", 0.035f));
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("hallow_tree", "dark_brown", 0.027f));
		
		int dynamicSeed = 1;
		int dynamicOccurances = 500;
		
		createScene(
				terrainTextures,
				blendMapFilename, heightMapFilename,
				fog,
				dynamicEntityDefinitions,
				dynamicSeed, dynamicOccurances
		);
		
	}
	
	private void createNightScene() {
		
		List<String> terrainTextures = new ArrayList<String>();
		terrainTextures.add("grass_night"); // background texture
		terrainTextures.add("grass_night"); // r texture
		terrainTextures.add("grass_night"); // g texture
		terrainTextures.add("grass_night"); // b texture
		
		String blendMapFilename = "blendMap";
		String heightMapFilename = "heightmap2";
		
		Vector3f fog = new Vector3f(6/(float)256, 6/(float)256, 6/(float)256); // r, g, b
		
		List<Triplet<String, String, Float>> dynamicEntityDefinitions = new ArrayList<Triplet<String, String, Float>>();
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("grass_and_rocks", "swampy2", 0.1f));
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("dead_tree", "dark_brown", 0.035f));
		dynamicEntityDefinitions.add(new Triplet<String, String, Float>("leafless_tree", "dark_brown", 0.027f));
		
		int dynamicSeed = 7;
		int dynamicOccurances = 500;
		
		createScene(
				terrainTextures,
				blendMapFilename, heightMapFilename,
				fog,
				dynamicEntityDefinitions,
				dynamicSeed, dynamicOccurances
		);
		
	}
	
	private void createScene(
			List<String> terrainTextures,
			String blendMapFilename, String heightMapFilename,
			Vector3f fog,
			List<Triplet<String, String, Float>> dynamicEntityDefinitions,
			int dynamicSeed, int dynamicOccurances
		) {
		
		// lighting
		this.light = new Light(new Vector3f(0, 90000, 0), new Vector3f(1, 1, 1));
		
		// define terrain texturing
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture(terrainTextures.get(0)));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture(terrainTextures.get(1)));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture(terrainTextures.get(2)));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture(terrainTextures.get(3)));
		this.texturePack = new TerrainTexturePack(
				backgroundTexture, rTexture, gTexture, bTexture);
		this.blendMap = new TerrainTexture(loader.loadTexture(blendMapFilename));
		this.terrain = new Terrain(0, -1, loader, texturePack, blendMap, heightMapFilename);
		
		renderer.setSkyColor(fog.x, fog.y, fog.z);
		renderer.setFogColor(fog.x, fog.y, fog.z);
		
		// load static entities
		loadStaticSceneEntities();
		
		// define dynamic entities
		List<TexturedModel> dynamicTexturedModels = new ArrayList<TexturedModel>();
		List<Float> scales = new ArrayList<Float>();
		
		for(Triplet<String, String, Float> d:dynamicEntityDefinitions) {
			dynamicTexturedModels.add(new TexturedModel(
					OBJLoader.loadObjModel(d.first, loader),
					new ModelTexture(loader.loadTexture(d.second))));
			scales.add(d.third);
		}
		
		// determines number of occurrences and positions of dynamic entities
		Random random = new Random();
		random.setSeed(dynamicSeed);
		int occurances = dynamicOccurances;
		
		// render dynamic entities
		for(int i = 0; i < dynamicTexturedModels.size(); i++) {
			TexturedModel model = dynamicTexturedModels.get(i);
			float scale = scales.get(i);
			for(int j=0;j<occurances;j++){
				float x = random.nextFloat() * 1600 - 800;
				float z = random.nextFloat() * -800;
				float y = terrain.getHeightOfTerrain(x, z);
				entities.add(new Entity(model, new Vector3f(x, y, z), 0, 0, 0, scale));
			}
		}
	}
	
	private void loadStaticSceneEntities() {
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
		
		this.entities = new ArrayList<Entity>();
		
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
	}
}
