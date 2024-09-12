package ru.mentola.townui.api.provider;

import lombok.Getter;
import lombok.Setter;
import ru.mentola.townui.api.TownUIAPI;

public final class TownUIProvider {
    @Getter @Setter
    private static TownUIAPI API;
}