package net.mcservertest.mixin;

import net.minecraft.server.dedicated.DedicatedServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.mcservertest.MCServerTest;

@Mixin(DedicatedServer.class)
public abstract class DedicatedServerMixin {

    @Inject(method = "initServer", at = @At("RETURN"))
    private void mcservertest$markServerStartup(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            MCServerTest.LOGGER.info("Server has been initialised.");
            MCServerTest.hasServerInitialised = true;
        }
    }
}
