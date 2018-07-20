package net.thedudemc.freelook.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

	public static Configuration config;
	public static boolean viewClamping = true;
	
	public static void init(File file) {
		
		config = new Configuration(new File("config/FreeLook.cfg"));
		config.load();

		viewClamping = config.getBoolean("view_clamping", "Clamping", viewClamping, "When true, your view will be clamped. Which means it will be as if you were looking in real life, rotation stops just past your shoulders.");
		
		config.save();
	}
	


}
