package net.thedudemc.freelook.util.handlers;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

@EventBusSubscriber
public class EventHandler {


	// private static long lastAltClick = 0;
	// private static boolean altIsPressed = false;

	@SubscribeEvent
	public static void onRenderTick(TickEvent.RenderTickEvent event) {

		Camera.update(event.phase == Phase.START);

	}

	/*
	 * public static Camera camera = new Camera();
	 * 
	 * @SubscribeEvent public void onCamera(EntityViewRenderEvent.CameraSetup
	 * event){
	 * 
	 * Minecraft mc = Minecraft.getMinecraft(); Entity entity = event.getEntity();
	 * if (entity instanceof EntityLivingBase &&
	 * ((EntityLivingBase)entity).isPlayerSleeping() ||
	 * mc.gameSettings.thirdPersonView != 1){ return; } float f =
	 * entity.getEyeHeight(); double partialTicks = event.getRenderPartialTicks();
	 * double d0 = entity.prevPosX + (entity.posX - entity.prevPosX) *
	 * (double)partialTicks; double d1 = entity.prevPosY + (entity.posY -
	 * entity.prevPosY) * (double)partialTicks + (double)f; double d2 =
	 * entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)partialTicks;
	 * double d3 = camera.cameraDistance - 4;
	 * 
	 * float f1 = entity.rotationYaw; float f2 = entity.rotationPitch;
	 * 
	 * double d4 = (double)(-MathHelper.sin(f1 * 0.017453292F) * MathHelper.cos(f2 *
	 * 0.017453292F)) * d3; double d5 = (double)(MathHelper.cos(f1 * 0.017453292F) *
	 * MathHelper.cos(f2 * 0.017453292F)) * d3; double d6 =
	 * (double)(-MathHelper.sin(f2 * 0.017453292F)) * d3;
	 * 
	 * for (int i = 0; i < 8; ++i) { float f3 = (float)((i & 1) * 2 - 1); float f4 =
	 * (float)((i >> 1 & 1) * 2 - 1); float f5 = (float)((i >> 2 & 1) * 2 - 1); f3 =
	 * f3 * 0.1F; f4 = f4 * 0.1F; f5 = f5 * 0.1F; RayTraceResult raytraceresult =
	 * mc.world.rayTraceBlocks(new Vec3d(d0 + (double)f3, d1 + (double)f4, d2 +
	 * (double)f5), new Vec3d(d0 - d4 + (double)f3 + (double)f5, d1 - d6 +
	 * (double)f4, d2 - d5 + (double)f5));
	 * 
	 * if (raytraceresult != null) { double d7 =
	 * raytraceresult.hitVec.distanceTo(new Vec3d(d0, d1, d2));
	 * 
	 * if (d7 < d3) { d3 = d7; } } } GlStateManager.rotate(entity.rotationPitch -
	 * f2, 1.0F, 0.0F, 0.0F); GlStateManager.rotate(entity.rotationYaw - f1, 0.0F,
	 * 1.0F, 0.0F); GlStateManager.translate(0.0F, 0.0F, (float)(-d3));
	 * GlStateManager.rotate(f1 - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
	 * GlStateManager.rotate(f2 - entity.rotationPitch, 1.0F, 0.0F, 0.0F); }
	 */
}