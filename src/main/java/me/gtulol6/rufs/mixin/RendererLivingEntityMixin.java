package me.gtulol6.rufs.mixin;

import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = net.minecraft.client.renderer.entity.RendererLivingEntity.class, priority = -999)
public class RendererLivingEntityMixin<T extends EntityLivingBase> extends Render<T> {

    protected RendererLivingEntityMixin(RenderManager renderManager) {
        super(renderManager);
    }

    @Inject(method = "rotateCorpse", at = @At(value = "TAIL", ordinal = 0))
    private void rotateCorpse(T bat, float p_77043_2_, float p_77043_3_, float partialTicks, CallbackInfo ci) {
        String s = EnumChatFormatting.getTextWithoutFormattingCodes(bat.getName());
        Minecraft mc = Minecraft.getMinecraft();
        Config c = RUFS.getInstance().getConfig();
        if (c.enableRotatedEntityModel && s != null) {
            if(c.enableRotatedPlayerModel && !(bat instanceof EntityPlayer))
                return;
            if(c.enableRotatedPlayerModelForSelfOnly && !s.equals(mc.thePlayer.getName()))
                return;
            GlStateManager.translate(c.entityModeltranslateX, bat.height + c.entityModeltranslateY, c.entityModeltranslateZ);
            GlStateManager.rotate(c.entityModelRotationAngle, c.entityModelrotateX, c.entityModelrotateY, c.entityModelrotateZ);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return null;
    }
}
