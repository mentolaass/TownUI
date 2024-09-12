package ru.mentola.townui.core.property.provider;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;
import ru.mentola.townui.core.property.Properties;
import ru.mentola.townui.core.property.Property;

@UtilityClass
public class PropertyProvider {
    @Nullable
    public <V> Property<V> provide(Properties properties, V value) {
        if (!properties.getType().equals(value.getClass()))
            return null;
        return new Property<V>(properties.getName(), value) {
            @Override
            public String getName() {
                return super.getName();
            }
            @Override
            public V getValue() {
                return super.getValue();
            }
        };
    }
}