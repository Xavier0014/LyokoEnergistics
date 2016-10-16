// Date: 2016-07-26 10:48:26
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package xavier0014.lyokoenergistics.model;

import javax.swing.text.html.parser.Entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTowerScreen extends ModelLE{
  //fields
    ModelRenderer Shape1;
  
  public ModelTowerScreen(){
    textureWidth = 64;
    textureHeight = 64;
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-12F, -24F, 0F, 24, 24, 0);
      Shape1.setRotationPoint(0F, 24F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Shape1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
