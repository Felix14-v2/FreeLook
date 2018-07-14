package net.thedudemc.freelook.client;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.thedudemc.freelook.FreeLook;

public class Camera {
	public static boolean enabled = false;

	public static float cameraYaw = 0;
	public static float cameraPitch = 0;
	public static float playerYaw = 0;
	public static float playerPitch = 0;
	public static float originalYaw = 0;
	public static float originalPitch = 0;

	public static float cameraDistance = 0;

	public static void update(boolean start) {
		Minecraft mc = Minecraft.getMinecraft();
		Entity player = mc.getRenderViewEntity();
		if (player == null) {
			return;
		}
		if(enabled) {
			updateCamera();
			if (start) {

				player.rotationYaw = player.prevRotationYaw = cameraYaw;
				player.rotationPitch = player.prevRotationPitch = -cameraPitch;

			} else {
				player.rotationYaw = mc.player.rotationYaw - cameraYaw + playerYaw;
				player.prevRotationYaw = mc.player.prevRotationYaw - cameraYaw + playerYaw;

				player.rotationPitch = -playerPitch;
				player.prevRotationPitch = -playerPitch;
			}
		}

	}

	private static void updateCamera() {
		Minecraft mc = Minecraft.getMinecraft();

		if (!mc.inGameHasFocus && !enabled) {
			return;
		}

		float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
		float f1 = f * f * f * 8.0F;

		double dx = Mouse.getDX() * f1 * 0.15D;
		double dy = Mouse.getDY() * f1 * 0.15D;

		if (FreeLook.keyBinding.isKeyDown()) {

			cameraYaw += dx;
			cameraPitch += dy;

			cameraPitch = MathHelper.clamp(cameraPitch, -90.0F, 90.0F);
			cameraYaw = MathHelper.clamp(cameraYaw, (originalYaw + -100.0F), (originalYaw + 100.0F));

		} else {

		}

	}

	public static void reset() {
		cameraYaw = originalYaw;
		cameraPitch = originalPitch;
		playerYaw = originalYaw;
		playerPitch = originalPitch;
	}

	public static void setCamera() {
		Minecraft mc = Minecraft.getMinecraft();

		cameraYaw = playerYaw = originalYaw = mc.player.rotationYaw;
		cameraPitch = originalPitch = -mc.player.rotationPitch;
		playerPitch = cameraPitch;

		enabled = true;

	}

}
