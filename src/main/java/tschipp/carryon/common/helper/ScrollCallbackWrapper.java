package tschipp.carryon.common.helper;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWScrollCallback;

//Thanks to gigaherz for the help!
public class ScrollCallbackWrapper
{
	GLFWScrollCallback oldCallback;

	public void setup(Minecraft mc)
	{
		this.oldCallback = GLFW.glfwSetScrollCallback(mc.getWindow().getWindow(), this::scrollCallback);
	}

	private void scrollCallback(long window, double xoffset, double yoffset)
	{
		MouseScrolledEvent event = new MouseScrolledEvent();
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled())
			return;

		if (this.oldCallback != null)
			this.oldCallback.invoke(window, xoffset, yoffset);
	}

	@Cancelable
	public static class MouseScrolledEvent extends Event
	{

	}
}
