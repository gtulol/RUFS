package me.gtulol6.rufs.mixin;

import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = net.minecraft.client.renderer.ItemRenderer.class, priority = -999)
public class ItemRendererMixin {

    @Final
    @Shadow
    private Minecraft mc;
    @Final
    @Shadow
    private RenderManager renderManager;


    @Inject(method = "renderPlayerArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderPlayer;renderRightArm(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", shift = At.Shift.AFTER))
    public void renderLeftArmToo(AbstractClientPlayer clientPlayer, float equipProgress, float swingProgress, CallbackInfo ci) {
        Config c = RUFS.getInstance().getConfig();
        if(c.renderLeftArmToo) {
            Render<AbstractClientPlayer> render = this.renderManager.getEntityRenderObject(this.mc.thePlayer);
            RenderPlayer renderplayer = (RenderPlayer)render;
            renderplayer.renderLeftArm(this.mc.thePlayer);
        }
    }
}
