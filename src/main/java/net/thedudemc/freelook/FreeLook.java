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
import net.thedudemc.freelook.util.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, clientSideOnly = true)
public class FreeLook {
	
	public static KeyBinding keyBinding;

	@Instance
	public static FreeLook instance;


	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {

		keyBinding = new KeyBinding("key.freelook.desc", Keyboard.KEY_LMENU, "key.freelook.category");
		ClientRegistry.registerKeyBinding(keyBinding);
	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {

	}
}
