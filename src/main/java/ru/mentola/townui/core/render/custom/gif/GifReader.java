package ru.mentola.townui.core.render.custom.gif;

import at.dhyan.open_imaging.GifDecoder;
import lombok.experimental.UtilityClass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import ru.mentola.townui.util.ResourceUtil;
import ru.mentola.townui.util.Util;

import java.awt.image.BufferedImage;

@UtilityClass
public class GifReader {
    public GifData read(Identifier id) {
        GifData gifData = new GifData(Util.convertIdentifierToPath(id));
        try {
            GifDecoder.GifImage image = GifDecoder.read(ResourceUtil.getISResource(Util.convertIdentifierToPath(id)));
            for (int i = 0; i < image.getFrameCount(); i++)
                gifData.allocate(registerDynamically(image.getFrame(i), gifData, i), image.getDelay(i) * 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gifData;
    }

    private Identifier registerDynamically(BufferedImage image, GifData data, int index) {
        return MinecraftClient.getInstance()
                .getTextureManager()
                .registerDynamicTexture("frame_" + index + "_" + data.getUuid(),
                        new NativeImageBackedTexture(Util.bufferedImgToNative(image)));
    }
}