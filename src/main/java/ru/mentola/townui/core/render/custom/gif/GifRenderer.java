package ru.mentola.townui.core.render.custom.gif;

import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import ru.mentola.townui.core.mouse.Mouse;

import java.awt.*;

@Getter
public final class GifRenderer {
    private final GifData gifData;

    public GifRenderer(String pathToGif) {
        this.gifData = GifReader.read(pathToGif);
    }

    public void render(DrawContext context, Mouse mouse, float delta, double x, double y, double width, double height) {
        Identifier currentFrame = gifData.getFrames()
                .get(gifData.getCurrentFrameIndex())
                .getLeft();
        int frameDelay = gifData.getFrames()
                .get(gifData.getCurrentFrameIndex())
                .getRight();
        long currentTime = System.currentTimeMillis();
        if (currentTime - gifData.getLastFrameTime() >= frameDelay) {
            if (gifData.getCurrentFrameIndex() >= gifData.getFrames().size()-1)
                gifData.setCurrentFrameIndex(0);
            gifData.setCurrentFrameIndex(gifData.getCurrentFrameIndex()+1);
            gifData.setLastFrameTime(currentTime);
        }
        context.drawTexture(currentFrame, (int) x, (int) y, 0, 0, (int) width, (int) height, (int) width, (int) height);
    }

    public void drawDebugInfo(DrawContext context, Pair<Identifier, Integer> frame, double x, double y, double width, double height) {
        MinecraftClient client = MinecraftClient.getInstance();
        context.getMatrices().push();
        context.getMatrices().scale(0.5f, 0.5f, 1);
        double scaledX = x + 15 / 0.5f;
        double scaledY = y / 0.5f;
        double scaledWidth = width / 0.5f;
        context.drawText(client.textRenderer, "frame #" + gifData.getCurrentFrameIndex(), (int) scaledX + (int) scaledWidth, (int) scaledY, Color.WHITE.getRGB(), false);
        context.drawText(client.textRenderer, "total frames: " + gifData.getFrames().size(), (int) scaledX + (int) scaledWidth, (int) scaledY + (int)((client.textRenderer.fontHeight / 0.5) - 5), Color.WHITE.getRGB(), false);
        context.drawText(client.textRenderer, "to next frame: " + (System.currentTimeMillis() - gifData.getLastFrameTime()) + " ms", (int) scaledX + (int) scaledWidth, (int) scaledY + (int)(((client.textRenderer.fontHeight / 0.5) - 5) * 2), Color.WHITE.getRGB(), false);
        context.drawText(client.textRenderer, "frame delay: " + frame.getRight() + " ms", (int) scaledX + (int) scaledWidth, (int) scaledY + (int)(((client.textRenderer.fontHeight / 0.5) - 5) * 3), Color.WHITE.getRGB(), false);
        context.drawText(client.textRenderer, "gif uuid: " + gifData.getUuid(), (int) scaledX + (int) scaledWidth, (int) scaledY + (int)(((client.textRenderer.fontHeight / 0.5) - 5) * 4), Color.WHITE.getRGB(), false);
        context.getMatrices().pop();
    }
}