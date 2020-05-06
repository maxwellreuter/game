package toolbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Mouse;

public class GameMouse {
	
	private static boolean[] isClickedDown = new boolean[3];
	
	public static boolean isLeftButtonDown() {
		return Mouse.isButtonDown(0);
	}
	
	public static boolean isRightButtonDown() {
		return Mouse.isButtonDown(1);
	}
	
	public static boolean isMiddleButtonDown() {
		return Mouse.isButtonDown(2);
	}
	
	public static boolean leftClick() {
		return isClicked(0);
	}
	
	public static boolean rightClick() {
		return isClicked(1);
	}
	
	public static boolean middleClick() {
		return isClicked(2);
	}
	
	private static boolean isClicked(int button) {
		if(Mouse.isButtonDown(button) && !isClickedDown[button]) {
			//click down occurring
			isClickedDown[button] = true;
			return true;
		}else if(Mouse.isButtonDown(button) && isClickedDown[button]) {
			//button is down but already clicked
			return false;
		}else{
			//button isn't down
			isClickedDown[button] = false;
			return false;
		}
	}
	
}
