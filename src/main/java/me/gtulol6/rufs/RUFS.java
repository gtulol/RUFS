package me.gtulol6.rufs;

import me.gtulol6.rufs.commands.RUFSCommand;
import me.gtulol6.rufs.config.Config;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "rufs", version = "1.0.0")
public class RUFS {

    private static RUFS instance;
    public static RUFS getInstance() {
        return instance;
    }
    private final Config config;
    public Config getConfig() {
        return config;
    }
    public static final String configLocation = "./config/rufs.toml";
public RUFS() {
    instance = this;
    config = new Config();
}
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        config.preload();
        new RUFSCommand().register();
}
}
