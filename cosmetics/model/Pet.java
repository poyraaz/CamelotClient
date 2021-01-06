package net.client.cosmetics.model.pets;

import org.lwjgl.opengl.GL11;

import net.client.cosmetics.renderer.impl.CosmeticModelRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ModelCharacterPet extends CosmeticModelRenderer {

    CosmeticModelRenderer head;
    CosmeticModelRenderer bipedHeadwear;
    CosmeticModelRenderer bipedBody;
    CosmeticModelRenderer bipedRightArm;
    CosmeticModelRenderer bipedLeftArm;
    CosmeticModelRenderer bipedRightLeg;
    CosmeticModelRenderer bipedLeftLeg;

    public ModelCharacterPet(ModelBase base) {
        super(base, 64, 64);

        this.head = new CosmeticModelRenderer(base, 0, 0);
        this.head.addBox(-4.0 F, -8.0 F, -4.0 F, 8, 8, 8, 0);
        this.head.setRotationPoint(0.0 F, 0.0 F + 0, 0.0 F);

        this.bipedHeadwear = new CosmeticModelRenderer(base, 32, 0);
        this.bipedHeadwear.addBox(-4.0 F, -8.0 F, -4.0 F, 8, 8, 8, 0 + 0.5 F);
        this.bipedHeadwear.setRotationPoint(0.0 F, 0.0 F + 0, 0.0 F);

        this.bipedBody = new CosmeticModelRenderer(base, 16, 16);
        this.bipedBody.addBox(-4.0 F, 0.0 F, -2.0 F, 8, 12, 4, 0);
        this.bipedBody.setRotationPoint(0.0 F, 0.0 F + 0, 0.0 F);

        this.bipedRightArm = new CosmeticModelRenderer(base, 40, 16);
        this.bipedRightArm.addBox(-3.0 F, -2.0 F, -2.0 F, 4, 12, 4, 0);
        this.bipedRightArm.setRotationPoint(-5.0 F, 2.0 F + 0, 0.0 F);

        this.bipedLeftArm = new CosmeticModelRenderer(base, 32, 48);
        this.bipedLeftArm.addBox(-1.0 F, -2.0 F, -2.0 F, 4, 12, 4, 0);
        this.bipedLeftArm.setRotationPoint(5.0 F, 2.0 F, 0.0 F);

        this.bipedRightLeg = new CosmeticModelRenderer(base, 0, 16);
        this.bipedRightLeg.addBox(-2.0 F, 0.0 F, -2.0 F, 4, 12, 4, 0);
        this.bipedRightLeg.setRotationPoint(-1.9 F, 12.0 F + 0, 0.0 F);

        this.bipedLeftLeg = new CosmeticModelRenderer(base, 16, 48);
        this.bipedLeftLeg.addBox(-2.0 F, 0.0 F, -2.0 F, 4, 12, 4, 0);
        this.bipedLeftLeg.setRotationPoint(1.9 F, 12.0 F, 0.0 F);

    }

    @Override
    public void render(float scale) {

        this.bindCosmeticTexture("pets", "pet.png");

        float f = 5.2 F;
        GlStateManager.scale(1.0 F / f, 1.0 F / f, 1.0 F / f);

        GL11.glTranslatef(2.7 F, -2 F, 0.0 F);

        if (entityIn.isSneaking()) {
            GL11.glTranslated(0.0 D, -0.05 D, 0.0 D);
        }

        GL11.glTranslatef(0.0 F, (float)(Math.cos((double)(this.ticks_4 / 10.0 F)) / 15.0 D), 0.0 F);

        this.head.rotateAngleY = this.ticks_5 / (180 F / (float) Math.PI);
        this.head.rotateAngleX = this.ticks_6 / (180 F / (float) Math.PI);

        this.bipedHeadwear.rotateAngleY = this.ticks_5 / (180 F / (float) Math.PI);
        this.bipedHeadwear.rotateAngleX = this.ticks_6 / (180 F / (float) Math.PI);

        this.bipedRightArm.rotateAngleX = MathHelper.cos(this.ticks_2 * 0.6662 F + (float) Math.PI) * 2.0 F * this.ticks_3 * 0.5 F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(this.ticks_2 * 0.6662 F) * 2.0 F * this.ticks_3 * 0.5 F;
        this.bipedRightArm.rotateAngleZ = 0.0 F;
        this.bipedLeftArm.rotateAngleZ = 0.0 F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(this.ticks_2 * 0.6662 F) * 1.4 F * this.ticks_3;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(this.ticks_2 * 0.6662 F + (float) Math.PI) * 1.4 F * this.ticks_3;
        this.bipedRightLeg.rotateAngleY = 0.0 F;
        this.bipedLeftLeg.rotateAngleY = 0.0 F;

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(this.ticks_4 * 0.09 F) * 0.05 F + 0.05 F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(this.ticks_4 * 0.09 F) * 0.05 F + 0.05 F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(this.ticks_4 * 0.067 F) * 0.05 F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(this.ticks_4 * 0.067 F) * 0.05 F;

        this.head.scaleX = 2 f;
        this.head.scaleY = 2 f;
        this.head.scaleZ = 2 f;

        this.bipedHeadwear.scaleX = 2 f;
        this.bipedHeadwear.scaleY = 2 f;
        this.bipedHeadwear.scaleZ = 2 f;

        this.bipedLeftLeg.scaleY = 0.7 f;
        this.bipedRightLeg.scaleY = 0.7 f;

        this.bipedLeftArm.scaleY = 0.7 f;
        this.bipedRightArm.scaleY = 0.7 f;

        this.head.render(scale);
        this.bipedHeadwear.render(scale);
        this.bipedBody.render(scale);
        this.bipedLeftArm.render(scale);
        this.bipedLeftLeg.render(scale);
        this.bipedRightArm.render(scale);
        this.bipedRightLeg.render(scale);
        super.render(scale);

    }

}
