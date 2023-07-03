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

@Mixin(net.minecraft.network.play.server.S2FPacketSetSlot.class)
public class S2FPacketSetSlotMixin {

    Config c = RUFS.getInstance().getConfig();
    @Shadow
    private ItemStack item;

    @Inject(method = "readPacketData", at = @At("TAIL"))
    public void readPacketData(PacketBuffer buf, CallbackInfo ci) {
        if (!c.loadSkullItemSkins) {
            if (this.item != null) {
                if (this.item.getItem() instanceof ItemSkull) {
                    if (this.item.getTagCompound() != null) {
                        if (this.item.getTagCompound().hasKey("SkullOwner")) {
                            this.item.getTagCompound().removeTag("SkullOwner");
                        }
                    }
                }
            }
        }
    }
}
