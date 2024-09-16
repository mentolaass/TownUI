package ru.mentola.townui.util;

import lombok.experimental.UtilityClass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.mouse.Mouse;
import ru.mentola.townui.core.positioning.HorizontalAlignment;
import ru.mentola.townui.core.positioning.VerticalAlignment;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.UUID;

@UtilityClass
public class Util {
    public boolean pointedToObject(Component component, Mouse mouse) {
        return pointedTo(component.getX(), component.getY(), component.getWidth(), component.getHeight(), mouse);
    }

    public boolean pointedTo(double x, double y, double width, double height, Mouse mouse) {
        return mouse.getX() >= x && mouse.getX() <= x + width && mouse.getY() >= y && mouse.getY() <= y + height;
    }

    public double calculateAlignedPosition(double start, double totalSize, double paddingStart, double paddingEnd, double contentSize, HorizontalAlignment alignment) {
        return switch (alignment) {
            case LEFT -> start + paddingStart;
            case CENTER -> start + paddingStart + ((totalSize - paddingStart - paddingEnd) - contentSize) / 2;
            case RIGHT -> start + totalSize - paddingEnd - contentSize;
        };
    }

    public double calculateAlignedPosition(double start, double totalSize, double paddingStart, double paddingEnd, double contentSize, VerticalAlignment alignment) {
        return switch (alignment) {
            case TOP -> start + paddingStart;
            case CENTER -> start + paddingStart + ((totalSize - paddingStart - paddingEnd) - contentSize) / 2;
            case BOTTOM -> start + totalSize - paddingEnd - contentSize;
        };
    }

    public Identifier registerDynamically(BufferedImage image) {
        return MinecraftClient.getInstance()
                .getTextureManager()
                .registerDynamicTexture(UUID.randomUUID().toString(),
                        new NativeImageBackedTexture(Util.bufferedImgToNative(image)));
    }

    @Nullable
    public BufferedImage bufferedImgFromIs(InputStream is) {
        try {
            return ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NativeImage bufferedImgToNative(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        NativeImage nativeImage = new NativeImage(NativeImage.Format.RGBA, width, height, false);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = image.getRGB(x, y);
                int alpha = (argb >> 24) & 0xFF;
                int red = (argb >> 16) & 0xFF;
                int green = (argb >> 8) & 0xFF;
                int blue = argb & 0xFF;
                int rgba = (alpha << 24) | (blue << 16) | (green << 8) | red;
                nativeImage.setColor(x, y, rgba);
            }
        }
        return nativeImage;
    }

    public String convertIdentifierToPath(Identifier identifier) {
        String path = identifier.getPath();
        path = "/assets/" + identifier.getNamespace() + (!path.startsWith("/") ? "/" : "") + path;
        return path;
    }
}