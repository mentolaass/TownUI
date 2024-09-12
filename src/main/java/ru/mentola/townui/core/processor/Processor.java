package ru.mentola.townui.core.processor;

public interface Processor<T> {
    void handle(T t, ProcessType type, Object... args);
}