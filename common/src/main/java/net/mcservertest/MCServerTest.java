package net.mcservertest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MCServerTest {
    public static final String MOD_ID = "mcservertest";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static boolean hasServerInitialised = false;

    public static void init() {
        LOGGER.info("Initialised mod successfully");
    }
}
