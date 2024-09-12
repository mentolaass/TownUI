package ru.mentola.townui.core.component.common;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.Nullable;
import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.mouse.Mouse;
import ru.mentola.townui.core.mouse.MouseButton;
import ru.mentola.townui.core.property.FieldProperty;
import ru.mentola.townui.core.render.RenderHelper;
import ru.mentola.townui.core.texture.Texture;

import java.awt.*;

@Getter @Setter
public final class BlockComponent extends Component {
    /**
     * Цвет фона блока
     */
    @FieldProperty(name="backgroundColor")
    private Color backgroundColor = Color.BLACK;
    /**
     * Толщина границы блока
     */
    @FieldProperty(name="borderThickness")
    private double borderThickness;
    /**
     * Цвет границы блока
     */
    @FieldProperty(name="borderColor")
    private Color borderColor = Color.BLACK;
    /**
     * Вместо стандартного рендера компонента будет использоваться текстура, если она указана
     */
    @FieldProperty(name="texture")
    private @Nullable Texture texture;

    @Override
    public void render(DrawContext context, Mouse mouse, float delta) {
        if (this.getTexture() == null) {
            RenderHelper.renderRectangle(context, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.backgroundColor);
            if (this.borderThickness > 0)
                RenderHelper.renderBorder(context, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.borderThickness, this.borderColor);
        } else {
            context.drawTexture(this.getTexture().getIdentifier(), (int) this.getX(), (int) this.getY(), (int) this.getTexture().getU(), (int) this.getTexture().getV(), (int) this.getTexture().getWidth(), (int) this.getTexture().getHeight());
        }
        super.render(context, mouse, delta);
    }

    @Override
    public void mouseClicked(Mouse mouse, MouseButton button) {
        super.mouseClicked(mouse, button);
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
    }
}