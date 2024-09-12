package ru.mentola.townui.api;

import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.render.custom.gif.GifRenderer;

public interface TownUIAPI {
    <T extends Component> Component.ComponentBuilder<T> getComponentBuilder(Class<T> component);
    /**
     * Create gif renderer (Dynamic registration is available only after full initialization of the game client, calling this method before will crash the game)
     */
    GifRenderer createGifRenderer(String pathToGif);

    void registerListener(Object listener);

    void unregisterListener(Object listener);
}