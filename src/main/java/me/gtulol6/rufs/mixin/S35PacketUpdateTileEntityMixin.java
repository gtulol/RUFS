package me.gtulol6.rufs.mixin;

import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.network.play.server.S35PacketUpdateTileEntity.class)
public class S35PacketUpdateTileEntityMixin {

    Config c = RUFS.getInstance().getConfig();
    @Shadow
    private NBTTagCompound nbt;

    @Inject(method = "readPacketData", at = @At("TAIL"))
    public void readPacketData(PacketBuffer buf, CallbackInfo ci){
        if(!c.loadSkullItemSkins) {
            if(this.nbt != null) {
                if (this.nbt.hasKey("SkullOwner")) {
                    this.nbt.removeTag("SkullOwner");
                }
            }
        }
    }
}
