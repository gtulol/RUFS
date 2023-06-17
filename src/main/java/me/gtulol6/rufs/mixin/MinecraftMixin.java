package me.gtulol6.rufs.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = net.minecraft.client.Minecraft.class, priority = -999)
public class MinecraftMixin {
    /**
     * ???
     */
    @ModifyArgs(method = "drawSplashScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V"))
    public void color1(Args args){
        args.set(1, 0.0F);
        args.set(2, 0.0F);
    }
}
