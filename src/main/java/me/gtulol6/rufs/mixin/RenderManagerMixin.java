package me.gtulol6.rufs.mixin;

import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = net.minecraft.client.renderer.entity.RenderManager.class, priority = -999)
public class RenderManagerMixin {

    @Shadow private boolean debugBoundingBox;
    Config c = RUFS.getInstance().getConfig();

    /*
    /**
     * @author gtulol6
     * @reason
     *
    @Overwrite
    private void renderDebugBoundingBox(Entity entityIn, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.depthMask(false);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        float f = entityIn.width / 2.0F;
        AxisAlignedBB axisalignedbb = entityIn.getEntityBoundingBox();
        AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX - entityIn.posX + x, axisalignedbb.minY - entityIn.posY + y, axisalignedbb.minZ - entityIn.posZ + z, axisalignedbb.maxX - entityIn.posX + x, axisalignedbb.maxY - entityIn.posY + y, axisalignedbb.maxZ - entityIn.posZ + z);
        RenderGlobal.drawOutlinedBoundingBox(axisalignedbb1, c.hitBoxColor.getRed(), c.hitBoxColor.getGreen(), c.hitBoxColor.getBlue(), c.hitBoxColor.getAlpha());
        if (entityIn instanceof EntityLivingBase) {
            float f1 = 0.01F;
            RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(x - (double)f, y + (double)entityIn.getEyeHeight() - 0.009999999776482582, z - (double)f, x + (double)f, y + (double)entityIn.getEyeHeight() + 0.009999999776482582, z + (double)f), eb.getRed(), eb.getGreen(), eb.getBlue(), eb.getAlpha());
        }

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        Vec3 vec3 = entityIn.getLook(partialTicks);
        worldrenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(x, y + (double)entityIn.getEyeHeight(), z).color(vec.getRed(), vec.getGreen(), vec.getBlue(), vec.getAlpha()).endVertex();
        worldrenderer.pos(x + vec3.xCoord * 2.0, y + (double)entityIn.getEyeHeight() + vec3.yCoord * 2.0, z + vec3.zCoord * 2.0).color(vec.getRed(), vec.getGreen(), vec.getBlue(), vec.getAlpha()).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
    }
    */


    @ModifyArgs(method = "renderDebugBoundingBox", at = @At(target = "Lnet/minecraft/client/renderer/RenderGlobal;drawOutlinedBoundingBox(Lnet/minecraft/util/AxisAlignedBB;IIII)V", value = "INVOKE", ordinal = 0))
    public void modifyHitBoxColor(Args args) {
        args.set(1, c.hitBoxColor.getRed());
        args.set(2, c.hitBoxColor.getGreen());
        args.set(3, c.hitBoxColor.getBlue());
        args.set(4, c.hitBoxColor.getAlpha());
    }

    @ModifyArgs(method = "renderDebugBoundingBox", at = @At(target = "Lnet/minecraft/client/renderer/RenderGlobal;drawOutlinedBoundingBox(Lnet/minecraft/util/AxisAlignedBB;IIII)V", value = "INVOKE", ordinal = 1))
    public void modifyEyeBoxColor(Args args) {
        args.set(1, c.eyeBoxColor.getRed());
        args.set(2, c.eyeBoxColor.getGreen());
        args.set(3, c.eyeBoxColor.getBlue());
        args.set(4, c.eyeBoxColor.getAlpha());
    }

    @ModifyArgs(method = "renderDebugBoundingBox", at = @At(target = "Lnet/minecraft/client/renderer/WorldRenderer;color(IIII)Lnet/minecraft/client/renderer/WorldRenderer;", value = "INVOKE", ordinal = 0))
    public void modifyVectorColor1(Args args) {
        args.set(0, c.vectorColor.getRed());
        args.set(1, c.vectorColor.getGreen());
        args.set(2, c.vectorColor.getBlue());
        args.set(3, c.vectorColor.getAlpha());
    }

    @ModifyArgs(method = "renderDebugBoundingBox", at = @At(target = "Lnet/minecraft/client/renderer/WorldRenderer;color(IIII)Lnet/minecraft/client/renderer/WorldRenderer;", value = "INVOKE", ordinal = 1))
    public void modifyVectorColor2(Args args) {
        args.set(0, c.vectorColor.getRed());
        args.set(1, c.vectorColor.getGreen());
        args.set(2, c.vectorColor.getBlue());
        args.set(3, c.vectorColor.getAlpha());
    }



    @Inject(method = "<init>", at = @At("TAIL"))
    public void setDebugBoundingBoxOnStartup(TextureManager renderEngineIn, RenderItem itemRendererIn, CallbackInfo ci) {
        c = RUFS.getInstance().getConfig();
        if(c.enableHitboxOnStartup)
            this.debugBoundingBox = true;
    }
}
