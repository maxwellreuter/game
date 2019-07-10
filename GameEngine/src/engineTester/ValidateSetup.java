// Opens an empty window to validate that the project is setup correctly

package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;

public class ValidateSetup {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		// persist display until user exit
		while(!Display.isCloseRequested()) {
			
			// game logic
			//render
			DisplayManager.updateDisplay();
			
		}
		
		DisplayManager.closeDisplay();
		
	}

}
