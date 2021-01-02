package yourclient.package;

import org.lwjgl.opengl.GL11;

import yourclient.cosmetics.renderer.impl.CosmeticModelRenderer;
import net.minecraft.client.model.ModelBase;

public class ModelLego extends CosmeticModelRenderer {

  CosmeticModelRenderer brickBase;
  CosmeticModelRenderer topLeftBrick;
  CosmeticModelRenderer bottomLeftBrick;
  CosmeticModelRenderer bottomRightBrick;
  CosmeticModelRenderer topRightBrick;

  public ModelLego(ModelBase base) {
    super(base);
    textureWidth = 64;
    textureHeight = 32;

    brickBase = (new CosmeticModelRenderer(base, 1, 1)).setTextureSize(64, 32);
    brickBase.addBox( - 4.5F, 0F, -4.5F, 9, 4, 9);
    brickBase.setRotationPoint(0F, -4F, 0F);
    brickBase.mirror = true;
    setRotation(brickBase, 0F, 0F, 0F);

    topLeftBrick = (new CosmeticModelRenderer(base, 33, 1)).setTextureSize(64, 32);
    topLeftBrick.addBox(1F, -1F, -4F, 3, 1, 3);
    topLeftBrick.setRotationPoint(0F, -4F, 0F);
    topLeftBrick.mirror = true;
    setRotation(topLeftBrick, 0F, 0F, 0F);

    bottomLeftBrick = (new CosmeticModelRenderer(base, 49, 1)).setTextureSize(64, 32);
    bottomLeftBrick.addBox(1F, -1F, 1F, 3, 1, 3);
    bottomLeftBrick.setRotationPoint(0F, -4F, 0F);
    bottomLeftBrick.setTextureSize(64, 32);
    bottomLeftBrick.mirror = true;
    setRotation(bottomLeftBrick, 0F, 0F, 0F);

    bottomRightBrick = (new CosmeticModelRenderer(base, 41, 9)).setTextureSize(64, 32);
    bottomRightBrick.addBox( - 4F, -1F, 1F, 3, 1, 3);
    bottomRightBrick.setRotationPoint(0F, -4F, 0F);
    bottomRightBrick.setTextureSize(64, 32);
    bottomRightBrick.mirror = true;
    setRotation(bottomRightBrick, 0f, 0f, 0f);

    topRightBrick = (new CosmeticModelRenderer(base, 1, 17)).setTextureSize(64, 32);
    topRightBrick.addBox( - 4F, -1F, -4F, 3, 1, 3);
    topRightBrick.setRotationPoint(0F, -4F, 0F);
    topRightBrick.setTextureSize(64, 32);
    topRightBrick.mirror = true;
    setRotation(topRightBrick, 0F, 0F, 0F);

  }

  @Override
  public void render(float scale) {
    this.bindCosmeticTexture("hats", "lego.png"); //Usage: type, location
    GL11.glRotatef(this.ticks_5, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(this.ticks_6, 1.0F, 0.0F, 0.0F);

    GL11.glScalef(1F, 1F, 1F);

    GL11.glTranslatef( - 0F, -0.45F, 0.0F);

    if (entityIn.isSneaking()) {
      GL11.glTranslated(0.0D, -0D, 0.0D);
    }

    this.brickBase.render(scale);
    this.topRightBrick.render(scale);
    this.topLeftBrick.render(scale);
    this.bottomRightBrick.render(scale);
    this.bottomLeftBrick.render(scale);
  }
