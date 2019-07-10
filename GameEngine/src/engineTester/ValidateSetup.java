// Manages the main game loop

package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class ValidateSetup {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		// OpenGL expects vertices to be defined counter-clockwise by default
		float[] vertices = {
				-0.5f, 0.5f, 0, 	// V0
				-0.5f, -0.5f, 0,	// V1
				0.5f, -0.5f, 0, 	// V2
				0.5f, 0.5f, 0 		// V3
		};
		
		int[] indices = {
				0, 1, 3,	// top-left triangle (V0, V1, V3)
				3, 2, 1		// bottom-right triangle (V3, V1, V2)
		};
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		// persist display until user exit
		while(!Display.isCloseRequested()) {
			// game logic
			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
