package me.gtulol6.rufs.config;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.JVMAnnotationPropertyCollector;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;
import me.gtulol6.rufs.RUFS;

import java.awt.*;
import java.io.File;

public class Config extends Vigilant {

    public static Config c;

    @Property(
            type = PropertyType.SWITCH,
            name = "Buggy Renderer",
            description = "Turns on Buggy Block Rendering.",
            category = "Block Face Render"
    )
    public boolean renderBuggyBlocks = false;
    @Property(
            type = PropertyType.SLIDER,
            name = "Buggy Blocks Messyness X",
            category = "Block Face Render",
            min = -100,
            max = 100
    )
    public int renderBuggyBlocksRangeX = 0;
    @Property(
            type = PropertyType.SLIDER,
            name = "Buggy Blocks Messyness Y",
            category = "Block Face Render",
            min = -100,
            max = 100
    )
    public int renderBuggyBlocksRangeY = 0;
    @Property(
            type = PropertyType.SLIDER,
            name = "Buggy Blocks Messyness Z",
            category = "Block Face Render",
            min = -100,
            max = 100
    )
    public int renderBuggyBlocksRangeZ = 0;


    @Property(
            type = PropertyType.TEXT,
            name = "Server Blocker",
            description = "Prevents you from joining servers which IPs contain this",
            category = "Connection")
    public String blockServerIPsContainingThisString = "hypixel";
    @Property(
            type = PropertyType.SWITCH,
            name = "Toggle Server Blocker",
            description = "Enable/Disable Server Blocker",
            category = "Connection"
    )
    public boolean toggleServerBlocker = false;


    @Property(
            type = PropertyType.COLOR,
            name = "Set the color of the hitbox",
            category = "Hitbox")
    public Color hitBoxColor = new Color(255, 255, 255, 255);
    @Property(
            type = PropertyType.COLOR,
            name = "Set the color of the eye box",
            category = "Hitbox")
    public Color eyeBoxColor = new Color(255, 0, 0, 255);
    @Property(
            type = PropertyType.COLOR,
            name = "Set the color of the looking vector",
            category = "Hitbox")
    public Color vectorColor = new Color(0, 0, 255, 255);
    @Property(
            type = PropertyType.SELECTOR,
            name = "Set the default vertex format",
            category = "Hitbox",
            options = {"BLOCK", "ITEM", "OLDMODEL_POSITION_TEX_NORMAL", "PARTICLE_POSITION_TEX_COLOR_LMAP", "POSITION",
                       "POSITION_COLOR", "POSITION_TEX", "POSITION_NORMAL", "POSITION_TEX_COLOR", "POSITION_TEX_NORMAL",
                       "POSITION_TEX_LMAP_COLOR", "POSITION_TEX_COLOR_NORMAL"}
    )
    public int defaultVertexFormat = 6;
    @SuppressWarnings("unused")
    @Property(
            type = PropertyType.BUTTON,
            name = "Reset",
            description = "Resets Hitbox config to default",
            category = "Hitbox"
    )
    public void resetHitboxConfig() {
        hitBoxColor = new Color(255, 255, 255, 255);
        eyeBoxColor = new Color(255, 0, 0, 255);
        vectorColor = new Color(0, 0, 255, 255);
        defaultVertexFormat = 6;
    }


    @Property(
            type = PropertyType.NUMBER,
            name = "Blend Function Source Factor",
            description = "Can combined with the Blend Function Destination Factor create various effects on the Hand Item.",
            category = "Hand Item",
            min = -10,
            max = 9999
    )
    public int blendFuncSrcFactor = 770;
    @Property(
            type = PropertyType.NUMBER,
            name = "Blend Function Destination Factor",
            description = "Can combined with the Blend Function Source Factor create various effects on the Hand Item.",
            category = "Hand Item",
            min = -10,
            max = 9999
    )
    public int blendFuncDstFactor = 771;
    @Property(
            type = PropertyType.NUMBER,
            name = "Blend Function Source Factor Alpha",
            category = "Hand Item",
            min = -10,
            max = 9999
    )
    public int blendFuncSrcFactorAlpha = 1;
    @Property(
            type = PropertyType.NUMBER,
            name = "Blend Function Destination Factor Alpha",
            category = "Hand Item",
            min = -10,
            max = 9999
    )
    public int blendFuncDstFactorAlpha = 0;
    @Property(
            type = PropertyType.NUMBER,
            name = "Alpha Function Function",
            category = "Hand Item",
            min = -10,
            max = 9999
    )
    public int alphaFuncFunc = 516;
    @Property(
            type = PropertyType.DECIMAL_SLIDER,
            name = "Alpha Function Ref",
            category = "Hand Item",
            minF = -10.0F,
            maxF = 10.0F
    )
    public float alphaFuncRef = 0.1F;
    @Property(
            type = PropertyType.NUMBER,
            name = "Cull Face Mode",
            category = "Hand Item",
            min = -10,
            max = 9999
    )
    public int cullFaceMode = 1029;
    @Property(
            type = PropertyType.SWITCH,
            name = "Cull Hand Items",
            category = "Hand Item"
    )
    public boolean cullHandItemRender = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Texture Manager Blur",
            category = "Hand Item"
    )
    public boolean textureManagerBlur = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Texture Manager Mipmap",
            category = "Hand Item"
    )
    public boolean textureManagerMipmap = false;
    @SuppressWarnings("unused")
    @Property(
            type = PropertyType.BUTTON,
            name = "Reset",
            description = "Resets all Variables changed in Hand Item to their default values.",
            category = "Hand Item"
    )
    public void resetHandItemVariablesToDefault() {
        blendFuncSrcFactor = 770;
        blendFuncDstFactor = 771;
        blendFuncSrcFactorAlpha = 1;
        blendFuncDstFactorAlpha = 0;
        alphaFuncFunc = 516;
        alphaFuncRef = 0.1F;
        cullFaceMode = 1029;
        cullHandItemRender = false;
        textureManagerBlur = false;
        textureManagerMipmap = true;
    }


    @Property(
            type = PropertyType.SWITCH,
            name = "Load Skin File",
            description = "Toggles whether to load Players Skin Files from the Auth Server, useful for reducing Data Usage.",
            category = "Skin/Cape Loading"
    )
    public boolean loadSkinFile = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Load Skin File",
            description = "Toggles whether to load Players Cape Files from the Auth Server, useful for reducing Data Usage.",
            category = "Skin/Cape Loading"
    )
    public boolean loadCapeFile = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Load Skull Item Skins",
            description = "Toggles whether to load Skull Item Skins from the Auth Server, useful for reducing Data Usage.",
            category = "Skin/Cape Loading"
    )
    public boolean loadSkullItemSkins = true;


    public Config() {
        super(new File(RUFS.configLocation), "§cRUFS", new JVMAnnotationPropertyCollector(), new CustomSortingBehavior());
        initialize();
        c = RUFS.getInstance().getConfig();
    }
}
