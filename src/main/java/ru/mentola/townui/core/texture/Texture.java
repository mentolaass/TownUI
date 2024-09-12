package ru.mentola.townui.core.texture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.util.Identifier;

@AllArgsConstructor @Getter
public final class Texture {
    private final Identifier identifier;
    private final double width, height, u, v;
}
