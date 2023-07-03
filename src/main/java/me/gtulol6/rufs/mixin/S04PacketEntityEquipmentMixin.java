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

@Mixin(net.minecraft.network.play.server.S04PacketEntityEquipment.class)
public class S04PacketEntityEquipmentMixin {

    Config c = RUFS.getInstance().getConfig();
    @Shadow
    private ItemStack itemStack;

    @Inject(method = "readPacketData", at = @At("TAIL"))
    public void readPacketData(PacketBuffer buf, CallbackInfo ci){
        if(!c.loadSkullItemSkins) {
            if(this.itemStack != null) {
                if(this.itemStack.getItem() instanceof ItemSkull) {
                    if (this.itemStack.getTagCompound() != null) {
                        if(this.itemStack.getTagCompound().hasKey("SkullOwner")) {
                            this.itemStack.getTagCompound().removeTag("SkullOwner");
                        }
                    }
                }
            }
        }
    }
}
