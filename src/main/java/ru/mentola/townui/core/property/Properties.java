package ru.mentola.townui.core.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.mentola.townui.core.image.Image;
import ru.mentola.townui.core.layer.Layer;
import ru.mentola.townui.core.positioning.HorizontalAlignment;
import ru.mentola.townui.core.positioning.Padding;
import ru.mentola.townui.core.positioning.VerticalAlignment;
import ru.mentola.townui.core.texture.Texture;

import java.awt.*;

@AllArgsConstructor @Getter
public enum Properties {
    X("x", Double.class),
    Y("y", Double.class),
    HEIGHT("height", Double.class),
    WIDTH("width", Double.class),
    NAME("name", String.class),
    CONTENT("content", String.class),
    PARENT_LAYER("parentLayer", Layer.class),
    TEXTURE("texture", Texture.class),
    IMAGE("image", Image.class),
    DEBUG("debug", Boolean.class),
    BORDER_THICKNESS("borderThickness", Double.class),
    CONTENT_VERTICAL_ALIGNMENT("contentVerticalAlignment", VerticalAlignment.class),
    CONTENT_HORIZONTAL_ALIGNMENT("contentHorizontalAlignment", HorizontalAlignment.class),
    CONTENT_PADDING("contentPadding", Padding.class),
    BACKGROUND_COLOR("backgroundColor", Color.class),
    FOREGROUND_COLOR("foregroundColor", Color.class),
    BORDER_COLOR("borderColor", Color.class),
    AUTO_WRAP("autoWrap", Boolean.class);

    private final String name;
    private final Class<?> type;
}