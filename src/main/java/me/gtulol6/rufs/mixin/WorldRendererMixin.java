package me.gtulol6.rufs.mixin;

import me.gtulol6.rufs.RUFS;
import me.gtulol6.rufs.config.Config;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    Random r = new Random();
    Config config = RUFS.getInstance().getConfig();
    /*
    /**
     * @author gtulol6
     * @reason RUFS
     *
    @Overwrite
    public void putPosition(double x, double y, double z) {
        int i = this.vertexFormat.getIntegerSize();
        int j = (this.vertexCount - 4) * i;

        for (int k = 0; k < 4; ++k) {
            int l = j + k * i;
            int i1 = l + 1;
            int j1 = i1 + 1;
            if (config.renderBuggyBlocks) {
                this.rawIntBuffer.put(l, Float.floatToRawIntBits((float) (x + this.xOffset + (r.nextFloat() / config.renderBuggyBlocksRangeX)) + Float.intBitsToFloat(this.rawIntBuffer.get(l))));
                this.rawIntBuffer.put(i1, Float.floatToRawIntBits((float) (y + this.yOffset + (r.nextFloat() / config.renderBuggyBlocksRangeY)) + Float.intBitsToFloat(this.rawIntBuffer.get(i1))));
                this.rawIntBuffer.put(j1, Float.floatToRawIntBits((float) (z + this.zOffset + (r.nextFloat() / config.renderBuggyBlocksRangeZ)) + Float.intBitsToFloat(this.rawIntBuffer.get(j1))));

            } else {
                this.rawIntBuffer.put(l, Float.floatToRawIntBits((float) (x + this.xOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(l))));
                this.rawIntBuffer.put(i1, Float.floatToRawIntBits((float) (y + this.yOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(i1))));
                this.rawIntBuffer.put(j1, Float.floatToRawIntBits((float) (z + this.zOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(j1))));
            }
        }
    }
    */

    @ModifyArgs(method = "putPosition", at = @At(target = "Ljava/lang/Float;floatToRawIntBits(F)I", value = "INVOKE", ordinal = 0))
    public void modifyfloatToRawIntBits1(Args args) {
        if (config.renderBuggyBlocks) {
            args.set(0, (float) (args.get(0)) + (r.nextFloat() * (config.renderBuggyBlocksRangeX / 10)));
        }
    }

    @ModifyArgs(method = "putPosition", at = @At(target = "Ljava/lang/Float;floatToRawIntBits(F)I", value = "INVOKE", ordinal = 1))
    public void modifyfloatToRawIntBits2(Args args) {
        if (config.renderBuggyBlocks) {
            args.set(0, (float) (args.get(0)) + (r.nextFloat() * (config.renderBuggyBlocksRangeY / 10)));
        }
    }

    @ModifyArgs(method = "putPosition", at = @At(target = "Ljava/lang/Float;floatToRawIntBits(F)I", value = "INVOKE", ordinal = 2))
    public void modifyfloatToRawIntBits3(Args args) {
        if (config.renderBuggyBlocks) {
            args.set(0, (float) (args.get(0)) + (r.nextFloat() * (config.renderBuggyBlocksRangeZ / 10)));
        }
    }

}

