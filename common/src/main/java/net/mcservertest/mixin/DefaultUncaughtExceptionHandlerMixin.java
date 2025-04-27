package net.mcservertest.mixin;

import net.minecraft.DefaultUncaughtExceptionHandler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.mcservertest.MCServerTest;

@Mixin(DefaultUncaughtExceptionHandler.class)
public abstract class DefaultUncaughtExceptionHandlerMixin {

    @Inject(method = "uncaughtException", at = @At("TAIL"))
    private void mcservertest$handleUncaughtException(Thread thread, Throwable throwable, CallbackInfo ci) {
        MCServerTest.LOGGER.fatal("Exiting process due to uncaught exception");
        System.exit(1);
    }
}
