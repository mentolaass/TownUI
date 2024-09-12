package ru.mentola.townui.api.event.manager;

import ru.mentola.townui.api.event.Event;
import ru.mentola.townui.api.event.Listener;
import ru.mentola.townui.api.event.container.ListenerContainer;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public final class EventManager {
    private final Set<ListenerContainer> containers = new HashSet<>();

    public void register(Object listener) {
        this.containers.add(this.extract(listener));
    }

    public void unregister(Object listener) {
        this.containers.remove(this.extract(listener));
    }

    public <T extends Event> void call(T t) {
        for (ListenerContainer container : this.containers) {
            for (Method method : container.getListeners()) {
                final Listener listener = method.getAnnotation(Listener.class);
                if (listener.type().equals(t.getClass())) invokeListener(container.getSource(), method, t);
            }
        }
    }

    private void invokeListener(Object source, Method listener, Object... args) {
        try {
            listener.invoke(source, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ListenerContainer extract(Object obj) {
        Set<Method> listeners = new HashSet<>();
        for (Method method : obj.getClass().getMethods())
            if (method.isAnnotationPresent(Listener.class))
                listeners.add(method);
        return new ListenerContainer(obj, listeners);
    }
}