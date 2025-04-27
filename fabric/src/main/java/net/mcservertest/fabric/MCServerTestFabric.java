package net.mcservertest.fabric;

import net.fabricmc.api.ModInitializer;
import net.mcservertest.MCServerTest;

public class MCServerTestFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MCServerTest.init();
    }
}
