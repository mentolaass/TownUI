package ru.mentola.townui.core.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter
public abstract class Property<T> {
    private String name;
    @Setter
    private T value;
}
