package shift.sextiarysector3.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShaft extends ModelBase {
    // fields
    ModelRenderer Shape1;
    ModelRenderer out;
    ModelRenderer in;

    public ModelShaft() {

        textureWidth = 64;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-2F, -2F, -8F, 4, 4, 16);
        Shape1.setRotationPoint(0F, 0F, 0F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        out = new ModelRenderer(this, 40, 0);
        out.addBox(-4F, -4F, -8.1F, 8, 8, 4);
        out.setRotationPoint(0F, 0F, 0F);
        out.setTextureSize(64, 32);
        out.mirror = true;
        setRotation(out, 0F, 0F, 0F);
        in = new ModelRenderer(this, 40, 12);
        in.addBox(-4F, -4F, 4.1F, 8, 8, 4);
        in.setRotationPoint(0F, 0F, 0F);
        in.setTextureSize(64, 32);
        in.mirror = true;
        setRotation(in, 0F, 0F, 0F);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3,
            float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        Shape1.render(f5);
        // out.render(f5);
        // in.render(f5);
    }

    public void renderOut(Entity entity, float f, float f1, float f2, float f3,
            float f4, float f5) {
        out.render(f5);
    }

    public void renderIn(Entity entity, float f, float f1, float f2, float f3,
            float f4, float f5) {
        in.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
