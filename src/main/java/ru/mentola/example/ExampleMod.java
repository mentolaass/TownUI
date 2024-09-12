package ru.mentola.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.mentola.example.testscreen.TestScreen;

public final class ExampleMod implements ClientModInitializer, ModInitializer {
    private static final KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "key.townui.example",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_RIGHT_SHIFT,
        "category.townui.example"
    ));

    @Override
    public void onInitialize() {

    }

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new TestScreen());
            }
        });
    }
}
