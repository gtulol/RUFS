package me.gtulol6.rufs.mixin;

import me.gtulol6.rufs.RUFS;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(GuiConnecting.class)
public class GuiConnectingMixin {
    @Shadow
    private boolean cancel;

    /*
    @Final
    @Shadow
    private GuiScreen previousGuiScreen;
    @Final
    @Shadow
    private static Logger logger;
    */

    @Inject(method = "connect", at = @At("HEAD"))
    private void connect(String ip, int port, CallbackInfo ci) {
        if(RUFS.getInstance().getConfig().toggleServerBlocker &&
                ip.contains(RUFS.getInstance().getConfig().blockServerIPsContainingThisString)) {
            /* ????
            //this.cancel = true;
            int td = RUFS.getInstance().getConfig().banTime - (int)(System.currentTimeMillis()/1000);
            int days = td/60/60/24;
            int hours = (td - days*60*60)/60/60;
            int mins = (td - hours*60)/60;
            int secs = (td - mins);
            logger.debug(days + "d " + hours + "h " + mins + "m " + secs + "s ");
            Minecraft.getMinecraft().displayGuiScreen(
                    new GuiDisconnected(
                            this.previousGuiScreen,
                            "connect.failed",
                            new ChatComponentTranslation(
                                    "disconnect.genericReason",
                                    new Object[]{
                                            "§cYou are temporarily banned for §f" +
                                            days + "d " + hours + "h " + mins + "m " + secs + "s " +
                                            "§cfrom this Server\n" +
                                            "§7Reason: §fExtreme Chat Infraction." +
                                            "§cFind out more: §b§nhttps://www.hypixel.net/appeal §r\n\n" +
                                            "§7Ban ID: §f#891F5966\n" +
                                            "§7Sharing your Ban ID may affect the processing of your appeal!"
                                    })));

             */
            this.cancel = true;

        }
    }
}
