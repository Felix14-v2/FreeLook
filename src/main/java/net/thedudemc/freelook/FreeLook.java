package net.thedudemc.freelook;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.thedudemc.freelook.proxy.ClientProxy;
import net.thedudemc.freelook.proxy.CommonProxy;
import net.thedudemc.freelook.util.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class FreeLook {

	@Instance
	public static FreeLook instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
	ClientProxy.init();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
	System.out.println("The sensitivity for the player is " + (Minecraft.getMinecraft().gameSettings.mouseSensitivity * 200));
	}
}
