package me.gtulol6.rufs.mixin;

import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType.FIRST_PERSON;

@SuppressWarnings("deprecation")
@Mixin(value = net.minecraft.client.renderer.entity.RenderItem.class, priority = -999)
public class RenderItemMixin {

    public Config c = RUFS.getInstance().getConfig();

    /*
    /**
     * @author gtulol6
     * @reason
     *
    @Overwrite
    protected void renderItemModelTransform(ItemStack stack, IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType) {
        this.textureManager.bindTexture(TextureMap.locationBlocksTexture);
        this.textureManager.getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(c.textureManagerBlur, c.textureManagerMipmap);
        this.preTransform(stack);
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(c.alphaFuncFunc, c.alphaFuncRef);
        GlStateManager.enableBlend();
        if(c.cullHandItemRender)
            GL11.glEnable(GL11.GL_CULL_FACE);
        GlStateManager.tryBlendFuncSeparate(c.blendFuncSrcFactor, c.blendFuncDstFactor, c.blendFuncSrcFactorAlpha, c.blendFuncDstFactorAlpha);
        GlStateManager.pushMatrix();
        model = ForgeHooksClient.handleCameraTransforms(model, cameraTransformType);
        this.renderItem(stack, model);
        GlStateManager.cullFace(c.cullFaceMode);
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        if(c.cullHandItemRender)
            GL11.glDisable(GL11.GL_CULL_FACE);
        this.textureManager.bindTexture(TextureMap.locationBlocksTexture);
        this.textureManager.getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
    }
    */

    @ModifyArgs(method = "renderItemModelTransform", at = @At(target = "Lnet/minecraft/client/renderer/texture/ITextureObject;setBlurMipmap(ZZ)V", value = "INVOKE"))
    public void modifyBlurMipmap(Args args){
        args.set(0, c.textureManagerBlur);
        args.set(1, c.textureManagerMipmap);
    }
    @ModifyArgs(method = "renderItemModelTransform", at = @At(target = "Lnet/minecraft/client/renderer/GlStateManager;alphaFunc(IF)V", value = "INVOKE"))
    public void modifyAlphaFunc(Args args){
        args.set(0, c.alphaFuncFunc);
        args.set(1, c.alphaFuncRef);
    }
    @Inject(method = "renderItemModelTransform", at = @At(target = "Lnet/minecraft/client/renderer/GlStateManager;enableBlend()V", value = "INVOKE"))
    public void setGL_CULL_FACE(ItemStack stack, IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType, CallbackInfo ci){
        if(c.cullHandItemRender)
            GL11.glEnable(GL11.GL_CULL_FACE);
        if(!c.cullHandItemRender)
            GL11.glDisable(GL11.GL_CULL_FACE);
    }
    @ModifyArgs(method = "renderItemModelTransform", at = @At(target = "Lnet/minecraft/client/renderer/GlStateManager;tryBlendFuncSeparate(IIII)V", value = "INVOKE"))
    public void modifyBlendFuncSeparate(Args args){
        args.set(0, c.blendFuncSrcFactor);
        args.set(1, c.blendFuncDstFactor);
        args.set(2, c.blendFuncSrcFactorAlpha);
        args.set(3, c.blendFuncDstFactorAlpha);
    }
    @ModifyArgs(method = "renderItemModelTransform", at = @At(target = "Lnet/minecraft/client/renderer/GlStateManager;cullFace(I)V", value = "INVOKE"))
    public void modifyCullFace(Args args){
        args.set(0, c.cullFaceMode);
    }
    @Inject(method = "renderItemModelTransform", at = @At(target = "Lnet/minecraft/client/renderer/GlStateManager;disableBlend()V", value = "INVOKE"))
    public void disableGL_CULL_FACE(ItemStack stack, IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType, CallbackInfo ci){
        if(c.cullHandItemRender)
            GL11.glDisable(GL11.GL_CULL_FACE);
        if(!c.cullHandItemRender)
            GL11.glEnable(GL11.GL_CULL_FACE);
    }

    /*
    https://github.com/Sk1erLLC/Patcher/blob/master/src/main/java/club/sk1er/patcher/mixins/features/lefthand/RenderItemMixin_LeftHandedness.java
     */
    @Inject(method = "renderItemModelTransform", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/resources/model/IBakedModel;)V"))
    public void flipHandItem(ItemStack stack, IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType, CallbackInfo ci) {
        if(c.onlyInFirstPerson && cameraTransformType != FIRST_PERSON)
            return;
        if (c.enableHandItemScale && !(stack.getItem().isFull3D() || stack.getItem() instanceof ItemBow)) {
            GlStateManager.scale(c.handItemScaleX, c.handItemScaleY, c.handItemScaleZ);
            GL11.glFrontFace(GL11.GL_CCW);
        }
    }
}
