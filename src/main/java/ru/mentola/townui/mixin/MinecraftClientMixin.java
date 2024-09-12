package ru.mentola.townui.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.mentola.townui.api.TownUIAPIImpl;
import ru.mentola.townui.api.event.impl.PostClientInitEvent;
import ru.mentola.townui.api.provider.TownUIProvider;

@Mixin(MinecraftClient.class)
public final class MinecraftClientMixin {
    @Inject(at=@At(value= "RETURN"), method = "Lnet/minecraft/client/MinecraftClient;<init>(Lnet/minecraft/client/RunArgs;)V")
    public void init(RunArgs args, CallbackInfo ci) {
        ((TownUIAPIImpl) TownUIProvider.getAPI())
                .getEventManager()
                .call(new PostClientInitEvent());
    }
}