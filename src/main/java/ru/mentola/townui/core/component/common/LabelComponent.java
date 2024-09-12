package ru.mentola.townui.core.component.common;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.mouse.Mouse;
import ru.mentola.townui.core.mouse.MouseButton;
import ru.mentola.townui.core.positioning.HorizontalAlignment;
import ru.mentola.townui.core.positioning.Padding;
import ru.mentola.townui.core.positioning.VerticalAlignment;
import ru.mentola.townui.core.property.FieldProperty;
import ru.mentola.townui.util.Util;

import java.awt.*;
import java.util.List;

@Getter @Setter
public final class LabelComponent extends Component {
    /**
     * Текст
     */
    @FieldProperty(name="content")
    private String content;
    /**
     * Положение текста по горизонтали
     */
    @FieldProperty(name="contentHorizontalAlignment")
    private @NotNull HorizontalAlignment contentHorizontalAlignment = HorizontalAlignment.LEFT;
    /**
     * Положение текста по вертикали
     */
    @FieldProperty(name="contentVerticalAlignment")
    private @NotNull VerticalAlignment contentVerticalAlignment = VerticalAlignment.TOP;
    /**
     * Отступы текста от границ
     */
    @FieldProperty(name="contentPadding")
    private @Nullable Padding contentPadding;
    /**
     * Цвет текста
     */
    @FieldProperty(name="foregroundColor")
    private Color foregroundColor = Color.WHITE;
    /**
     * Автоматический перенос текста на новую строку.
     */
    @FieldProperty(name="autoWrap")
    private boolean autoWrap;

    @Override
    public void render(DrawContext context, Mouse mouse, float delta) {
        super.render(context, mouse, delta);

        if (!content.isEmpty()) {
            context.enableScissor((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight() - 1));
            double paddingLeft = (contentPadding != null) ? contentPadding.getLeft() : 0;
            double paddingRight = (contentPadding != null) ? contentPadding.getRight() : 0;
            double paddingTop = (contentPadding != null) ? contentPadding.getTop() : 0;
            double paddingBottom = (contentPadding != null) ? contentPadding.getBottom() : 0;
            if (!autoWrap) {
                double textX = Util.calculateAlignedPosition(this.getX(), this.getWidth(), paddingLeft, paddingRight, MinecraftClient.getInstance().textRenderer.getWidth(this.content), this.contentHorizontalAlignment);
                double textY = Util.calculateAlignedPosition(this.getY(), this.getHeight(), paddingTop, paddingBottom, MinecraftClient.getInstance().textRenderer.fontHeight, this.contentVerticalAlignment);
                context.drawText(MinecraftClient.getInstance().textRenderer, this.content, (int) textX, (int) textY, this.foregroundColor.getRGB(), false);
            } else {
                int maxWidth = (int) (this.getWidth() - paddingLeft - paddingRight);
                List<OrderedText> wrappedText = MinecraftClient.getInstance().textRenderer.wrapLines(Text.of(this.content), maxWidth);
                double textY = Util.calculateAlignedPosition(this.getY(), this.getHeight(), paddingTop, paddingBottom, wrappedText.size() * MinecraftClient.getInstance().textRenderer.fontHeight, this.contentVerticalAlignment);
                for (OrderedText line : wrappedText) {
                    double textX = Util.calculateAlignedPosition(this.getX(), this.getWidth(), paddingLeft, paddingRight, MinecraftClient.getInstance().textRenderer.getWidth(line), this.contentHorizontalAlignment);
                    context.drawText(MinecraftClient.getInstance().textRenderer, line, (int) textX, (int) textY, this.foregroundColor.getRGB(), false);
                    textY += MinecraftClient.getInstance().textRenderer.fontHeight;
                }
            }
            context.disableScissor();
        }
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