package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;
import terrains.Terrain;
import java.lang.Math;

public class Player extends Entity {
	
	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 30;
	
	private static final float TERRAIN_HEIGHT =  0;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	private boolean hasTarget = false;
	
	private float dx1;
	private float dy1;
	private float dz1;
	
	private boolean isInAir = false;

	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	public void move(Terrain terrain) {
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if(super.getPosition().y<terrainHeight) {
			upwardsSpeed = 0;
			isInAir = false;
			super.getPosition().y = terrainHeight;
		}
		
		if(super.getTargetPosition() != null) {
			Vector3f targetPosition = super.getTargetPosition();
			Vector3f currentPosition = super.getPosition();
			
			float dx2 = (float) targetPosition.x - currentPosition.x;
			float dy2 = (float) targetPosition.y - currentPosition.y;
			float dz2 = (float) targetPosition.z - currentPosition.z;

			dx1 = (float) targetPosition.x - currentPosition.x;
			dy1 = (float) targetPosition.y - currentPosition.y;
			dz1 = (float) targetPosition.z - currentPosition.z;
			
			float sum = (float) Math.sqrt((Math.pow(Math.abs(dx1), 2) + Math.pow(Math.abs(dy1), 2) + Math.pow(Math.abs(dz1), 2)));
			
			dx1 = dx1 / sum;
			dy1 = dy1 / sum;
			dz1 = dz1 / sum;
			
			hasTarget = true;
			
			if(Math.abs(dx2) < 1 && Math.abs(dy2) < 1 && Math.abs(dz2) < 1) {
				hasTarget = false;
				super.clearTarget();
			}
			
			float speed = 0.36f;
			
			super.increasePosition(
					dx1 * speed,
					dy1 * speed,
					dz1 * speed
			);
			
			//System.out.print(newSum);
			//System.out.print("\n");
			//Implement smooth player movement for mouse picking
		}
	}
	
	private void jump() {
		if(!isInAir) {
			this.upwardsSpeed = JUMP_POWER;
			isInAir = true;
		}
	}
	
	private void checkInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = RUN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -RUN_SPEED;
		}else {
			this.currentSpeed =0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed = -TURN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.currentTurnSpeed = TURN_SPEED;
		}else {
			this.currentTurnSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
		}
	}
}
