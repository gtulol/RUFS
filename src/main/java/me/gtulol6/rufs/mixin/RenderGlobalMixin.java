package me.gtulol6.rufs.mixin;

import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = net.minecraft.client.renderer.RenderGlobal.class, priority = -999)
public class RenderGlobalMixin {
    /**
     * @author gtulol6
     * @reason why
     */
    @Overwrite
    public static void drawOutlinedBoundingBox(AxisAlignedBB boundingBox, int red, int green, int blue, int alpha) {
        Config config = RUFS.getInstance().getConfig();
        VertexFormat vf;
        switch (config.defaultVertexFormat) {
            case 0:
                vf = DefaultVertexFormats.BLOCK;
                break;
            case 1:
                vf = DefaultVertexFormats.ITEM;
                break;
            case 2:
                vf = DefaultVertexFormats.OLDMODEL_POSITION_TEX_NORMAL;
                break;
            case 3:
                vf = DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP;
                break;
            case 4:
                vf = DefaultVertexFormats.POSITION;
                break;
            case 5:
                vf = DefaultVertexFormats.POSITION_COLOR;
                break;
            case 6:
                vf = DefaultVertexFormats.POSITION_TEX;
                break;
            case 7:
                vf = DefaultVertexFormats.POSITION_NORMAL;
                break;
            case 8:
                vf = DefaultVertexFormats.POSITION_TEX_COLOR;
                break;
            case 9:
                vf = DefaultVertexFormats.POSITION_TEX_NORMAL;
                break;
            case 10:
                vf = DefaultVertexFormats.POSITION_TEX_LMAP_COLOR;
                break;
            case 11:
                vf = DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL;
                break;
            default:
                vf = DefaultVertexFormats.POSITION_COLOR;
                break;
        }
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(3, vf);
        worldrenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        worldrenderer.begin(3, vf);
        worldrenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        worldrenderer.begin(1, vf);
        worldrenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
    }

    //TODO: drawSelectionBoundingBox color changer
}
