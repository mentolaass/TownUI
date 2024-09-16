package ru.mentola.townui.api;

import lombok.Getter;
import net.minecraft.util.Identifier;
import ru.mentola.townui.api.event.manager.EventManager;
import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.layer.Layer;
import ru.mentola.townui.core.render.custom.gif.GifRenderer;
import ru.mentola.townui.core.screen.TownScreen;

@Getter
public final class TownUIAPIImpl implements TownUIAPI {
    private final EventManager eventManager = new EventManager();

    @Override
    public <T extends Component> Component.ComponentBuilder<T> getComponentBuilder(Class<T> component) {
        return Component.builder(component);
    }

    @Override
    public GifRenderer createGifRenderer(Identifier id) {
        return new GifRenderer(id);
    }

    @Override
    public void registerListener(Object listener) {
        this.eventManager.register(listener);
    }

    @Override
    public void unregisterListener(Object listener) {
        this.eventManager.unregister(listener);
    }

    @Override
    public TownScreen buildScreen(String name, Layer layer) {
        return new TownScreen(name, layer);
    }
}