package net.mcservertest.mixin;

import net.mcservertest.MCServerTest;
import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.mcservertest.MCServerTest.hasServerInitialised;

@Mixin(DedicatedServer.class)
public class DedicatedServerMixin {

    @Inject(method="initServer", at = @At("RETURN"))
    private void mcservertest$startExitTimer(CallbackInfoReturnable<Boolean> cir){
        if(cir.getReturnValue()) {
            MCServerTest.LOGGER.info("Server has been initialised.");
            hasServerInitialised = true;
        }
    }

}
