package ru.mentola.townui.core.render.custom.gif;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor @Getter
public final class GifData {
    private final List<Pair<Identifier, Integer>> frames = new ArrayList<>();
    private UUID uuid = UUID.randomUUID();
    private final String path;
    // render states
    @Setter
    private int currentFrameIndex;
    @Setter
    private long lastFrameTime;

    public void allocate(Identifier identifier, Integer delay) {
        this.frames.add(new Pair<>(identifier, delay));
    }

    public void clear() {
        this.frames.clear();
        this.uuid = UUID.randomUUID();
    }
}