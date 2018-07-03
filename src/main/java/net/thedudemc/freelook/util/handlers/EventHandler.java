package net.thedudemc.freelook.util.handlers;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec2f;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.thedudemc.freelook.proxy.ClientProxy;

@EventBusSubscriber
public class EventHandler {

	public static boolean keyPressed = false;

	/*
	 * @SubscribeEvent public void onRenderTick(TickEvent.RenderTickEvent event) {
	 * if (event.phase == Phase.START || Minecraft.getMinecraft().isGamePaused()) {
	 * return; } float partialTicks = event.renderTickTime; float yaw =
	 * Mouse.getDX() + (Mouse.getDX() * partialTicks); float pitch = Mouse.getDY() +
	 * (Mouse.getDY() * partialTicks); //if (yaw > 180.0f) { yaw -= 360.0f; } //else
	 * if (yaw < -180.0f) { yaw += 360.0f; }
	 * 
	 * EntityPlayer p = Minecraft.getMinecraft().player;
	 * //p.setLocationAndAngles(p.posX, p.posY, p.posZ, yaw, pitch); p.rotationYaw =
	 * yaw; p.prevRotationYaw = yaw; p.rotationPitch = pitch; p.prevRotationPitch =
	 * pitch; System.out.println("Shit is happening.."); }
	 */

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event) {

		KeyBinding[] keyBindings = ClientProxy.keyBindings;

		if (keyBindings[0].isPressed()) {
			keyPressed = true;

		} else {
			keyPressed = false;
		}
	}

	@SubscribeEvent
	public static void controlCamera(CameraSetup e) {

		if (keyPressed == true) {

			if (e.getEntity() instanceof EntityPlayer) {
				
				float sens = Minecraft.getMinecraft().gameSettings.mouseSensitivity;
				
				float originalX = Mouse.getX(); 		System.out.println("Original X position is: " + originalX);				
				float originalY = Mouse.getY(); 		System.out.println("Original Y position is: " + originalY);				
				float newX = Mouse.getX(); 				System.out.println("New X position is: " + newX);				
				float newY = Mouse.getY(); 				System.out.println("New Y position is: " + newY);				
				float diffX = originalX - newX; 		System.out.println("Difference between original and new X: " + diffX);				
				float diffY = originalY - newY; 		System.out.println("Difference between original and new Y: " + diffY);				
				float sensX = diffX * sens; 			System.out.println("Difference of X as affected by sensitivity: " + sensX);				
				float sensY = diffY * sens; 			System.out.println("Difference of Y as affected by sensitivity: " + sensY);				
				float x = newX + sensX; 				System.out.println("New camera position to move cursor on X: " + x);				
				float y = newY + sensY; 				System.out.println("New camera position to move cursor on Y: " + y);

				Vec2f vec = e.getEntity().getPitchYaw();
				System.out.println("Vec thing is: " + vec);
				
			}
		}

	}

}
