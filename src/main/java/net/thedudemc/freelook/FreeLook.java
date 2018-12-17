package net.thedudemc.freelook;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.thedudemc.freelook.util.Config;
import net.thedudemc.freelook.util.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, clientSideOnly = true)
public class FreeLook {

	public static KeyBinding keyFreeLook;
	public static KeyBinding keyToggleMode;

	@Instance
	public static FreeLook instance;

	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		Config.init(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		keyFreeLook = new KeyBinding("key.freelook.desc", Keyboard.KEY_LMENU, "key.freelook.category");
		keyToggleMode = new KeyBinding("key.togglemode.desc", Keyboard.KEY_RMENU, "key.freelook.category");
		ClientRegistry.registerKeyBinding(keyFreeLook);
		ClientRegistry.registerKeyBinding(keyToggleMode);
	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {

	}
}
