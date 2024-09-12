package ru.mentola.townui.core.cache;

import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.Nullable;
import ru.mentola.townui.core.render.custom.gif.GifRenderer;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public final class Cache {
    public static final Set<GifRenderer> CACHED_GIF_RENDERERS = new HashSet<>();
    public static final Set<Pair<String, Identifier>> CACHED_DYNAMICALLY_IMAGES = new HashSet<>();

    @Nullable
    public static <T> T query(Set<T> cache, Predicate<T> filter) {
        return cache.stream()
                .filter(filter)
                .findFirst()
                .orElse(null);
    }
}