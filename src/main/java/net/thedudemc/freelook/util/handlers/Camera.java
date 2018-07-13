package net.thedudemc.freelook.util.handlers;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.thedudemc.freelook.proxy.ClientProxy;

public class Camera {
	static KeyBinding altKey = ClientProxy.keyBinding;
	public static boolean enabled = false;

	public static float cameraYaw = 0;
	public static float cameraPitch = 0;
	public static float playerYaw = 0;
	public static float playerPitch = 0;
	public static float originYaw = 0;
	public static float originPitch = 0;
	
	public static void update(boolean start) {
		Minecraft mc = Minecraft.getMinecraft();
		Entity player = mc.getRenderViewEntity();

		// Run updateCamera Method first..
		updateCamera();
		// at the start of the RenderTickEvent phase, do this
		if (start) {

			// sets the player's rotation and previous rotation (aka camera) to cameraYaw,
			// which was established in updateCamera
			player.rotationYaw = player.prevRotationYaw = cameraYaw;
			player.rotationPitch = player.prevRotationPitch = -cameraPitch;

			// Then at the end of the RenderTickEvent phase...
		} else {
			// set player's rotation to the client's current rotation, subtracted by camera
			// rotation - player rotation.
			player.rotationYaw = mc.player.rotationYaw - cameraYaw + playerYaw;
			player.prevRotationYaw = mc.player.prevRotationYaw - cameraYaw + playerYaw;
			// these invert the pitch so up is up, etc.
			player.rotationPitch = -playerPitch;
			player.prevRotationPitch = -playerPitch;
		}

	}

	private static void updateCamera() {
		// Minecraft client
		Minecraft mc = Minecraft.getMinecraft();

		// if not in the game, utilizing the camera... (ie. not in pause menu or other
		// gui.) do nothing.
		if (!mc.inGameHasFocus) {
			return;
		}

		// gets mouse sensitivity from the client settings. Not sure of the calculation
		// after.. Possibly mouse acceleration effect
		float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
		float f1 = f * f * f * 8.0F;

		// gets the delta of the mouse movement.. again, unsure of the calculation
		// after..
		double dx = Mouse.getDX() * f1 * 0.15D;
		double dy = Mouse.getDY() * f1 * 0.15D;

		// keybind is Left Alt.. if the player is holding that key, do the following.
		// Essentially, this method wont even be called if the player isn't holding the
		// key so this is redundant.
		if (ClientProxy.keyBinding.isKeyDown()) {
			// cameraYaw + dx then set cameraYaw to that value.. ie. 0 + 1 = 1, cameraYaw =
			// 1
			cameraYaw += dx;
			cameraPitch += dy;

			// if less than -90 or greater than 90, set to -90, 90.. etc
			cameraPitch = MathHelper.clamp(cameraPitch, -90.0F, 90.0F);
			cameraYaw = MathHelper.clamp(cameraYaw, -100.0F, 100.0F);

			// Don't think this ever gets called since this method cannot run if the key
			// isn't held to begin with
		} else {
			playerYaw += dx;
			playerPitch += dy;
			playerPitch = MathHelper.clamp(playerPitch, -90.0F, 90.0F);
			playerYaw = MathHelper.clamp(playerYaw, -100.0F, 100.0F);
		}

	}

	// ignore this for now..
	public static void reset() {
		enabled = false;
		cameraYaw = 0;
		cameraPitch = 0;
		playerYaw = 0;
		playerPitch = 0;
	}

	// should probably implement this somewhere.. we shall see.
	public void enabled() {
		Minecraft mc = Minecraft.getMinecraft();
		if (!enabled) {
			// I *think* this establishes the starting position when you first press alt..
			cameraYaw = playerYaw = mc.player.rotationYaw;
			cameraPitch = mc.player.rotationPitch;
			playerPitch = -cameraPitch;
		}
		enabled = true;
	}

}
