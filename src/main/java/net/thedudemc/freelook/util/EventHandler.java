package net.thedudemc.freelook.util;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.thedudemc.freelook.FreeLook;
import net.thedudemc.freelook.client.Camera;

@EventBusSubscriber
public class EventHandler {

	public static boolean altIsDown = false;
	public static boolean last = false;

	@SubscribeEvent
	public static void onRenderTick(TickEvent.RenderTickEvent event) {
		if (FreeLook.keyBinding.isKeyDown() && last) {
			Camera.update(event.phase == Phase.START);
		}
		if (!FreeLook.keyBinding.isKeyDown() && last) {
			Camera.reset(); 
			last = false;
		}
	}

	@SubscribeEvent
	public static void onKey(KeyInputEvent event) {
		if (FreeLook.keyBinding.isPressed()) {
			if (!altIsDown) {
				Camera.setCamera();
			}
			altIsDown = true;
			last = true;
		} else {
			altIsDown = false;
		}
	}

}