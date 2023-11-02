package me.gtulol6.rufs.commands;

import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;

public class RUFSCommand extends Command {

    public RUFSCommand() {
        super("rufs");
    }


    @DefaultHandler
    public void handle() {
        Config.c = RUFS.getInstance().getConfig();
        EssentialAPI.getGuiUtil().openScreen(RUFS.getInstance().getConfig().gui());
    }
}
