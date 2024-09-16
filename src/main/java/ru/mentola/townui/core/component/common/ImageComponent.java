package ru.mentola.townui.core.component.common;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import ru.mentola.townui.api.provider.TownUIProvider;
import ru.mentola.townui.core.cache.Cache;
import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.image.Image;
import ru.mentola.townui.core.mouse.Mouse;
import ru.mentola.townui.core.mouse.MouseButton;
import ru.mentola.townui.core.property.FieldProperty;
import ru.mentola.townui.core.render.RenderHelper;
import ru.mentola.townui.core.render.custom.gif.GifRenderer;
import ru.mentola.townui.util.ResourceUtil;
import ru.mentola.townui.util.Util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.UUID;

@Getter @Setter
public final class ImageComponent extends Component {
    /**
     * Толщина границы картинки
     */
    @FieldProperty(name="borderThickness")
    private double borderThickness;
    /**
     * Цвет границы картинки
     */
    @FieldProperty(name="borderColor")
    private Color borderColor = Color.BLACK;
    /**
     * Картинка
     */
    @FieldProperty(name="image")
    private Image image;
    /**
     * Debug-mode (only on gif)
     */
    @FieldProperty(name="debug")
    private boolean debug = false;

    @Override
    public void render(DrawContext context, Mouse mouse, float delta) {
        if (image != null) {
            if (image.getIdentifier().getPath().endsWith(".gif")) {
                GifRenderer renderer = Cache.query(Cache.CACHED_GIF_RENDERERS, (gifRenderer) -> gifRenderer.getGifData().getPath().equals(Util.convertIdentifierToPath(image.getIdentifier())));
                if (renderer == null) {
                    renderer = TownUIProvider.getAPI().createGifRenderer(image.getIdentifier());
                    Cache.CACHED_GIF_RENDERERS.add(renderer);
                }
                renderer.render(context, mouse, delta, this.getX(), this.getY(), this.getWidth(), this.getHeight());
                if (this.debug) renderer.drawDebugInfo(context, renderer.getGifData().getFrames().get(renderer.getGifData().getCurrentFrameIndex()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
            } else {
                Pair<String, Identifier> img = Cache.query(Cache.CACHED_DYNAMICALLY_IMAGES, (img_) -> img_.getLeft().equals(Util.convertIdentifierToPath(image.getIdentifier())));
                if (img == null) {
                    img = new Pair<>(image.getIdentifier().getPath(), Util.registerDynamically(Util.bufferedImgFromIs(ResourceUtil.getISResource(Util.convertIdentifierToPath(image.getIdentifier())))));
                    Cache.CACHED_DYNAMICALLY_IMAGES.add(img);
                }
                context.drawTexture(img.getRight(), (int) this.getX(), (int) this.getY(), 0, 0, (int) this.getWidth(), (int) this.getHeight(), (int) this.getWidth(), (int) this.getHeight());
            }
        }
        if (this.borderThickness > 0)
            RenderHelper.renderBorder(context, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.borderThickness, this.borderColor);
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
