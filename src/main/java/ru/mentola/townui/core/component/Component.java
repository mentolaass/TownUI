package ru.mentola.townui.core.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mentola.townui.core.layer.Layer;
import ru.mentola.townui.core.mouse.Mouse;
import ru.mentola.townui.core.mouse.MouseButton;
import ru.mentola.townui.core.processor.ProcessType;
import ru.mentola.townui.core.processor.Processor;
import ru.mentola.townui.core.property.FieldProperty;
import ru.mentola.townui.core.property.Properties;
import ru.mentola.townui.core.property.Property;
import ru.mentola.townui.core.reflect.Reflection;
import ru.mentola.townui.core.texture.Texture;
import ru.mentola.townui.util.Util;

import java.util.HashSet;
import java.util.Set;

@Setter @Getter
public abstract class Component {
    @FieldProperty(name="x")
    private double x;
    @FieldProperty(name="y")
    private double y;
    @FieldProperty(name="height")
    private double height;
    @FieldProperty(name="width")
    private double width;
    @FieldProperty(name="autosize")
    private boolean autosize;
    /**
     * Имя компонента
     */
    @FieldProperty(name="name")
    private String name;
    /**
     * Слой в котором находится компонент
     */
    @FieldProperty(name="parentLayer")
    private @Nullable Layer parentLayer;
    /**
     * Обработчик событий для компонента
     */
    @FieldProperty(name="processor")
    private @Nullable Processor<Component> processor;

    // state
    private boolean hover;

    public void render(DrawContext context, Mouse mouse, float delta) {
        if (autosize) {
            this.setWidth(MinecraftClient.getInstance()
                    .getWindow()
                    .getScaledWidth());
            this.setHeight(MinecraftClient.getInstance()
                    .getWindow()
                    .getScaledHeight());
        }
        if (this.processor != null) {
            if (Util.pointedToObject(this, mouse)) {
                if (!this.hover) {
                    this.processor.handle(this, ProcessType.MOUSE_ENTER, context, mouse, delta);
                    this.hover = true;
                }
            } else {
                if (this.hover) {
                    this.processor.handle(this, ProcessType.MOUSE_LEAVE, context, mouse, delta);
                    this.hover = false;
                }
            }
            this.processor.handle(this, ProcessType.RENDER, context, mouse, delta);
        }
    }

    public void mouseClicked(Mouse mouse, MouseButton button) {
        if (Util.pointedToObject(this, mouse)) {
            if (this.processor == null) return;
            this.processor.handle(this, ProcessType.MOUSE_CLICK, button);
        }
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.processor == null) return;
        this.processor.handle(this, ProcessType.KEY_PRESS, keyCode);
    }

    public void resize() {
        if (this.processor == null) return;
        this.processor.handle(this, ProcessType.RESIZE);
    }

    public static <T extends Component> ComponentBuilder<T> builder(Class<T> type) {
        try {
            return new ComponentBuilder<T>(type.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException("No find component " + type + " constructor");
        }
    }

    @RequiredArgsConstructor
    public static class ComponentBuilder<T extends Component> {
        private final Set<Property<?>> properties = new HashSet<>();
        private final T t;

        public ComponentBuilder<T> setName(String name) {
            t.setName(name);
            return this;
        }

        public ComponentBuilder<T> setX(double x) {
            t.setX(x);
            return this;
        }

        public ComponentBuilder<T> setY(double y) {
            t.setY(y);
            return this;
        }

        public ComponentBuilder<T> setWidth(double width) {
            t.setWidth(width);
            return this;
        }

        public ComponentBuilder<T> setHeight(double height) {
            t.setHeight(height);
            return this;
        }

        public ComponentBuilder<T> setProcessor(Processor<Component> processor) {
            t.setProcessor(processor);
            return this;
        }

        public ComponentBuilder<T> setAutosize(boolean autosize) {
            t.setAutosize(autosize);
            return this;
        }

        public ComponentBuilder<T> putProperty(@Nullable Property<?> property) {
            if (property != null) properties.add(property);
            return this;
        }

        public T build() {
            Reflection.putPropertiesToAnnotatedComponent(this.properties, this.t);
            return this.t;
        }
    }
}