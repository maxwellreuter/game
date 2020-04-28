// Manages the main game loop

package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;


public class ValidateSetup {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		// persist display until user exit
		while(!Display.isCloseRequested()) {
			DisplayManager.updateDisplay();
			
		}
		
		DisplayManager.closeDisplay();
		
	}

}
