package me.gtulol6.rufs.mixin;

import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.network.play.server.S30PacketWindowItems.class)
public class S30PacketWindowItemsMixin {

    Config c = RUFS.getInstance().getConfig();
    @Shadow
    private ItemStack[] itemStacks;

    @Inject(method = "readPacketData", at = @At("TAIL"))
    public void readPacketData(PacketBuffer buf, CallbackInfo ci) {
        if (!c.loadSkullItemSkins) {
            int i = itemStacks.length;
            for (int j2 = 0; j2 < i; ++j2) {
                if (this.itemStacks[j2] != null) {
                    if (this.itemStacks[j2].getItem() instanceof ItemSkull) {
                        if (this.itemStacks[j2].getTagCompound() != null) {
                            if (this.itemStacks[j2].getTagCompound().hasKey("SkullOwner")) {
                                this.itemStacks[j2].getTagCompound().removeTag("SkullOwner");
                            }
                        }
                    }
                }
            }
        }
    }
}
