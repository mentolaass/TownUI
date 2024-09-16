package ru.mentola.townui.core.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import ru.mentola.townui.core.layer.Layer;
import ru.mentola.townui.core.mouse.Mouse;
import ru.mentola.townui.core.mouse.MouseButton;

public final class TownScreen extends Screen {
    private final Layer layer;

    public TownScreen(String name, Layer layer) {
        super(Text.of(name));
        this.layer = layer;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        layer.render(context, new Mouse(mouseX, mouseY), delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        layer.mouseClicked(new Mouse(mouseX, mouseY), button == 0 ? MouseButton.LEFT : button == 1 ? MouseButton.RIGHT : button == 2 ? MouseButton.MIDDLE : MouseButton.UNKNOWN);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        layer.keyPressed(keyCode, scanCode, modifiers);
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
