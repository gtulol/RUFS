package me.gtulol6.rufs.mixin.accessor;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/*
https://github.com/Sk1erLLC/Patcher/blob/master/src/main/java/club/sk1er/patcher/mixins/accessors/ItemRendererAccessor.java
 */
@Mixin(value = net.minecraft.client.renderer.ItemRenderer.class, priority = -999)
public interface ItemRendererAccessor {
    @Accessor
    ItemStack getItemToRender();
}