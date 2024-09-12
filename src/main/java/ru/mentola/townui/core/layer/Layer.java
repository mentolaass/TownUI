package ru.mentola.townui.core.layer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.mouse.Mouse;
import ru.mentola.townui.core.mouse.MouseButton;
import ru.mentola.townui.util.Util;

import java.util.LinkedHashSet;

@AllArgsConstructor @Getter @Setter
public class Layer {
    private final LinkedHashSet<Component> components = new LinkedHashSet<>();
    private double x;
    private double y;
    private double width;
    private double height;
    /**
     * Обновление ширины и высоты в зависимости от размера окна игры
     */
    private boolean autosize;

    public Layer addComponent(Component component) {
        components.add(component);
        component.setParentLayer(this);
        return this;
    }

    public Layer removeComponent(Component component) {
        components.remove(component);
        component.setParentLayer(null);
        return this;
    }

    public void render(DrawContext context, Mouse mouse, float delta) {
        if (autosize) {
            this.width = MinecraftClient.getInstance()
                    .getWindow()
                    .getScaledWidth();
            this.height = MinecraftClient.getInstance()
                    .getWindow()
                    .getScaledHeight();
        }
        context.enableScissor((int) x, (int) y, (int) (x + width), (int) (y + height));
        for (Component component : components)
            component.render(context, mouse, delta);
        context.disableScissor();
    }

    public void mouseClicked(Mouse mouse, MouseButton button) {
        if (!Util.pointedTo(this.x, this.y, this.width, this.height, mouse))
            return;
        for (Component component : components)
            component.mouseClicked(mouse, button);
    }

    public void resize() {
        for (Component component : components) component.resize();
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        for (Component component : components)
            component.keyPressed(keyCode, scanCode, modifiers);
    }
}