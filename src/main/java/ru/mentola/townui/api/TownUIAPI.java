package ru.mentola.townui.api;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.layer.Layer;
import ru.mentola.townui.core.render.custom.gif.GifRenderer;
import ru.mentola.townui.core.screen.TownScreen;

public interface TownUIAPI {
    <T extends Component> Component.ComponentBuilder<T> getComponentBuilder(Class<T> component);
    /**
     * Create gif renderer (Dynamic registration is available only after full initialization of the game client, calling this method before will crash the game)
     */
    GifRenderer createGifRenderer(Identifier id);

    void registerListener(Object listener);

    void unregisterListener(Object listener);

    TownScreen buildScreen(String name, Layer layer);
}