package net.thedudemc.freelook.client;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.thedudemc.freelook.FreeLook;
import net.thedudemc.freelook.util.Config;

public class Camera {
	public static boolean enabled = false;

	public static Minecraft mc = Minecraft.getMinecraft();
	public static Entity player = mc.getRenderViewEntity();

	public static float cameraYaw = 0;
	public static float cameraPitch = 0;
	public static float playerYaw = 0;
	public static float playerPitch = 0;
	public static float originalYaw = 0;
	public static float originalPitch = 0;

	public static float cameraDistance = 0;

	public static void setCamera() {

		cameraYaw = playerYaw = originalYaw = mc.player.rotationYaw;
		cameraPitch = originalPitch = -mc.player.rotationPitch;
		playerPitch = cameraPitch;

		enabled = true;
	}

	private static void updateCamera() {

		if (!mc.inGameHasFocus && !enabled) {
			return;
		}

		float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
		float f1 = f * f * f * 8.0F;

		double dx = Mouse.getDX() * f1 * 0.15D;
		double dy = Mouse.getDY() * f1 * 0.15D;

		if (FreeLook.keyFreeLook.isKeyDown()) {

			cameraYaw += dx;
			cameraPitch += dy;
			cameraPitch = MathHelper.clamp(cameraPitch, -90.0F, 90.0F);
			if (Config.viewClamping == true) {
				cameraYaw = MathHelper.clamp(cameraYaw, (originalYaw + -100.0F), (originalYaw + 100.0F));
			}
		}
	}

	public static void resetCamera() {
		cameraYaw = originalYaw;
		cameraPitch = originalPitch;
		playerYaw = originalYaw;
		playerPitch = originalPitch;
		enabled = false;
	}

	public static void update(boolean start) {
		if (player == null) {
			return;
		}
		if (enabled) {
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

	public static void cameraEnabled(boolean start) {
		if (player == null && !mc.inGameHasFocus) {
			return;
		}
		if (mc.inGameHasFocus) {
			if (!enabled) {
				setCamera();
				enabled = true;
			}

			updateCamera2();
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

	private static void updateCamera2() {

		if (!mc.inGameHasFocus && !enabled) {
			return;
		}

		if (mc.inGameHasFocus) {

			float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
			float f1 = f * f * f * 8.0F;

			double dx = Mouse.getDX() * f1 * 0.15D;
			double dy = Mouse.getDY() * f1 * 0.15D;

			cameraYaw += dx;
			cameraPitch += dy;

			cameraPitch = MathHelper.clamp(cameraPitch, -90.0F, 90.0F);
			if (Config.viewClamping == true) {
				cameraYaw = MathHelper.clamp(cameraYaw, (originalYaw + -100.0F), (originalYaw + 100.0F));
			}
		}
	}
}
