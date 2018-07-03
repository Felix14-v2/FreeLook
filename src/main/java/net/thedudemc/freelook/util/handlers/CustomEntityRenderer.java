package net.thedudemc.freelook.util.handlers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;

public class CustomEntityRenderer extends EntityRenderer {
	private final Minecraft mc;

	float offsetY = -1.05F;

	public CustomEntityRenderer(Minecraft mc) {
		super(mc, mc.getResourceManager());
		this.mc = mc;
	}

	public void hurtCameraEffect(float p_78482_1_) {
	}

	@Override
	public void renderWorld(float p_78471_1_, long p_78471_2_) {
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glRotatef(90, 0, 0, 1);
		super.renderWorld(p_78471_1_, p_78471_2_);
	}

	@Override
	public void updateCameraAndRender(float partialTick, long nanoTime) {
		hurtCameraEffect(partialTick);

		EntityPlayer player = mc.player;

		if (player == null || player.isPlayerSleeping()) {
			super.updateCameraAndRender(partialTick, nanoTime);
			return;
		}

		GL11.glRotatef(90, 1, 0, 0);

		super.updateCameraAndRender(partialTick, nanoTime);
	}

	@Override
	public void getMouseOver(float partialTick) {
		super.getMouseOver(partialTick);
	}
}