package net.mcservertest.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.mcservertest.MCServerTest;

@Mod(MCServerTest.MOD_ID)
public class MCServerTestForge {
    public MCServerTestForge(FMLJavaModLoadingContext context) {
        MCServerTest.init();
    }
}
