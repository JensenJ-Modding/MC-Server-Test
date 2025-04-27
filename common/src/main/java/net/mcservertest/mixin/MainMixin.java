package net.mcservertest.mixin;

import net.minecraft.server.Main;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.mcservertest.MCServerTest;

@Mixin(value = Main.class, remap = false)
public abstract class MainMixin {

    @Inject(
            method = "main",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lorg/slf4j/Logger;error(Lorg/slf4j/Marker;Ljava/lang/String;Ljava/lang/Throwable;)V",
                            shift = At.Shift.AFTER))
    private static void mcservertest$catchException(String[] strings, CallbackInfo ci) {
        MCServerTest.LOGGER.fatal("Exiting process due to bad server initialisation");
        System.exit(1);
    }
}
