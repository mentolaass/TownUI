package ru.mentola.townui.api.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.Set;

@AllArgsConstructor @Getter
public final class ListenerContainer {
    private Object source;
    private Set<Method> listeners;
}