package yourclient.cosmetics.renderer.impl;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.client.cosmetics.Cosmetic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CosmeticModelRenderer {
  public float textureWidth;
  public float textureHeight;
  private int textureOffsetX;
  private int textureOffsetY;
  public float rotationPointX;
  public float rotationPointY;
  public float rotationPointZ;
  public float rotateAngleX;
  public float rotateAngleY;
  public float rotateAngleZ;
  private boolean compiled;
  private int displayList;
  public boolean mirror;
  public boolean showModel;
  public boolean isHidden;
  public List < CosmeticModelBox > cubeList;
  public List < CosmeticModelRenderer > childModels;
  public final String boxName;
  private ModelBase baseModel;
  public float offsetX;
  public float offsetY;
  public float offsetZ;
  public Entity entityIn;
  public float ticks_2;
  public float ticks_3;
  public float ticks_4;
  public float ticks_5;
  public float ticks_6;
  public AbstractClientPlayer clientPlayer;
  public float partialTicks;
  public Cosmetic cosmetic;

  public float airTicks;

  public CosmeticModelRenderer(ModelBase p_i1172_1_, String p_i1172_2_) {
    this.textureWidth = 64.0F;
    this.textureHeight = 32.0F;
    this.showModel = true;
    this.cubeList = new ArrayList();
    this.baseModel = p_i1172_1_;
    this.boxName = p_i1172_2_;
    this.setTextureSize(p_i1172_1_.textureWidth, p_i1172_1_.textureHeight);
  }

  public CosmeticModelRenderer(ModelBase p_i1173_1_) {
    this(p_i1173_1_, (String) null);
  }

  public CosmeticModelRenderer(ModelBase p_i1174_1_, int p_i1174_2_, int p_i1174_3_) {
    this(p_i1174_1_);
    this.setTextureOffset(p_i1174_2_, p_i1174_3_);
  }

  public void addChild(CosmeticModelRenderer p_78792_1_) {
    if (this.childModels == null) {
      this.childModels = new ArrayList();
    }

    this.childModels.add(p_78792_1_);
  }

  public CosmeticModelRenderer setTextureOffset(int p_78784_1_, int p_78784_2_) {
    this.textureOffsetX = p_78784_1_;
    this.textureOffsetY = p_78784_2_;
    return this;
  }

  public CosmeticModelRenderer addBox(String p_78786_1_, float p_78786_2_, float p_78786_3_, float p_78786_4_, int p_78786_5_, int p_78786_6_, int p_78786_7_) {
    p_78786_1_ = this.boxName + "." + p_78786_1_;
    TextureOffset textureoffset = this.baseModel.getTextureOffset(p_78786_1_);
    this.setTextureOffset(textureoffset.textureOffsetX, textureoffset.textureOffsetY);
    this.cubeList.add((new CosmeticModelBox(this, this.textureOffsetX, this.textureOffsetY, p_78786_2_, p_78786_3_, p_78786_4_, p_78786_5_, p_78786_6_, p_78786_7_, 0.0F)).func_78244_a(p_78786_1_));
    return this;
  }

  public CosmeticModelRenderer addBox(float p_78789_1_, float p_78789_2_, float p_78789_3_, int p_78789_4_, int p_78789_5_, int p_78789_6_) {
    this.cubeList.add(new CosmeticModelBox(this, this.textureOffsetX, this.textureOffsetY, p_78789_1_, p_78789_2_, p_78789_3_, p_78789_4_, p_78789_5_, p_78789_6_, 0.0F));
    return this;
  }

  public void addBox(float p_78790_1_, float p_78790_2_, float p_78790_3_, int p_78790_4_, int p_78790_5_, int p_78790_6_, float p_78790_7_) {
    this.cubeList.add(new CosmeticModelBox(this, this.textureOffsetX, this.textureOffsetY, p_78790_1_, p_78790_2_, p_78790_3_, p_78790_4_, p_78790_5_, p_78790_6_, p_78790_7_));
  }

  public void setRotationPoint(float p_78793_1_, float p_78793_2_, float p_78793_3_) {
    this.rotationPointX = p_78793_1_;
    this.rotationPointY = p_78793_2_;
    this.rotationPointZ = p_78793_3_;
  }

  public void renderRGB(int defaultRed, int defaultGreen, int defaultBlue) {
    GL11.glColor3f((float) defaultRed / 255.0F, (float) defaultGreen / 255.0F, (float) defaultBlue / 255.0F);
  }

  public void render(float p_78785_1_) {
    if (!this.isHidden && this.showModel) {
      if (!this.compiled) {
        this.compileDisplayList(p_78785_1_);
      }

      GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
      int i;
      if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
        if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F) {
          GL11.glCallList(this.displayList);
          if (this.childModels != null) {
            for (i = 0; i < this.childModels.size(); ++i) { ((CosmeticModelRenderer) this.childModels.get(i)).render(p_78785_1_);
            }
          }
        } else {
          GL11.glTranslatef(this.rotationPointX * p_78785_1_, this.rotationPointY * p_78785_1_, this.rotationPointZ * p_78785_1_);
          GL11.glCallList(this.displayList);
          if (this.childModels != null) {
            for (i = 0; i < this.childModels.size(); ++i) { ((CosmeticModelRenderer) this.childModels.get(i)).render(p_78785_1_);
            }
          }

          GL11.glTranslatef( - this.rotationPointX * p_78785_1_, -this.rotationPointY * p_78785_1_, -this.rotationPointZ * p_78785_1_);
        }
      } else {
        GL11.glPushMatrix();
        GL11.glTranslatef(this.rotationPointX * p_78785_1_, this.rotationPointY * p_78785_1_, this.rotationPointZ * p_78785_1_);
        if (this.rotateAngleZ != 0.0F) {
          GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
        }

        if (this.rotateAngleY != 0.0F) {
          GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
        }

        if (this.rotateAngleX != 0.0F) {
          GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
        }

        GL11.glCallList(this.displayList);
        if (this.childModels != null) {
          for (i = 0; i < this.childModels.size(); ++i) { ((CosmeticModelRenderer) this.childModels.get(i)).render(p_78785_1_);
          }
        }

        GL11.glPopMatrix();
      }

      GL11.glTranslatef( - this.offsetX, -this.offsetY, -this.offsetZ);
    }

  }

  public void renderWithRotation(float p_78791_1_) {
    if (!this.isHidden && this.showModel) {
      if (!this.compiled) {
        this.compileDisplayList(p_78791_1_);
      }

      GL11.glPushMatrix();
      GL11.glTranslatef(this.rotationPointX * p_78791_1_, this.rotationPointY * p_78791_1_, this.rotationPointZ * p_78791_1_);
      if (this.rotateAngleY != 0.0F) {
        GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
      }

      if (this.rotateAngleX != 0.0F) {
        GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
      }

      if (this.rotateAngleZ != 0.0F) {
        GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
      }

      GL11.glCallList(this.displayList);
      GL11.glPopMatrix();
    }

  }

  public void postRender(float p_78794_1_) {
    if (!this.isHidden && this.showModel) {
      if (!this.compiled) {
        this.compileDisplayList(p_78794_1_);
      }

      if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
        if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
          GL11.glTranslatef(this.rotationPointX * p_78794_1_, this.rotationPointY * p_78794_1_, this.rotationPointZ * p_78794_1_);
        }
      } else {
        GL11.glTranslatef(this.rotationPointX * p_78794_1_, this.rotationPointY * p_78794_1_, this.rotationPointZ * p_78794_1_);
        if (this.rotateAngleZ != 0.0F) {
          GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
        }

        if (this.rotateAngleY != 0.0F) {
          GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
        }

        if (this.rotateAngleX != 0.0F) {
          GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
        }
      }
    }

  }

  private void compileDisplayList(float p_78788_1_) {
    this.displayList = GLAllocation.generateDisplayLists(1);
    GL11.glNewList(this.displayList, 4864);
    WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();

    for (int i = 0; i < this.cubeList.size(); ++i) { ((CosmeticModelBox) this.cubeList.get(i)).render(worldrenderer, p_78788_1_);
    }

    GL11.glEndList();
    this.compiled = true;
  }

  public CosmeticModelRenderer setTextureSize(int p_78787_1_, int p_78787_2_) {
    this.textureWidth = (float) p_78787_1_;
    this.textureHeight = (float) p_78787_2_;
    return this;
  }

  public void renderCosmetic(Entity entityIn, float ticks_2, float ticks_3, float ticks_4, float ticks_5, float ticks_6, float scale, float partialTicks) {
    this.ticks_2 = ticks_2;
    this.ticks_3 = ticks_3;
    this.ticks_4 = ticks_4;
    this.ticks_5 = ticks_5;
    this.ticks_6 = ticks_6;
    this.entityIn = entityIn;
    this.partialTicks = partialTicks;
    this.render(scale);
  }

  public void setRotation(CosmeticModelRenderer modelRenderer, float xRotation, float yRotation, float zRotation) {
    modelRenderer.rotateAngleX = xRotation;
    modelRenderer.rotateAngleY = yRotation;
    modelRenderer.rotateAngleZ = zRotation;
  }

  public void setHeadRotations() {
    if (this.entityIn.isSneaking()) {
      GL11.glTranslated(0, 0.225D, 0);
    }

    GL11.glRotatef(this.ticks_5, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(this.ticks_6, 1.0F, 0.0F, 0.0F);
  }

  public void bindCosmeticTexture(String type, String name) {
    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/cosmetics/" + type + "/" + name));
  }

  public void bindEntityTexture(String name) {
    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/entity/" + name));
  }

  public void runAnimationProcess() {
    if (this.entityIn != null) {
      this.performAnimation();
    }

  }

  public void performAnimation() {}

  public void renderItem(ItemStack item) {
    Minecraft.getMinecraft().getItemRenderer().renderItem((EntityLivingBase) this.entityIn, item, TransformType.NONE);
  }

  public void renderText(String text, int x, int y, int color) {
    Minecraft.getMinecraft().fontRendererObj.drawString(text, x, y, color);
  }

  public int getStringWidth(String text) {
    return Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
  }
}
