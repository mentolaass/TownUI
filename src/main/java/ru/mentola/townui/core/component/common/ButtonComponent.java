package ru.mentola.townui.core.component.common;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.mouse.Mouse;
import ru.mentola.townui.core.mouse.MouseButton;
import ru.mentola.townui.core.positioning.HorizontalAlignment;
import ru.mentola.townui.core.positioning.Padding;
import ru.mentola.townui.core.positioning.VerticalAlignment;
import ru.mentola.townui.core.property.FieldProperty;
import ru.mentola.townui.core.render.RenderHelper;
import ru.mentola.townui.core.texture.Texture;
import ru.mentola.townui.util.Util;

import java.awt.*;

@Getter @Setter
public final class ButtonComponent extends Component {
    /**
     * Текст внутри кнопки
     */
    @FieldProperty(name="content")
    private String content;
    /**
     * Положение текста внутри кнопки по горизонтали
     */
    @FieldProperty(name="contentHorizontalAlignment")
    private @NotNull HorizontalAlignment contentHorizontalAlignment = HorizontalAlignment.LEFT;
    /**
     * Положение текста внутри кнопки по горизонтали
     */
    @FieldProperty(name="contentVerticalAlignment")
    private @NotNull VerticalAlignment contentVerticalAlignment = VerticalAlignment.TOP;
    /**
     * Отступы текста от границ кнопки
     */
    @FieldProperty(name="contentPadding")
    private @Nullable Padding contentPadding;
    /**
     * Цвет фона кнопки
     */
    @FieldProperty(name="backgroundColor")
    private Color backgroundColor = Color.BLACK;
    /**
     * Цвет текста внутри кнопки
     */
    @FieldProperty(name="foregroundColor")
    private Color foregroundColor = Color.WHITE;
    /**
     * Толщина границы кнопки
     */
    @FieldProperty(name="borderThickness")
    private double borderThickness;
    /**
     * Цвет границы кнопки
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
            if (this.content != null && !this.content.isEmpty()) {
                double paddingLeft = (contentPadding != null) ? contentPadding.getLeft() : 0;
                double paddingRight = (contentPadding != null) ? contentPadding.getRight() : 0;
                double paddingTop = (contentPadding != null) ? contentPadding.getTop() : 0;
                double paddingBottom = (contentPadding != null) ? contentPadding.getBottom() : 0;
                double textX = Util.calculateAlignedPosition(this.getX(), this.getWidth(), paddingLeft, paddingRight, MinecraftClient.getInstance().textRenderer.getWidth(this.content), this.contentHorizontalAlignment);
                double textY = Util.calculateAlignedPosition(this.getY(), this.getHeight(), paddingTop, paddingBottom, MinecraftClient.getInstance().textRenderer.fontHeight, this.contentVerticalAlignment);
                context.drawText(MinecraftClient.getInstance().textRenderer, this.content, (int) textX, (int) textY, this.foregroundColor.getRGB(), false);
            }
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