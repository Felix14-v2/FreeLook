package net.thedudemc.freelook.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	public static KeyBinding[] keyBindings;

	public static void init() {
		keyBindings = new KeyBinding[1];
		keyBindings[0] = new KeyBinding("key.freelook.desc", Keyboard.KEY_LMENU, "key.freelook.category");
		
		for (int i = 0; i < keyBindings.length; ++i) {
			ClientRegistry.registerKeyBinding(keyBindings[i]);
		}
	}

}
