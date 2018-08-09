package net.thedudemc.freelook.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.thedudemc.freelook.FreeLook;
import net.thedudemc.freelook.client.Camera;

@EventBusSubscriber
public class EventHandler {

	public static boolean toggleEnabled = false;
	public static boolean initialPress = false;
	public static boolean freelookEnabled = false;
	public static boolean disabled = true;
	
	@SubscribeEvent
	public static void onRespawn(PlayerRespawnEvent e) {
		initialPress = false;
		toggleEnabled = false;
		freelookEnabled = false;
		disabled = true;
		Camera.setCamera();
	}


	@SubscribeEvent
	public static void onRenderTick(TickEvent.RenderTickEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (player == null) {
			return;
		}
		if (!player.isRiding()) {
			if (!toggleEnabled) {
				if (FreeLook.keyFreeLook.isKeyDown() && !initialPress) {
					Camera.setCamera();
					initialPress = true;
				}
				if (FreeLook.keyFreeLook.isKeyDown() && initialPress) {
					Camera.update(event.phase == Phase.START);
				}
				if (!FreeLook.keyFreeLook.isKeyDown() && initialPress) {
					Camera.resetCamera();
					initialPress = false;
				}
			} else {
				if (freelookEnabled) {
					Camera.cameraEnabled(event.phase == Phase.START);
				} else {
					Camera.resetCamera();
				}
			}
		}
	}

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (FreeLook.keyToggleMode.isPressed()) {
			if (!toggleEnabled) {
				toggleEnabled = true;
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString(TextFormatting.GREEN + "FreeLook Mode: Toggle"));

			} else {
				toggleEnabled = false;
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString(TextFormatting.GREEN + "FreeLook Mode: Hold"));

			}
		}
		if (player == null) {
			return;
		}
		if (!player.isRiding()) {

			if (FreeLook.keyFreeLook.isPressed() && toggleEnabled) {
				if (!freelookEnabled) {
					freelookEnabled = true;
				} else {
					freelookEnabled = false;
				}
			}
		}
	}

}