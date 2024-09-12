package ru.mentola.townui.core.reflect;

import lombok.experimental.UtilityClass;
import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.property.FieldProperty;
import ru.mentola.townui.core.property.Property;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

@UtilityClass
public class Reflection {
    public <T extends Component> void putPropertiesToAnnotatedComponent(Set<Property<?>> properties, T target) {
        for (Property<?> property : properties) {
            Stream<Field> stream = Arrays.stream(target.getClass().getDeclaredFields());
            Field field = stream.filter((f) -> f.isAnnotationPresent(FieldProperty.class))
                    .filter((f) -> f.getAnnotation(FieldProperty.class)
                            .name()
                            .equals(property.getName()))
                    .findFirst()
                    .orElse(null);
            if (field != null) {
                try {
                    field.setAccessible(true);
                    field.set(target, property.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}