package net.thedudemc.freelook.util;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.thedudemc.freelook.client.Camera;
import net.thedudemc.freelook.client.ClientProxy;

@EventBusSubscriber
public class EventHandler {

	public static boolean altIsDown = false;
	// private static long lastAltClick = 0;
	// private static boolean altIsPressed = false;

	@SubscribeEvent
	public static void onRenderTick(TickEvent.RenderTickEvent event) {
		Camera.update(event.phase == Phase.START);
	}

	@SubscribeEvent
	public static void onKey(KeyInputEvent event) {
		if (ClientProxy.keyBinding.isKeyDown()) {
			if (!altIsDown) {
				Camera.setCamera();
			}
			altIsDown = true;
		} else {
			altIsDown = false;
		}
	}

}