package net.thedudemc.freelook.util.handlers;

import org.lwjgl.input.Mouse;

import net.minecraft.util.MouseHelper;

public class MouseHandler extends MouseHelper {

	@Override
    public void mouseXYChange()
    {
        this.deltaX = Mouse.getDX();
        this.deltaY = Mouse.getDY();
    }
	
}
