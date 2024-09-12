package ru.mentola.townui;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import ru.mentola.townui.api.TownUIAPIImpl;
import ru.mentola.townui.api.provider.TownUIProvider;

public final class TownUI implements ModInitializer, ClientModInitializer {
    @Override
    public void onInitialize() {
        TownUIProvider.setAPI(new TownUIAPIImpl());
    }

    @Override
    public void onInitializeClient() {

    }
}
