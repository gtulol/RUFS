package me.gtulol6.rufs.commands;

import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import me.gtulol6.rufs.RUFS;

public class RUFSCommand extends Command {

    public RUFSCommand() {
        super("rufs");
    }


    @DefaultHandler
    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(RUFS.getInstance().getConfig().gui());
    }
}
