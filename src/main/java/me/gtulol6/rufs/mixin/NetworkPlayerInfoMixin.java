package me.gtulol6.rufs.mixin;

import com.mojang.authlib.GameProfile;
import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.client.network.NetworkPlayerInfo.class)
public class NetworkPlayerInfoMixin {

    @Shadow
    private ResourceLocation locationSkin;
    @Shadow
    private ResourceLocation locationCape;
    @Final
    @Shadow
    private GameProfile gameProfile;
    Config c = RUFS.getInstance().getConfig();

    @Inject(method = {"getLocationSkin"}, at = @At(value = "HEAD"))
    public void getLocationSkin(CallbackInfoReturnable<ResourceLocation> cir) {
        if(!c.loadSkinFile)
            this.locationSkin = DefaultPlayerSkin.getDefaultSkin(this.gameProfile.getId());
    }

    @Inject(method = {"getLocationCape"}, at = @At(value = "HEAD"))
    public void getLocationCape(CallbackInfoReturnable<ResourceLocation> cir) {
        if(!c.loadCapeFile)
            this.locationCape = DefaultPlayerSkin.getDefaultSkin(this.gameProfile.getId());
    }
}
