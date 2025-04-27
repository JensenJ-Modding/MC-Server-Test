package net.mcservertest.mixin;

import net.mcservertest.MCServerTest;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Shadow public abstract void halt(boolean bl);

    @Unique
    private int mcservertest$exitTimer = 0;

    @Inject(method = "runServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;tickServer(Ljava/util/function/BooleanSupplier;)V"))
    private void mcservertest$runExitTimer(CallbackInfo ci){
        if(!MCServerTest.hasServerInitialised) {
            return;
        }

        mcservertest$exitTimer++;
        int exitTimeout = 5 * 20;
        MCServerTest.LOGGER.info("Waiting {} ticks until closing the server.", exitTimeout - mcservertest$exitTimer);

        if(mcservertest$exitTimer >= exitTimeout){
            MCServerTest.LOGGER.info("Closing server.");
            halt(false);
        }
    }
}
