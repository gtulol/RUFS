package me.gtulol6.rufs.mixin;

import gg.essential.api.EssentialAPI;
import me.gtulol6.rufs.RUFS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.GuiOptions.class)
public class GuiOptionsMixin extends GuiScreen {


    @Inject(method = "initGui", at = @At("TAIL"))
    public void initGui(CallbackInfo ci) {
        this.buttonList.add(new GuiButton(0xB00F, this.width / 2 + 160, this.height / 6 + 120 - 6, 70, 20, "RUFS Config"));
    }

    @Inject(method = "actionPerformed", at = @At("TAIL"))
    public void actionPerformed(GuiButton button, CallbackInfo ci){
        if(button.enabled && button.id == 0xB00F) {
            Minecraft.getMinecraft().gameSettings.saveOptions();
            EssentialAPI.getGuiUtil().openScreen(RUFS.getInstance().getConfig().gui());
        }
    }
}
