package dexland.argento;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid=Main.MODID)
public final class Main {
    public static final String MODID = "examplemod";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public Main() {
        LOGGER.debug("DexLandMeow enabled!");
        MinecraftForge.EVENT_BUS.register(new EventListener());
    }
}