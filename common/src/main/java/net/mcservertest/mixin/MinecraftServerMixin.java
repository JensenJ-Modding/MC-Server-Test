package net.mcservertest.mixin;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.mcservertest.MCServerTest;

@Mixin(value = MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Shadow
    public abstract void halt(boolean bl);

    @Unique private boolean mcservertest$hasServerCrashed = false;

    @Unique private int mcservertest$exitTimer = 0;

    @Inject(
            method = "runServer",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/server/MinecraftServer;tickServer(Ljava/util/function/BooleanSupplier;)V"))
    private void mcservertest$runExitTimer(CallbackInfo ci) {
        if (!MCServerTest.hasServerInitialised) {
            return;
        }

        mcservertest$exitTimer++;
        int exitTimeout = 5 * 20;
        MCServerTest.LOGGER.info("Waiting {} ticks until closing the server.", exitTimeout - mcservertest$exitTimer);

        if (mcservertest$exitTimer >= exitTimeout) {
            MCServerTest.LOGGER.info("Closing server.");
            halt(false);
        }
    }

    @Inject(
            method = "runServer",
            at = @At(value = "INVOKE", target = "Ljava/lang/IllegalStateException;<init>(Ljava/lang/String;)V"))
    private void mcservertest$markServerCrashedInit(CallbackInfo ci) {
        mcservertest$hasServerCrashed = true;
    }

    @Inject(
            method = "runServer",
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V"))
    private void mcservertest$markServerCrashedRun(CallbackInfo ci) {
        mcservertest$hasServerCrashed = true;
    }

    @Inject(
            method = "halt",
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V"))
    private void mcservertest$markServerCrashedHalt(CallbackInfo ci) {
        mcservertest$hasServerCrashed = true;
    }

    @Inject(method = "runServer", at = @At(value = "TAIL"))
    private void mcservertest$exitServer(CallbackInfo ci) {
        if (mcservertest$hasServerCrashed) {
            MCServerTest.LOGGER.fatal("Exiting process due to server exception");
            System.exit(1);
        }
    }
}
