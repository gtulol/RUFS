package me.gtulol6.rufs.mixin;

import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;
import me.gtulol6.rufs.mixin.accessor.ItemRendererAccessor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = net.minecraft.client.renderer.EntityRenderer.class, priority = -999)
public class EntityRendererMixin {

    @Shadow
    @Final
    public ItemRenderer itemRenderer;

    /*
    https://github.com/Sk1erLLC/Patcher/blob/master/src/main/java/club/sk1er/patcher/mixins/features/lefthand/EntityRendererMixin_LeftHandedness.java
     */
    @Dynamic("OptiFine")
    @Inject(
            method = {"renderHand(FI)V", "renderHand(FIZZZ)V", "func_78476_b"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;enableLightmap()V", shift = At.Shift.AFTER)
    )
    private void scaleHand(CallbackInfo ci) {
        Config c = RUFS.getInstance().getConfig();
        if (c.enableHandItemScale) {
            ItemStack itemToRender = ((ItemRendererAccessor) itemRenderer).getItemToRender();
            if (itemToRender == null || !(itemToRender.getItem() instanceof ItemMap)) {
                GlStateManager.scale(c.handItemScaleX, c.handItemScaleY, c.handItemScaleZ);
                GL11.glFrontFace(GL11.GL_CW);
            }
        }
    }

    /*
    https://github.com/Sk1erLLC/Patcher/blob/master/src/main/java/club/sk1er/patcher/mixins/features/lefthand/EntityRendererMixin_LeftHandedness.java
     */
    @Dynamic("OptiFine")
    @Inject(
            method = {"renderHand(FI)V", "renderHand(FIZZZ)V", "func_78476_b"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;disableLightmap()V")
    )
    private void resetFrontFace(CallbackInfo ci) {
        Config c = RUFS.getInstance().getConfig();
        if (c.enableHandItemScale) {
            GL11.glFrontFace(GL11.GL_CCW);
        }
    }
}
