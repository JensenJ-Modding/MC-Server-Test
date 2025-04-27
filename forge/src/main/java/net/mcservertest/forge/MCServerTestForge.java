package net.mcservertest.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import dev.architectury.platform.forge.EventBuses;
import net.mcservertest.MCServerTest;

@Mod(MCServerTest.MOD_ID)
public class MCServerTestForge {
    public MCServerTestForge(FMLJavaModLoadingContext context) {
        EventBuses.registerModEventBus(MCServerTest.MOD_ID, context.getModEventBus());
        MCServerTest.init();
    }
}
