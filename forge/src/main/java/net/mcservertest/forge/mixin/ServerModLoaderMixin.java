package net.mcservertest.forge.mixin;

import net.minecraftforge.server.loading.ServerModLoader;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.mcservertest.MCServerTest;

@Mixin(value = ServerModLoader.class, remap = false)
public abstract class ServerModLoaderMixin {

    @Inject(
            method = "load",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraftforge/logging/CrashReportExtender;dumpModLoadingCrashReport(Lorg/apache/logging/log4j/Logger;Lnet/minecraftforge/fml/LoadingFailedException;Ljava/io/File;)Ljava/io/File;"))
    private static void mcservertest$handleServerModLoadingException(CallbackInfo ci) {
        MCServerTest.LOGGER.fatal("Exiting process due to server mod loading exception");
        System.exit(1);
    }
}
